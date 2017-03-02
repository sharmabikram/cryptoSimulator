
package Algorithms.Hash;

import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class MD5 implements Algorithms.CrypticAlgo{

    KeyGenerator kg;
    SecretKey sk;
    Mac mac;
    
    public MD5(){
        try{
            kg = KeyGenerator.getInstance("HmacMD5");
            sk = kg.generateKey();
            mac = Mac.getInstance("HmacMD5");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public byte[] encrypt(byte[] message) {
        try {
            mac.init(sk);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mac.doFinal(message);
    }

    @Override
    public byte[] decrypt(byte[] message) {
        return message;
    }

    
    public int[][] getDataSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
