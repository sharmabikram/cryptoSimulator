package Algorithms.Symmetric;

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
    byte[] raw;
    SecretKeySpec skeySpec; 
    Cipher cipher;
    
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
    public byte[] encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
        encrypted = cipher.doFinal(message);
        }catch(Exception e){
            e.printStackTrace();
        }
        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] message) {
        byte[] decrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skey);
        decrypted = cipher.doFinal(message);
        }catch(Exception e){
            e.printStackTrace();
        }
        return decrypted;
    }

     public static XYSeries getDataSet() {
    
            return point;  
    }
    
}
