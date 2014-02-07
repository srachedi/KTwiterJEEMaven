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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ktwtr.models.Comment;
import ktwtr.models.Likes;
import ktwtr.models.Member;
import ktwtr.models.Post;

/**
 *
 * @author ram
 */
@Path("/ctrllikes")
public class CtrlLikes {

    @Context
    HttpServletRequest request;

    @Path("/likepost")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response likePost(@FormParam("post-id") long postid) {

        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        Post post = Post.getPost(postid);

        if (Likes.isLikedPost(post, member)) {
            Likes like = Likes.getLikeByPostMember(post, member);
            Ebean.delete(like);
        } else {
            Likes newlike = new Likes();
            newlike.setMember(member);
            newlike.setPost(post);
            Ebean.save(newlike);
        }

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

    @Path("/likecomment")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response likeComment(@FormParam("comment-id") long commentid) {

        HttpSession session = request.getSession(true);
        Member member = Member.getMember(session.getAttribute("login").toString());
        Comment comment = Comment.getComment(commentid);

        if (Likes.isLikedComment(comment, member)) {
            Likes like = Likes.getLikeByCommentMember(comment, member);
            Ebean.delete(like);
        } else {
            Likes newlike = new Likes();
            newlike.setMember(member);
            newlike.setComment(comment);
            Ebean.save(newlike);
        }

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
}
