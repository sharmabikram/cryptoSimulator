
package Algorithms.Hash;


import java.security.MessageDigest;


public class SHA1 implements Algorithms.CrypticAlgo{
    
    MessageDigest sha1;
    
    public SHA1(){
        try{
            
           sha1 = MessageDigest.getInstance("SHA-1");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public byte[] encrypt(byte[] message) {
        return sha1.digest(message);
    }

    @Override
    public byte[] decrypt(byte[] message) {
        return message;
    }

    
    public int[][] getDataSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
