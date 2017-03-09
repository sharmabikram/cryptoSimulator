/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Asymmetric;

import Algorithms.CrypticAlgo;
import Algorithms.CrypticObject;
import org.bouncycastle.util.Arrays;
import org.jfree.data.xy.XYSeries;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Sneh
 */
public class blockRSA implements Algorithms.CrypticAlgo{
    
    int beg, end, length;
    byte[] msgBytes;
    CrypticObject crypt;
    ApplicationContext context; 
    Algorithms.CrypticAlgo algo; 
    

    @Override
    public CrypticObject encrypt(byte[] message) {
        
        context = new ClassPathXmlApplicationContext("spring.xml");
        algo = (CrypticAlgo)context.getBean("basicRSA");
        CrypticObject tmp;
        crypt = new CrypticObject();
        crypt.data = msgBytes;
        int sTime, eTime;
        
        beg = 0;
        length = message.length;
        sTime = eTime = 0;
        
        while(beg < length)
        {
                end = (beg+245>=length)?(length):(beg+245);
                msgBytes = Arrays.copyOfRange(message, beg, end);
                tmp = algo.encrypt(msgBytes);
                
                crypt.time += tmp.time;
                crypt.data = Arrays.concatenate(crypt.data, tmp.data);
                beg += 245;
                
        }
        
        return crypt;
    }

    @Override
    public CrypticObject decrypt(byte[] message) {
        CrypticObject tmp;
        crypt = new CrypticObject();
        int sTime, eTime;
        
        beg = 0;
        length = message.length;
        sTime = eTime = 0;
        
        while(beg < length)
        {
                end = (beg+256>=length)?(length):(beg+256);
                msgBytes = Arrays.copyOfRange(message, beg, end);
                tmp = algo.decrypt(msgBytes);
                
                crypt.time += tmp.time;
                crypt.data = Arrays.concatenate(crypt.data, tmp.data);
                beg += 256;
                
        }
        
        return crypt;
    }

    @Override
    public XYSeries getEncryptionDataSet() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        algo = (CrypticAlgo)context.getBean("basicRSA");
        return algo.getEncryptionDataSet();
    }

    @Override
    public XYSeries getDecryptionDataSet() {
        return algo.getDecryptionDataSet();
    }
    
}
