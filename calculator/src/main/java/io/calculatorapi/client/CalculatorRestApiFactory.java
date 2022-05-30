package io.calculatorapi.client;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

public class CalculatorRestApiFactory {

    public static CalculatorRestApi build(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder()
                        // Longish settings useful when debuging
                        .readTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .build()
                )
                .build();

        return retrofit.create(CalculatorRestApi.class);
    }
}
