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
public class blockElGamal implements Algorithms.CrypticAlgo{
    
    int beg, end, length;
    byte[] msgBytes;
    CrypticObject crypt;
    ApplicationContext context; 
    Algorithms.CrypticAlgo algo; 
    

    @Override
    public CrypticObject encrypt(byte[] message) {
        
        context = new ClassPathXmlApplicationContext("spring.xml");
        algo = (CrypticAlgo)context.getBean("basicElGamal");
        CrypticObject tmp;
        crypt = new CrypticObject();
        crypt.data = msgBytes;
        int sTime, eTime;
        
        beg = 0;
        length = message.length;
        sTime = eTime = 0;
        
        while(beg < length)
        {
                end = (beg+20>=length)?(length):(beg+20);
                msgBytes = Arrays.copyOfRange(message, beg, end);
                tmp = algo.encrypt(msgBytes);
                
                crypt.time += tmp.time;
                crypt.data = Arrays.concatenate(crypt.data, tmp.data);
                beg += 20;
                
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
                end = (beg+40>=length)?(length):(beg+40);
                msgBytes = Arrays.copyOfRange(message, beg, end);
                tmp = algo.decrypt(msgBytes);
                
                crypt.time += tmp.time;
                crypt.data = Arrays.concatenate(crypt.data, tmp.data);
                beg += 40;
                
        }
        
        return crypt;
    }

    @Override
    public XYSeries getDataSet() {
        return algo.getDataSet();
    }
    
}
