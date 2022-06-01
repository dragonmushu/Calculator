package io.calculatorapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/openapi/")
public class SwaggerResource {

    @GET
    @Path("{path:.*}")
    public Response staticResources(@PathParam("path") final String path) {

        final InputStream resource = this.getClass().getResourceAsStream("/WEB-INF/openapi/" + path);

        return null == resource
            ? Response.status(NOT_FOUND).build()
            : Response.ok().entity(resource).build();
    }
}
