package com.feeds.atmosphere.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import java.io.InputStream;

@Path("/")
public class FileResource {

    private
    @Context
    ServletContext sc;

    @Path("/jquery/{id}")
    @GET
    @Produces("application/javascript")
    public InputStream getJQuery(@PathParam("id") PathSegment ps) {
        return sc.getResourceAsStream("/jquery/" + ps.getPath());
    }

    @GET
    @Produces("text/html")
    public InputStream getIndex() {
        return sc.getResourceAsStream("/index.html");
    }
}
