/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import ktwtr.models.Member;
import ktwtr.models.Profile;

/**
 *
 * @author ram
 */
@Path("/ctrlprofiles")
public class CtrlProfiles {

    @Context
    HttpServletRequest request;

    @Path("/viewprofile")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response viewProfile() {
        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        member.setProfile(Profile.getProfile(member));
        return Response.ok().entity(member).build();
    }

    @Path("/editprofile")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response editProfile() {
        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        member.setProfile(Profile.getProfile(member));
        return Response.ok().entity(member).build();
    }

    @Path("/updateprofile")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateProfile(@FormParam("nom") String nom,@FormParam("prenom") String prenom, @FormParam("sex") String sex, @FormParam("email") String email) {
        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        member.setEmail(email);
        Ebean.update(member);
        
        Profile profile = Profile.getProfile(member);
        profile.setNom(nom);
        profile.setPrenom(prenom);
        profile.setSexe(sex);
        Ebean.update(profile);
        
        URI uri = URI.create("/ctrlprofiles/viewprofile");
        return Response.seeOther(uri).build();
    }
}
