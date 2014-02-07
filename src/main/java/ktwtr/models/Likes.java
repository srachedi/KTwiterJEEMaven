/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ram
 */
@Entity
@Table(name = "tb_likes")
public class Likes {

    @Id
    @GeneratedValue
    long Id;
    @ManyToOne
    Member member;
    @ManyToOne
    Post post;
    @ManyToOne
    Comment comment;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateLike;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
    }

    // Methodes statics  ================================================================================
    
    public static List<Likes> getLikesByPost(Post post) {
        return Ebean.find(Likes.class).where().eq("post", post).findList();
    }
    
    public static List<Likes> getLikesByComment(Comment comment) {
        return Ebean.find(Likes.class).where().eq("comment", comment).findList();
    }
    
    // Nombre de likes par Post.
    public static long nbrLikesPerPost(Post post) {
        return Ebean.find(Likes.class).where().eq("post", post).findRowCount();
    }

    // Nombre de likes par Commentaire.
    public static long nbrLikesPerComment(Comment comment) {
        return Ebean.find(Likes.class).where().eq("comment", comment).findRowCount();
    }

    // Verifier si un membre "member" a fait un like pour le post "post".
    public static Boolean isLikedPost(Post post, Member member) {
        return Ebean.find(Likes.class).where().eq("post", post).eq("member", member).findRowCount() > 0;
    }

    // Verifier si un membre "member" a fait un like pour le commentaire "comment".
    public static Boolean isLikedComment(Comment comment, Member member) {
        return Ebean.find(Likes.class).where().eq("comment", comment).eq("member", member).findRowCount() > 0;
    }

    //
    public static Likes getLikeByPostMember(Post post, Member member) {
        return Ebean.find(Likes.class).where().eq("post", post).eq("member", member).findUnique();
    }

    public static Likes getLikeByCommentMember(Comment comment, Member member) {
        return Ebean.find(Likes.class).where().eq("comment", comment).eq("member", member).findUnique();
    }
}
