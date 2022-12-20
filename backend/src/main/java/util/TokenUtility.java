package util;

import db.dao.GenericDao;
import model.Token;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class TokenUtility {
    public static int min_duration=10;

    public static String hashString(String to_hash){

        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(to_hash.getBytes(StandardCharsets.UTF_8));

            String hash=String.format("%064x",new BigInteger(1, messageDigest.digest()));


            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getToken(String bearerToken){
        String[] bearer=bearerToken.split("Bearer ");
        if(bearer.length==2)
            return bearerToken.split("Bearer ")[1];
        return null;
    }

    public static boolean checkSum(String bearerToken){
        Token t=new Token();

        String token=TokenUtility.getToken(bearerToken);

        if(token==null)
            return false;

        GenericDao dao=new GenericDao();
        try {
            List<Object> stocked_token=dao.getAll(t);

            for (int i = 0; i < stocked_token.size(); i++) {
                Token tokenObj=(Token)(stocked_token.get(i));
                System.out.println("DBTOKEN: "+tokenObj.getToken());
                System.out.println("CLIENTTOKEN: "+token);
                if(tokenObj.getToken().equals(token))
                    if(!tokenObj.isExpired())
                        return true;

            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
