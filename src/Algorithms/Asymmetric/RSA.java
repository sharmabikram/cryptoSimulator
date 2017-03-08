package Algorithms.Asymmetric;

import Algorithms.CrypticObject;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;


import javax.crypto.Cipher;
import org.jfree.data.xy.XYSeries;


/**
 *
 * @author lenovo
 */
public class RSA implements Algorithms.CrypticAlgo{
    
    public static int[][] dataSet = new int[2][10];
    
    KeyPairGenerator kpg;
    KeyPair keyPair;
    PrivateKey privKey;
    PublicKey pubKey;
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    Cipher cipher;
    private static XYSeries point;
    static {
        point = new XYSeries("RSA");
        for(int i = 0; i<10; ++i){
            point.add(i, i);
        }
    }
    public RSA(){
        
        try{
            kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            keyPair = kpg.generateKeyPair();
            privKey = keyPair.getPrivate();
            pubKey = keyPair.getPublic();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public CrypticObject encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            sTime = System.currentTimeMillis();
            encrypted = cipher.doFinal(message);
            eTime = System.currentTimeMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        crypt.data = encrypted;
        crypt.time = eTime - sTime;
        return crypt;
    }

    @Override
    public CrypticObject decrypt(byte[] message) {
        byte[] decrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            sTime = System.currentTimeMillis();
            decrypted = cipher.doFinal(message);
            eTime = System.currentTimeMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        crypt.data = decrypted;
        crypt.time = eTime - sTime;
        return crypt;
    }

    
    public XYSeries getDataSet() {
    
            return point;  
    }
    
}
