package model;


import db.note.Attr;
import db.note.Table;

@Table
public class Utilisateur {
    @Attr(pk = true, sequence = "utilisateur_id_seq")
    Integer id;
    @Attr
    String matricule;
    @Attr
    String email;

    @Attr
    String mdp;

    public Utilisateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
