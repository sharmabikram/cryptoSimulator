/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author lenovo
 */
public class Client {
    private Socket connection;
    private String serverName;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    JTextArea messageBox ;
    
    public Client(String name, JTextArea box){
        messageBox = box;
        serverName = name;
        try{
          connection = new Socket(InetAddress.getByName(serverName), 1);  
          showMessage("\nConnected to server\n"+connection.getInetAddress());
          setUpStreams();
          BB84Protocol();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setUpStreams() throws Exception{
        output = new ObjectOutputStream(connection.getOutputStream());
        input = new ObjectInputStream(connection.getInputStream());
        //output.flush();
        showMessage("\nSet Up Streams\n");
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
    
    private void BB84Protocol(){
        String msg = null;
        String resFilter = null;
        BigInteger big = null;
        StringBuilder binary = new StringBuilder();
        int msgLen, filterLen, keyLen;
        
        try{
            // read the msg
            msg = (String) input.readObject();
            System.out.println("the msg is "+ msg);
            // generate a random filter
            big = new BigInteger(128, new Random());
            binary.append(big.toString(2));
            while(binary.length() != 128){
                binary.append("0");
            }
            
            // send the filter over network
            sendMessage(binary.toString());
            
            // read the result filter
            resFilter = (String)input.readObject();
            showMessage("The read filter is \n"+resFilter+"\n");
            // convertiing in tha char array
            msgLen = msg.length();
            filterLen = msgLen/2;
            char msgArr[] = new char[msgLen];
            char resFilterArr[] = new char[filterLen];
            msgArr = msg.toCharArray();
            resFilterArr = resFilter.toCharArray();
            
            // generate the key
            StringBuilder key = new StringBuilder();
            for(int i = 0, j = 0; i<msgLen; i= i+2, j++){
                if(resFilterArr[j] == '1'){
                    key.append(msgArr[i+1]);
                }
            }
            
            showMessage("the key is "+key.toString());
            closeResources();
        }catch(Exception e){
            System.out.println("BB84 client");
            e.printStackTrace();
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
        }catch(Exception e){
            System.out.println("In close server");
            e.printStackTrace();
        }
    }
    
}
