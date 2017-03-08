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
    private static XYSeries point;
    static {
        point = new XYSeries("Elliptic Curve");
        for(int i = 0; i<10; ++i){
            point.add(i, i);
        }
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

    
    public XYSeries getDataSet() {
    
            return point;  
    }
    
}
