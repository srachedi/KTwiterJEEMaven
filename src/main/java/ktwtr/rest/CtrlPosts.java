/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.rest;

import com.avaje.ebean.Ebean;
import java.util.List;
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
import ktwtr.models.*;

/**
 *
 * @author ram
 */
@Path("/ctrlposts")
public class CtrlPosts {

    @Context
    HttpServletRequest request;

    @Path("/submitpost")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response submitPost(@FormParam("post") String post) {

        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());

        Post newpost = new Post();
        newpost.setAutor(member);
        newpost.setContent(post);
        Post.setPost(newpost);

        List<Post> posts = Post.all();
        for (int i = 0; i < posts.size(); i++) {
            List<Comment> comments = Comment.getCmntsByPost(posts.get(i));
            for (int j = 0; j < comments.size(); j++) {
                comments.get(j).setCommentLikes(Likes.nbrLikesPerComment(comments.get(j)));
            }
            posts.get(i).setComments(comments);
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();
    }

    @Path("/deletepost")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePost(@FormParam("post-id") long postid) {
        Post post = Post.getPost(postid);
        List<Comment> comments = Comment.getCmntsByPost(post);
        for (int i = 0; i < comments.size(); i++) {
            List<Likes> likes = Likes.getLikesByComment(comments.get(i));
            Ebean.delete(likes);
        }
        List<Likes> likes = Likes.getLikesByPost(post);
        Ebean.delete(likes);
        Ebean.delete(comments);
        Ebean.delete(post);

        List<Post> posts = Post.all();
        for (int i = 0; i < posts.size(); i++) {
            comments = Comment.getCmntsByPost(posts.get(i));
            for (int j = 0; j < comments.size(); j++) {
                comments.get(j).setCommentLikes(Likes.nbrLikesPerComment(comments.get(j)));
            }
            posts.get(i).setComments(comments);
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();
    }

    @Path("/allposts")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response allPosts() {
        List<Post> posts = Post.all();
        for (int i = 0; i < posts.size(); i++) {
            List<Comment> comments = Comment.getCmntsByPost(posts.get(i));
            for (int j = 0; j < comments.size(); j++) {
                comments.get(j).setCommentLikes(Likes.nbrLikesPerComment(comments.get(j)));
            }
            posts.get(i).setComments(comments);
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();
    }

    @Path("/wallposts")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response wallPosts() {
        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        List<Post> posts = Post.getPostsByM(member);
        for (int i = 0; i < posts.size(); i++) {
            List<Comment> comments = Comment.getCmntsByPost(posts.get(i));
            for (int j = 0; j < comments.size(); j++) {
                comments.get(j).setCommentLikes(Likes.nbrLikesPerComment(comments.get(j)));
            }
            posts.get(i).setComments(comments);
            posts.get(i).setPostLikes(Likes.nbrLikesPerPost(posts.get(i)));
        }
        return Response.ok().entity(posts).build();
    }
}
