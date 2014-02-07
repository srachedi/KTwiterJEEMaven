package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String content;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postDate;
    private long postLikes;
    @ManyToOne
    private Member autor;
    private List<Comment> comments;

    // Getters and Setters
    // ==============================================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public long getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(long postLikes) {
        this.postLikes = postLikes;
    }

    public Member getAutor() {
        return autor;
    }

    public void setAutor(Member autor) {
        this.autor = autor;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Methodes statics  ================================================================================
    public static List<Post> all() {
        return Ebean.find(Post.class).order().desc("postDate").findList();
    }

    public static long getNbrPosts() {
        return Ebean.find(Post.class).findRowCount();
    }
    
    public static long nbrPostsByMember(Member member) {
        return Ebean.find(Post.class).where().eq("autor",member).findRowCount();
    }
    public static List<Post> getPostsByM(Member member) {
        return Ebean.find(Post.class).where().eq("autor", member).findList();
    }

    public static Post getPost(long postId) {
        return Ebean.find(Post.class).where().eq("id", postId).findUnique();
    }

    public static void setPost(Post post) {
        post.postDate = new Date();
        Ebean.save(post);
    }
}
