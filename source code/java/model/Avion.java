package model;

import db.dao.ConnectDb;
import db.dao.GenericDao;
import db.note.Attr;
import db.note.Table;
import db.note.Utilitaire;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Table
public class Avion {
    @Attr(pk = true, sequence = "vehicule_id_seq")
    Integer id;

    @Attr
    String matricule;

    @Attr
    String image;

    @Attr
    Integer id_categorie;

    Categorie_avion categorie;
    @Attr
    Integer id_modele;

    Modele_avion modele;
    @Attr
    Integer annee;


    List<Parcours> parcours;

    Payement_assurance payement_assurance;

    public Payement_assurance getPayement_assurance() {
        return payement_assurance;
    }

    public void setPayement_assurance(Payement_assurance payement_assurance) {
        this.payement_assurance = payement_assurance;
    }

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(List<Parcours> parcours) {
        this.parcours = parcours;
    }


    public Avion() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public Categorie_avion getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie_avion categorie) {
        this.categorie = categorie;
    }

    public Modele_avion getModele() {
        return modele;
    }

    public void setModele(Modele_avion modele) {
        this.modele = modele;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_modele() {
        return id_modele;
    }

    public void setId_modele(Integer id_modele) {
        this.id_modele = id_modele;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }












    private void build_model(Object o, ConnectDb con) throws Exception {

        Modele_avion m=new Modele_avion();
        m.setId(((Avion)o).getId_modele());
        try {
            ((Avion)o).setModele((Modele_avion) m.findByID(con));
        } catch (Exception e) {
            throw e;
        }
    }


    private void build_last_assurance(Object o, ConnectDb con) throws Exception {

        Payement_assurance p=new Payement_assurance();
        p.setId_avion(((Avion)o).getId());
        try {
            ((Avion)o).setPayement_assurance((Payement_assurance) p.getLast(con));
        } catch (Exception e) {
            throw e;
        }
    }

    private void build_ctg(Object o, GenericDao dao, ConnectDb con) throws Exception {

        Categorie_avion ctg=new Categorie_avion();
        ctg.setId(((Avion)o).getId_categorie());
        try {
            if(con==null)
                ((Avion)o).setCategorie((Categorie_avion) dao.getBy_id(ctg));
            else
                ((Avion)o).setCategorie((Categorie_avion) dao.getBy_id(ctg,con));
        } catch (Exception e) {
            throw e;
        }
    }



    private void build_fk(Object o,GenericDao dao,ConnectDb con) throws Exception {
        try {
            build_ctg(o,dao,con);
            build_model(o,con);
            build_last_assurance(o,con);
            build_parcours(o,con,dao);
        } catch (Exception e) {
            throw e;
        }

    }

    private void build_allFK(List<Object> list,GenericDao dao, ConnectDb con) throws Exception {
        for (Object o :
                list) {
            try {
                build_fk(o,dao,con);
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

            build_allFK(result,dao,con);
            return result;
        } catch (Exception e) {

            throw e;
        }
    }

    public List<Object> findAll( ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        boolean close=false;
        if(con==null){
            con= Utilitaire.getConnection(this);
            close=true;
        }
        try {
            List<Object> result=null;

            result= dao.getAll(this,con);

            build_allFK(result,dao,con);


            return result;
        } catch (Exception e) {

            throw e;
        }finally {
            if(close)
                con.close();
        }
    }

    private void build_parcours(Object obj,ConnectDb con,GenericDao dao) throws Exception {
        Parcours p=new Parcours();
        p.setId_avion(getId());


        try {

                ((Avion)obj).setParcours((List<Parcours>) (List) dao.get(p,"and",con));



        } catch (Throwable e) {
            throw e;
        }

    }

    public Avion findByID(ConnectDb con) throws Exception {
        GenericDao dao=new GenericDao();
        boolean close=false;
        if(con==null){
            con= Utilitaire.getConnection(this);
            close=true;
        }
        try {
            Object result=null;
            result=  dao.getBy_id(this,con);

            build_fk(result,dao,con);


            return (Avion) result;
        } catch (Exception e) {

            throw e;
        }finally {
            if(close)
                con.close();
        }
    }

    public List<Avion> filter_expiredAssurance(int next_month, List<Avion> avions) throws Exception {
        List<Avion> result=new ArrayList<>();

        if(avions ==null) {
            try {
                avions =(List<Avion>) (List) new Avion().findAll(null);
            } catch (Throwable e) {
                throw e;
            }
        }else if(avions.size()==0)
            avions =(List<Avion>) (List) new Avion().findAll(null);

        if(next_month==0)
            return avions;
        for (Avion v :
                avions) {
            if(v.getPayement_assurance()!=null){
                Date limit=Date.valueOf(LocalDate.now());

                Calendar calendar=Calendar.getInstance();
                calendar.setTime(limit);
                calendar.add(Calendar.MONTH,next_month);
                limit=new Date(calendar.getTimeInMillis());
                if(v.getPayement_assurance().getDate_expiration().compareTo(limit)==-1)
                    result.add(v);
            }
        }
        return result;
    }
}
