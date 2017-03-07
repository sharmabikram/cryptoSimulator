/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DES implements Algorithms.CrypticAlgo{
    
    public static int[][] dataSet = new int[2][10];
    KeyGenerator kgen;
    SecretKey skey;
    byte[] raw;
    SecretKeySpec skeySpec; 
    Cipher cipher;
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    
    private static XYSeries point;
    static {
        point = new XYSeries("DES");
        for(int i = 0; i<10; ++i){
            point.add(i, i+5);
        }
    }
    
    public DES(){
        try{
            kgen = KeyGenerator.getInstance("DES");
        }catch(Exception e){
            System.out.println("No such Algorithm");
        }
        
        skey = kgen.generateKey();
    }
    
    @Override
    public CrypticObject encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
