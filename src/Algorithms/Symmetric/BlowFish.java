/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Symmetric;

import Algorithms.CrypticObject;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import org.jfree.data.xy.XYSeries;

public class BlowFish implements Algorithms.CrypticAlgo{

    public static int[][] dataSet = new int[2][10];
    static KeyGenerator kgen;
    static SecretKey skey;
    static byte[] raw;
    static SecretKeySpec skeySpec; 
    Cipher cipher;
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    private static XYSeries pointEncrypt, pointDecrypt;
    static {
        pointEncrypt = new XYSeries("Blow Fish");
        pointDecrypt = new XYSeries("Blow Fish");
        // hardcoded data set
        pointEncrypt.add(1, 0.018);
        pointEncrypt.add(2, 0.037);
        pointEncrypt.add(3, 0.056);
        pointEncrypt.add(4, 0.074);
        pointEncrypt.add(5, 0.092);
        pointEncrypt.add(6, 0.11);
        pointEncrypt.add(7, 0.128);
        pointEncrypt.add(8, 0.153);
        pointEncrypt.add(9, 0.171);
        pointEncrypt.add(10, 0.185);
        
        pointDecrypt.add(1, 0.021);
        pointDecrypt.add(2, 0.039);
        pointDecrypt.add(3, 0.058);
        pointDecrypt.add(4, 0.076);
        pointDecrypt.add(5, 0.096);
        pointDecrypt.add(6, 0.116);
        pointDecrypt.add(7, 0.145);
        pointDecrypt.add(8, 0.167);
        pointDecrypt.add(9, 0.171);
        pointDecrypt.add(10, 0.191);
    }
    
    public BlowFish(){
        try{
            kgen = KeyGenerator.getInstance("Blowfish");
        }catch(Exception e){
            System.out.println("No such Algorithm");
        }
        
        skey = kgen.generateKey();
        raw = skey.getEncoded();
        skeySpec = new SecretKeySpec(raw, "Blowfish");
    }
    @Override
    public CrypticObject encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            sTime = System.currentTimeMillis();
            encrypted = cipher.doFinal(message);
            eTime = System.currentTimeMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        crypt.data = encrypted;
        crypt.time = eTime -sTime;
        return crypt;
    }

    @Override
    public CrypticObject decrypt(byte[] message) {
        byte[] decrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
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
