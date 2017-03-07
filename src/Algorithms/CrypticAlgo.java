/*
All the algorithm we wish to include should implement this interface
*/
package Algorithms;

public interface CrypticAlgo {
    
    public CrypticObject encrypt(byte[] message);
    public CrypticObject decrypt(byte[] message);
    //public int[][] getDataSet();

    
    
}
