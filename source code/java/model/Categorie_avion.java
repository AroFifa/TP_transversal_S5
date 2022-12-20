package model;

import db.note.Attr;
import db.note.Table;

@Table
public class Categorie_avion {
    @Attr(pk = true,sequence = "categorie_avion_id_seq")
    Integer id;
    @Attr
    String categorie;

    public Categorie_avion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
