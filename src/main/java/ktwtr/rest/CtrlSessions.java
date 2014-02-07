/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/ctrlsessions")
public class CtrlSessions {

    @Context
    HttpServletRequest request;

    @Path("/signin")
    @POST
    public Response signin(@FormParam("login") String login, @FormParam("password") String password) {
        if (Member.isMember(login, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            return Response.ok().entity(login).build();
        } else {
            return Response.ok().entity("fail").build();
        }
    }

    @Path("/signout")
    @GET
    public Response signout() {
        HttpSession session = request.getSession(true);
        String login = session.getAttribute("login").toString();
        session.invalidate();
        return Response.ok().entity(login).build();
    }
}
