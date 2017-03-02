/*
All the algorithm we wish to include should implement this interface
*/
package Algorithms;

public interface CrypticAlgo {
    
    public byte[] encrypt(byte[] message);
    public byte[] decrypt(byte[] message);
    //public int[][] getDataSet();

    
    
}
