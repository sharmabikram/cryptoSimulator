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
    
    private static XYSeries pointEncrypt, pointDecrypt;
    static {
        pointEncrypt = new XYSeries("AES");
        pointDecrypt = new XYSeries("AES");
        // hardcoded data set
        pointEncrypt.add(1, 0.012);
        pointEncrypt.add(2, 0.022);
        pointEncrypt.add(3, 0.037);
        pointEncrypt.add(4, 0.045);
        pointEncrypt.add(5, 0.055);
        pointEncrypt.add(6, 0.077);
        pointEncrypt.add(7, 0.087);
        pointEncrypt.add(8, 0.093);
        pointEncrypt.add(9, 0.1);
        pointEncrypt.add(10, 0.111);
        
        pointDecrypt.add(1, 0.012);
        pointDecrypt.add(2, 0.024);
        pointDecrypt.add(3, 0.039);
        pointDecrypt.add(4, 0.043);
        pointDecrypt.add(5, 0.054);
        pointDecrypt.add(6, 0.066);
        pointDecrypt.add(7, 0.077);
        pointDecrypt.add(8, 0.087);
        pointDecrypt.add(9, 0.097);
        pointDecrypt.add(10, 0.108);
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

     public XYSeries getEncryptionDataSet() {
    
            return pointEncrypt;  
    }
    
    public XYSeries getDecryptionDataSet() {
    
            return pointDecrypt;  
    }
    
}
