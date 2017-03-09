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
    
    private static XYSeries pointEncrypt, pointDecrypt;
    static {
        pointEncrypt = new XYSeries("DES");
        pointDecrypt = new XYSeries("DES");
        // hardcoded data set
        pointEncrypt.add(1, 0.033);
        pointEncrypt.add(2, 0.066);
        pointEncrypt.add(3, 0.098);
        pointEncrypt.add(4, 0.133);
        pointEncrypt.add(5, 0.179);
        pointEncrypt.add(6, 0.204);
        pointEncrypt.add(7, 0.239);
        pointEncrypt.add(8, 0.27);
        pointEncrypt.add(9, 0.316);
        pointEncrypt.add(10, 0.351);
        
        pointDecrypt.add(1, 0.035);
        pointDecrypt.add(2, 0.069);
        pointDecrypt.add(3, 0.106);
        pointDecrypt.add(4, 0.134);
        pointDecrypt.add(5, 0.169);
        pointDecrypt.add(6, 0.214);
        pointDecrypt.add(7, 0.239);
        pointDecrypt.add(8, 0.269);
        pointDecrypt.add(9, 0.302);
        pointDecrypt.add(10, 0.343);
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

    public XYSeries getEncryptionDataSet() {
    
            return pointEncrypt;  
    }
    
    public XYSeries getDecryptionDataSet() {
    
            return pointDecrypt;  
    }
    
}
