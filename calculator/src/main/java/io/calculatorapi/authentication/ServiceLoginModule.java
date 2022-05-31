package io.calculatorapi.authentication;

import org.eclipse.jetty.jaas.JAASPrincipal;
import org.eclipse.jetty.jaas.JAASRole;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

public class ServiceLoginModule implements LoginModule {
    private static final String ADMIN_USER_NAME = "admin";
    private static final String ADMIN_PW = "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08";
    public static final String ADMIN_ROLE = "admin";
    public static final String SERVICE_REALM = "service";

    private Subject subject;
    private CallbackHandler handler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private boolean isAdmin;
    private boolean succeeded;

    private String user;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.handler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        this.isAdmin = false;
        this.succeeded = false;
        this.user = null;
    }

    @Override
    public boolean login() throws LoginException {
        if (handler == null) {
            throw new LoginException("No callback handler available");
        }

        isAdmin = false;
        succeeded = false;
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("user name: ");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {
            handler.handle(callbacks);

            // extract username
            String username = ((NameCallback)callbacks[0]).getName();
            // extract password
            char[] pw = ((PasswordCallback)callbacks[1]).getPassword();
            if (pw == null) {
                // treat a NULL password as an empty password
                pw = new char[0];
            }
            char[] password = new char[pw.length];
            System.arraycopy(pw, 0, password, 0, pw.length);
            ((PasswordCallback)callbacks[1]).clearPassword();

            // check if user is admin
            if (username.equals(ADMIN_USER_NAME)) {
                String hashedPw = getPasswordHash(password).toUpperCase();

                if (hashedPw.equals(ADMIN_PW)) {
                    isAdmin = true;
                } else {
                    throw new LoginException("Invalid admin credentials");
                }
            }

            this.user = username;
            succeeded = true;

            return true;
        } catch (Exception e) {
            isAdmin = false;
            succeeded = false;
            this.user = null;
            throw new LoginException("Login failed");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded) {
            if (isAdmin) {
                subject.getPrincipals().add(new JAASRole(ADMIN_ROLE));
            } else if (this.user != null) {
                subject.getPrincipals().add(new JAASPrincipal(this.user));
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        logout();
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        this.subject.getPrincipals().clear();
        this.succeeded = false;
        this.isAdmin = false;
        this.user = null;

        return true;
    }

    private String getPasswordHash(char[] pw) throws LoginException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedPw = toBytes(pw);
            byte[] encodedhash = digest.digest(encodedPw);
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            throw new LoginException("Could not parse password.");
        }
    }

    byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }
}
