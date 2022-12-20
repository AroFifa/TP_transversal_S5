package runnable.controller;

import db.dao.GenericDao;
import json.Error_obj;
import json.Success;
import model.Avion;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("avions")
public class AvionController {

    @GetMapping()
    public Object getVehicles() {

        Avion v = new Avion();
        List<Object> liste = null;
        try {
            liste = v.findAll(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return new Success(liste);

    }

    @GetMapping("assurance")
    public Object getExpiredVechile(@RequestParam(defaultValue = "0") int mois) {

        Avion v = new Avion();
        List<Avion> liste = null;
        try {
            liste = v.filter_expiredAssurance(mois,null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return new Success(liste);

    }

    @GetMapping("/{id}")
    public Object getVehicle(@PathVariable Integer id) {
        Avion v = new Avion();
        v.setId(id);
        try {
            v = v.findByID(null);
            if (v == null)
                return new Error_obj("404", "ID not found");

            return new Success(v);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public Object save(@RequestBody Avion vehicle) {
        GenericDao dao = new GenericDao();
        try {
            dao.save(vehicle);

            return new Success("vehicle created successfully");

        } catch (Exception e) {
            return new Error_obj("404", "creation vehicle failed");
            //throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        GenericDao dao = new GenericDao();
        Avion v = new Avion();
        v.setId(id);
        try {
            dao.delete(v);
            return new Success("vehicle deleted successfully");
        } catch (Exception e) {
            return new Error_obj("404", "delete vehicle failed");
            //throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Object update(@RequestBody Avion vehicle, @PathVariable Integer id) {
        GenericDao dao = new GenericDao();
        vehicle.setId(id);
        try {
            dao.update(vehicle);
            return new Success("vehicle updated successfully");
        } catch (Exception e) {
            return new Error_obj("404", "update vehicle failed");
            //throw new RuntimeException(e);
        }

    }

}
