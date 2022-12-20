package runnable.controller;

import db.dao.GenericDao;
import json.Error_obj;
import json.Success;
import model.Parcours;
import model.Avion;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/avions/{idavion}/kilometrages")
public class DetailsController {

    @GetMapping()
    public Object getParcours(@PathVariable Integer idavion) {
//,@RequestHeader("Authorization") String bearerToken
//        if(!TokenUtility.checkSum(bearerToken))
//            return new Error_obj("404","Access denied");

        GenericDao dao = new GenericDao();
        Avion v = new Avion();
        v.setId(idavion);
        try {
            v = v.findByID(null);
        } catch (Exception e) {
            return new Error_obj("404", "ID not found");
        }


        return new Success(v);
    }

    @GetMapping("/{id}")
    public Object getAvion(@PathVariable Integer idavion, @PathVariable Integer id) {
        GenericDao dao = new GenericDao();
        Parcours v = new Parcours();
        v.setId(id);
        v.setId_avion(idavion);
        try {

            Object parcours = dao.getBy_id(v);
            if (parcours == null)
                return new Error_obj("404", "ID not found");

            return new Success(parcours);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public Object save(@RequestBody Parcours p,@PathVariable Integer idavion) {
        GenericDao dao = new GenericDao();
        try {

            p.setId_avion(idavion);
            dao.save(p);
            return new Success("kilometrage created successfully");

        } catch (Exception e) {
            return new Error_obj("404", "creation kilometrage failed");
            //throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        GenericDao dao = new GenericDao();
        Parcours p = new Parcours();
        p.setId(id);
        try {
            dao.delete(p);
            return new Success("kilometrage deleted successfully");

        } catch (Exception e) {
            return new Error_obj("404", "delete kilometrage failed");
            //throw new RuntimeException(e);
        }
    }


    @PutMapping("/{id}")
    public Object update(@RequestBody Parcours p, @PathVariable Integer idavion, @PathVariable Integer id) {
        GenericDao dao = new GenericDao();
        p.setId(id);
        try {
            p.setId_avion(idavion);
            dao.update(p);
            return new Success("kilometrage updated successfully");

        } catch (Exception e) {
            return new Error_obj("404", "update kilometrage failed");
            //throw new RuntimeException(e);
        }

    }
}
