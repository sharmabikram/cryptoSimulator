package Algorithms.Symmetric;

import Algorithms.CrypticObject;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author lenovo
 */
public class AES implements Algorithms.CrypticAlgo{
    
    public static int[][] dataSet = new int[2][10];
    KeyGenerator kgen;
    SecretKey skey;
    SecretKeySpec skeySpec; 
    Cipher cipher;
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    
    private static XYSeries point;
    static {
        point = new XYSeries("AES");
        for(int i = 0; i<10; ++i){
            point.add(i+1, i+3);
        }
    }
    
    public AES(){
        try{
            kgen = KeyGenerator.getInstance("AES");
        }catch(Exception e){
            System.out.println("No such Algorithm");
        }
        
        skey = kgen.generateKey();
    }
    
    @Override
    public CrypticObject encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
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
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skey);
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
