package Algorithms.Asymmetric;

import Algorithms.CrypticObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;


import javax.crypto.Cipher;

import org.jfree.data.xy.XYSeries;


/**
 *
 * @author lenovo
 */
public class ElGamal implements Algorithms.CrypticAlgo{
    
    public static int[][] dataSet = new int[2][10];
    
    KeyPairGenerator kpg;
    KeyPair keyPair;
    PrivateKey privKey;
    SecureRandom random;
    PublicKey pubKey;
    
    long sTime, eTime;
    
    byte[] encrypted, decrypted;
    CrypticObject crypt = new CrypticObject();
    
    Cipher cipher;
    private static XYSeries point;
    static {
        point = new XYSeries("RSA");
        for(int i = 0; i<10; ++i){
            point.add(i, i);
        }
    }
    public ElGamal(){
        
        try{
            kpg = KeyPairGenerator.getInstance("ElGamal", "BC"); 
            random = new SecureRandom();
            kpg.initialize(160, random);
            keyPair = kpg.generateKeyPair();
            privKey = keyPair.getPrivate();
            pubKey = keyPair.getPublic();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public CrypticObject encrypt(byte[] message) {
        
        try{
            cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);
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
        
        
        try{
            cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
            cipher.init(Cipher.DECRYPT_MODE,  privKey, random);
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

    
    public static XYSeries getDataSet() {
    
            return point;  
    }
    
}
