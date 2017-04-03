/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class Server {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private String message = "";
    JTextArea messageBox;
    private int msgLen, filterLen;
    
    public Server(JTextArea messageBox){
        this.messageBox = messageBox;
        try{
            server = new ServerSocket(1, 100);
            waitForConnection();
            protocolBB84();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void waitForConnection() throws IOException {
       showMessage("\nWaiting for someone to connect...\n");
       System.out.println("Wait");
       //connection = server.accept();
       //setUpStreams();
      /* new Thread(
                new Runnable(){
                    public void run(){
                        try {
                            // display in the text area
                            connection = server.accept();
                            setUpStreams();
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        )
        try {
            Thread.sleep(10000);
            if(connection == null)
                server.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       connection = server.accept();
       setUpStreams();
       System.out.println("over");
       Toolkit.getDefaultToolkit().beep();
       showMessage("\nConnected to a Client\n");
    }
    
    private void setUpStreams()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        input = new ObjectInputStream(connection.getInputStream());
        output.flush();
        showMessage("\nStreams Set up \n");
    }
    
    private void showMessage(final String text){
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                       messageBox.append(text);
                    }
                }
        );
    }
    
    
    private void protocolBB84(){
        String filter = null;
        BigInteger big = new BigInteger(256, new Random());
        StringBuilder binary = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int keyLen;
        binary.append(big.toString(2));
        if(binary.length() %2 != 0){
            binary.append("0");
        }
        
        msgLen = binary.length();
        filterLen = msgLen/2;
        keyLen = filterLen;
        System.out.println(binary.length());
        System.out.println(binary);
        //System.out.println("hi".length());
        sendMessage(binary.toString());
        
        try {
            // receiving the filter
            filter = (String)input.readObject();
            showMessage("The read filter is \n"+filter+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(filter !=null){
        char resFilter[] = new char[filterLen];
        char filterArr[] = new char[filterLen];
        char binaryArr[] = new char[msgLen];
        StringBuilder resF = new StringBuilder();
        int temp, val;
        // check the correctness of the filter
        filterArr = filter.toCharArray();
        binaryArr = binary.toString().toCharArray();
       // int j  =0;
        for(int i=0, j =0; i<msgLen; i = i+2, j++){
            temp = binaryArr[i] - '0';
            temp += binaryArr[i+1] - '0';
            
            if(temp%2 == 0 && filterArr[j] == '0')
                resF.append('1');
            else if(temp%2 == 1 && filterArr[j] == '1')
                resF.append('1');
            else
            {resF.append('0');}
        }
       // System.out.println(resFilter);
        
        // send the result filter
        sendMessage(resF.toString());
        resFilter = resF.toString().toCharArray();
        
        // process the filter to get the key
        int bit;
        char keyArr[] = new char[keyLen];
        StringBuilder key = new StringBuilder();
        for(int i = 0, j = 0 ; i<msgLen; i = i+2, j++){
            if(resFilter[j] == '1'){
                key.append(binaryArr[i+1]);
            }
        }// keyArr generated
        //String key = keyArr.toString();
        showMessage("\nThe Key is "+key.toString());
        System.out.println("key is");
        System.out.println(key);
        closeResources();
        }
    }
    
    private void sendMessage(final String text){
        try{
            output.writeObject(text);
        }catch(Exception e){
            System.out.println("Send message server");
            e.printStackTrace();
        }
    }
    
    private void closeResources(){
        try{
            System.out.println("Closing resiurces");
            showMessage("\nClosing Connection\n");
            input.close();
            output.close();
            connection.close();
            server.close();
            server = null;
        }catch(Exception e){
            System.out.println("In close server");
            e.printStackTrace();
        }
    }
}
