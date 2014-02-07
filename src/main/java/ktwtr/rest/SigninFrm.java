/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author ram
 */
@Path("/signinfrm")
public class SigninFrm {
    @GET
    public URI getMsg() {
        JSONObject json = new JSONObject();
        return UriBuilder.fromUri("/signin.html").build();
    }    

}
