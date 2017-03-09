/*
All the algorithm we wish to include should implement this interface
*/
package Algorithms;

import org.jfree.data.xy.XYSeries;

public interface CrypticAlgo {
    
    public CrypticObject encrypt(byte[] message);
    public CrypticObject decrypt(byte[] message);
    public XYSeries getEncryptionDataSet();
    public XYSeries getDecryptionDataSet();
}
