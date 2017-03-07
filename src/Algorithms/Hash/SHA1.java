
package Algorithms.Hash;


import Algorithms.CrypticObject;
import java.security.MessageDigest;


public class SHA1 implements Algorithms.CrypticAlgo{
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    MessageDigest sha1;
    
    public SHA1(){
        try{
            
           sha1 = MessageDigest.getInstance("SHA-1");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public CrypticObject encrypt(byte[] message) {
        sTime = System.currentTimeMillis();
        encrypted = sha1.digest(message);
        eTime = System.currentTimeMillis();
        
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
