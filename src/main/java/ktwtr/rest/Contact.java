/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.sun.jersey.api.view.Viewable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author ram
 */
@Path("/contact")
public class Contact {

    @GET
    @Produces("text/html")
    public Response getContact() {
        Viewable viewable = new Viewable("/contact.html");
        return Response.ok(viewable).build();
    }
}
