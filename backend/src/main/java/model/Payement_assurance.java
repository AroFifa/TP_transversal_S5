package model;

import db.dao.ConnectDb;
import db.dao.GenericDao;
import db.note.Attr;
import db.note.Table;

import java.sql.Date;

@Table
public class Payement_assurance {
    @Attr(pk = true, sequence = "payement_assurance_id_seq")
    Integer id;
    @Attr
    Integer id_avion;
    @Attr
    Date date_payement;
    @Attr
    Date date_expiration;



    public Payement_assurance getLast(ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        try {
            String query="SELECT * from Payement_assurance where id_avion=? order by date_payement DESC limit 1";
            return (Payement_assurance) dao.getUnique(this,query,con, getId_avion());
        } catch (Throwable e) {
            throw e;
        }
    }

    public Payement_assurance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_avion() {
        return id_avion;
    }

    public void setId_avion(Integer id_avion) {
        this.id_avion = id_avion;
    }

    public Date getDate_payement() {
        return date_payement;
    }

    public void setDate_payement(Date date_payement) {
        this.date_payement = date_payement;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }
}
