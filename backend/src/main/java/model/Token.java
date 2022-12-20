package model;

import db.note.Attr;
import db.note.Table;
import util.TokenUtility;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Table
public class Token {
    @Attr(pk = true,sequence = "token_id_seq")
    Integer id;

    @Attr
    String email;

    @Attr
    String mdp;

    @Attr
    String token;

    @Attr
    Integer id_utilisateur;

    @Attr
    Timestamp creation_date;

    @Attr
    Timestamp expiration_date;

    Utilisateur utilisateur;

    public Utilisateur getSociete() {
        return utilisateur;
    }

    public void setSociete(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Token() {
    }

    public Token(Utilisateur utilisateur){
        setSociete(utilisateur);
        setEmail(utilisateur.getEmail());
        setMdp(utilisateur.getMdp());
        setId_utilisateur(utilisateur.getId());

    }


    public void generate(){

        String emailToken=TokenUtility.hashString(getEmail());
        String mdpToken=TokenUtility.hashString(getMdp());

        Timestamp creation_date=Timestamp.valueOf(LocalDateTime.now());

        LocalDateTime dateTime = LocalDateTime.now().plus(Duration.of(TokenUtility.min_duration, ChronoUnit.MINUTES));

        Timestamp expiration_date=Timestamp.valueOf(creation_date.toLocalDateTime().plus(TokenUtility.min_duration,ChronoUnit.MINUTES));

        setCreation_date(creation_date);
        setExpiration_date(expiration_date);

        String creation_token=TokenUtility.hashString(creation_date.toString());
        String expiration_token=TokenUtility.hashString(expiration_date.toString());

        String tokenH=emailToken.concat(mdpToken.concat(creation_token.concat(expiration_token)));
        setToken(tokenH);


    }

    public boolean isExpired(){
        return Timestamp.valueOf(LocalDateTime.now()).after(getExpiration_date());
    }

}
