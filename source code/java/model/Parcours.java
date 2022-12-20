package model;

import db.note.Attr;
import db.note.Table;

import java.sql.Date;

@Table
public class Parcours {
    @Attr(pk = true, sequence = "parcours_id_seq")
    Integer id;
    @Attr
    Integer id_avion;
    @Attr
    Date date;
    @Attr
    Integer debut_km;
    @Attr
    Integer fin_km;

    public Parcours() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDebut_km() {
        return debut_km;
    }

    public void setDebut_km(Integer debut_km) {
        this.debut_km = debut_km;
    }

    public Integer getFin_km() {
        return fin_km;
    }

    public void setFin_km(Integer fin_km) {
        this.fin_km = fin_km;
    }
}
