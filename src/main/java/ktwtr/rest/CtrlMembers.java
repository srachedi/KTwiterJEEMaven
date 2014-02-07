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
import ktwtr.models.Profile;

/**
 *
 * @author ram
 */
@Path("/ctrlmembers")
public class CtrlMembers {
    
    @Context
    HttpServletRequest request;
    
    @Path("/addmember")
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response addMember(@FormParam("login") String login, @FormParam("email") String email, @FormParam("password") String password) {
        if (!Member.isMemberByLogin(login)) {
            Member member = new Member();
            member.setLogin(login);
            member.setEmail(email);
            member.setPassword(password);
            Ebean.save(member);
            
            member = Member.getMember(login);
            Profile profile = new Profile();
            profile.setMember(member);
            profile.setRoles("Member");
            Profile.setProfile(profile);
            
            HttpSession session = request.getSession(true);
            session.setAttribute("login", login);
            return Response.ok().entity(login).build();
        }else{
            return Response.ok().entity("fail").build();
        }       
    }
    
    @Path("/delmember")
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response deleteMember(@FormParam("login") String login) {
        Member member = Member.getMember(login);
        Ebean.delete(member);
        return Response.ok().entity("The member '" + login + "' is deleted.").build();
    }
    
    @Path("/updatemember")
    @POST
    @Produces({MediaType.TEXT_PLAIN})
    public Response updateMember(@FormParam("email") String email, @FormParam("password") String password) {
        Member member = Member.getMember("");
        //Ebean.delete(member);
        return Response.ok().entity("The member is updated.").build();
    }
}
