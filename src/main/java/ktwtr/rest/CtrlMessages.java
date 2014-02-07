/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Member;
import ktwtr.models.Message;

/**
 *
 * @author ram
 */
@Path("/ctrlmessages")
public class CtrlMessages {

    @Context
    HttpServletRequest request;

    @Path("/savemessage")
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response saveMessage(@FormParam("message") String content, @FormParam("destlogin") String destlogin, @FormParam("title") String title) {
        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        
        Message message = new Message();
        message.setTitle(title);
        message.setMessage(content);
        message.setExpediteur(member);
        message.setDestinataire(Member.getMember(destlogin));
        Message.setMessage(message);
        return Response.ok().entity("The member is signed up").build();
    }

    @Path("/deletemessage")
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteMessage(@FormParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {
        Member member = new Member();
        member.setLogin(login);
        member.setEmail(email);
        member.setPassword(password);
        Ebean.save(member);
        return Response.ok().entity("The member is signed up").build();
    }
}
