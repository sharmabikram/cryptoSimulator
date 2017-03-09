package Algorithms.Asymmetric;

import Algorithms.CrypticObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;


import javax.crypto.Cipher;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

import org.jfree.data.xy.XYSeries;


/**
 *
 * @author lenovo
 */
public class EllipticCurve implements Algorithms.CrypticAlgo{
    
    public static int[][] dataSet = new int[2][10];
    
    KeyPairGenerator kpg;
    KeyPair keyPair;
    PrivateKey privKey;
    SecureRandom random;
    PublicKey pubKey;
    
    byte[] encrypted, decrypted;
    long sTime, eTime;
    CrypticObject crypt = new CrypticObject(); 
    Cipher cipher;
    private static XYSeries pointEncrypt, pointDecrypt;
    static {
        pointEncrypt = new XYSeries("Elliptic Curve");
        pointDecrypt = new XYSeries("Elliptic Curve");
        // hardcoded data set
        pointEncrypt.add(1, 0.066);
        pointEncrypt.add(2, 0.126);
        pointEncrypt.add(3, 0.181);
        pointEncrypt.add(4, 0.239);
        pointEncrypt.add(5, 0.31);
        pointEncrypt.add(6, 0.372);
        pointEncrypt.add(7, 0.432);
        pointEncrypt.add(8, 0.492);
        pointEncrypt.add(9, 0.531);
        pointEncrypt.add(10, 0.615);
        
        pointDecrypt.add(1, 0.063);
        pointDecrypt.add(2, 0.123);
        pointDecrypt.add(3, 0.179);
        pointDecrypt.add(4, 0.235);
        pointDecrypt.add(5, 0.298);
        pointDecrypt.add(6, 0.354);
        pointDecrypt.add(7, 0.412);
        pointDecrypt.add(8, 0.471);
        pointDecrypt.add(9, 0.523);
        pointDecrypt.add(10, 0.601);
    }
    public EllipticCurve(){
        
        try{
            kpg = KeyPairGenerator.getInstance("ECDSA", "BC"); 
            random = new SecureRandom();
            ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("prime192v1");
            kpg.initialize(ecSpec, random);
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
            cipher = Cipher.getInstance("ECIES");
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
        byte[] decrypted = "".getBytes();
        try{
            cipher = Cipher.getInstance("ECIES");
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

    
    public XYSeries getEncryptionDataSet() {
    
            return pointEncrypt;  
    }
    
    public XYSeries getDecryptionDataSet() {
    
            return pointDecrypt;  
    }
    
}
