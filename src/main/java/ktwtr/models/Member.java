package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

@Entity
@Table(name = "tb_member")
public class Member {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String email;
    private String password;
    private long nbrPosts;
    private long nbrComments;
    private long nbrReceivedMessages;
    private long nbrSentMessages;
    private List<Message> receivedMessages;
    private List<Message> sentMessages;
    private List<Comment> comments;
    private List<Post> posts;
    @OneToOne
    private Profile profile;

    // Getters and Setters ================================================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNbrPosts() {
        return nbrPosts;
    }

    public void setNbrPosts(long nbrPosts) {
        this.nbrPosts = nbrPosts;
    }

    public long getNbrComments() {
        return nbrComments;
    }

    public void setNbrComments(long nbrComments) {
        this.nbrComments = nbrComments;
    }

    public long getNbrReceivedMessages() {
        return nbrReceivedMessages;
    }

    public void setNbrReceivedMessages(long nbrReceivedMessages) {
        this.nbrReceivedMessages = nbrReceivedMessages;
    }

    public long getNbrSentMessages() {
        return nbrSentMessages;
    }

    public void setNbrSentMessages(long nbrSentMessages) {
        this.nbrSentMessages = nbrSentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    // Methodes statics ====================================================================================
    public static List<Member> members() {
        return Ebean.find(Member.class).findList();
    }

    public static Member getMember(String login) {
        return Ebean.find(Member.class).where().eq("login", login).findUnique();
    }

    public static Boolean isMember(String login, String password) {
        return Ebean.find(Member.class).where().eq("login", login).eq("password", password).findRowCount() > 0;
    }

    public static Boolean isMemberByLogin(String login) {
        return Ebean.find(Member.class).where().eq("login", login).findRowCount() > 0;
    }
}
