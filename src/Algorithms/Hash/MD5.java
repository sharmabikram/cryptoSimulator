
package Algorithms.Hash;

import Algorithms.CrypticObject;
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
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    
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
    public CrypticObject encrypt(byte[] message) {
        try {
            mac.init(sk);
            sTime = System.currentTimeMillis();
            encrypted = mac.doFinal(message);
            eTime = System.currentTimeMillis();
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        crypt.data = encrypted;
        crypt.time = eTime - sTime;
        return crypt;
    }

    @Override
    public CrypticObject decrypt(byte[] message) {
        return crypt;
    }

    
    public int[][] getDataSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
