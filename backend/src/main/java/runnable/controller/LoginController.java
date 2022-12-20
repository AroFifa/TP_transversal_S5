package runnable.controller;

import db.dao.GenericDao;
import json.Error_obj;
import json.Success;
import model.Utilisateur;
import model.Token;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import util.TokenUtility;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/signin")
    public Object signIn(@RequestBody Utilisateur utilisateur, @RequestHeader("Authorization") String bearerToken) {


        if(!TokenUtility.checkSum(bearerToken))
            return new Error_obj("401","Access denied");

        GenericDao dao = new GenericDao();
        try {

            dao.save(utilisateur);
            return new Success("signin sucess");

        } catch (Exception e) {
            return new Error_obj("404", "signin failed");
            //throw new RuntimeException(e);
        }

    }


    @DeleteMapping("/logout")
    public Object logout(@RequestHeader("Authorization") String bearerToken){
//        TOKEN client alaina avy any amn requête
        String token_client=TokenUtility.getToken(bearerToken);

        if(token_client==null)
            return new Error_obj("500","TOKEN doesn't exist");

        Token token=new Token();
        token.setToken(token_client);

        GenericDao dao=new GenericDao();
        try {
            List<Object> token_obj = dao.get(token,"and");
            if(token_obj.size()!=0)
                token=(Token)token_obj.get(0);


            dao.delete(token);
            return new Success(token_client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @PostMapping
    public Object login(@RequestBody Utilisateur utilisateur) {
        GenericDao dao = new GenericDao();

        try {
            ArrayList<Object> s = dao.get(utilisateur, "and");

            if (s == null || s.size()==0)
                return new Error_obj("500", "authentification failed");

            Object result=s.get(0);

            Token token=new Token((Utilisateur) result);
            token.generate();
            dao.save(token);

            HttpHeaders header=new HttpHeaders();
            header.set("Bearer",token.getToken());


            return new Success(token.getToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
