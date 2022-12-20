package model;

import db.dao.ConnectDb;
import db.dao.GenericDao;
import db.note.Attr;
import db.note.Table;

import java.util.List;

@Table
public class Modele_avion {
    @Attr(pk = true,sequence = "modele_avion_id_seq")
    Integer id;

    @Attr
    Integer id_marque;

    @Attr
    String modele;

    Marque_avion marque;

    public Marque_avion getMarque() {
        return marque;
    }

    public void setMarque(Marque_avion marque) {
        this.marque = marque;
    }

    private void build_marque(Object o,GenericDao dao,ConnectDb con) throws Exception {

        Marque_avion m=new Marque_avion();
        m.setId(((Modele_avion)o).getId_marque());
        try {
            if(con==null)
                ((Modele_avion)o).setMarque((Marque_avion) dao.getBy_id(m));
            else
                ((Modele_avion)o).setMarque((Marque_avion) dao.getBy_id(m,con));
        } catch (Exception e) {
            throw e;
        }
    }

    private void build_marques(List<Object> list,GenericDao dao,ConnectDb con) throws Exception {
        for (Object o :
                list) {
            try {
                build_marque(o,dao,con);
            } catch (Exception e) {
                throw e;
            }
        }
    }
    public List<Object> find(String condition, ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        try {
            List<Object> result=null;
            if(con==null)
                result=dao.get(this,condition);
            else
                result= dao.get(this,condition,con);

            build_marques(result,dao,con);
            return result;
        } catch (Exception e) {

            throw e;
        }
    }
    public List<Object> findAll( ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        try {
            List<Object> result=null;
            if(con==null)
                result=dao.getAll(this);
            else
                result= dao.getAll(this,con);

            build_marques(result,dao,con);
            return result;
        } catch (Exception e) {

            throw e;
        }
    }

    public Modele_avion findByID(ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        try {
            Object result=null;
            if(con==null)
                result=  dao.getBy_id(this);
            else
                result=  dao.getBy_id(this,con);

            build_marque(result,dao,con);
            return (Modele_avion) result;
        } catch (Exception e) {

            throw e;
        }
    }
    public Modele_avion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_marque() {
        return id_marque;
    }

    public void setId_marque(Integer id_marque) {
        this.id_marque = id_marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String model) {
        this.modele = modele;
    }
}
