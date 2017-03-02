/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Symmetric;

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
    
    private static XYSeries point;
    static {
        point = new XYSeries("BolwFish");
        for(int i = 0; i<10; ++i){
            point.add(i, i+7);
        }
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
    public byte[] encrypt(byte[] message) {
        byte[] encrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
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
            cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
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
