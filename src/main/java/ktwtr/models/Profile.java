package ktwtr.models;

import com.avaje.ebean.Ebean;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_profile")
public class Profile{

    @Id
    @GeneratedValue
    private long id;
    private String nom;
    private String prenom;
    private String sexe;
    @OneToOne
    private Member member;
    private String roles;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInscription;

//    // Getters and Setters ================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

//    // Methodes statics ====================================================================================
    public static List<Profile> all() {
        return Ebean.find(Profile.class).findList();
    }

    public static Profile getProfile(Member member) {
        return Ebean.find(Profile.class).where().eq("member", member).findUnique();
    }

    public static void setProfile(Profile profile) {
        profile.dateInscription = new Date();
        Ebean.save(profile);
    }
}