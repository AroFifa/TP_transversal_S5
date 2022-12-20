package model;

import db.note.Attr;
import db.note.Table;

@Table
public class Marque_avion {

    @Attr(pk = true,sequence = "marque_avion_id_seq")
    Integer id;
    @Attr
    String marque;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Marque_avion() {
    }
}
