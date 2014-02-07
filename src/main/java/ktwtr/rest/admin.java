/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ktwtr.models.Member;

/**
 *
 * @author ram
 */
@Path("/admin")
public class admin {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Member> members (){
        return Member.members();
    }
}