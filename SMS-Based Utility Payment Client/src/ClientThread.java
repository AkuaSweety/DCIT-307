/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SOA
 */

import java.net.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;


public class ClientThread extends Thread //implements ActionListener 
{

/**
 * Stores the host address to be accessed by other classes.
 */
public static String host;

/**
 * stores the sent message to the server which can be accessed by other class
 */
public static String send_msg;
/**
 * stores the reply from the server which can be accessed by other class
 */
 public static String reply;
/**
 * stores the port number which is read from the file ip to connect to the server which can be accessed by other class
 */
public static int port;
/**
 * Holds the path of the file ip in memory
 */
File ipCheck;
/**
 * reads what is contained in the file ip
 */
FileReader read;
/**
 * writes into the file ip
 */
FileWriter ipWrite;
/**
 * stores the value of the port number given by the user before writing into file
 */
String ip;
/**
 * Object that connect to the server
 */
ConnectServer connection;
/**
 * Object that serves as the client interface
 */



    public ClientThread(){
            //stores the path of the file to check for ip
        ipCheck=new File("ipCheck/ip");

        //condition for if file exist
        if(ipCheck.isFile()){
        //sets the information to be used to connect to the server
        setHostInfo ();
        //connect to s erver
        connectToServer();          
        }
        else {
        //if file does not exist, implement to set port number
        ipConnect();
        }
    }
    
    public ClientThread(String a){}
    
    
    	public void connectToServer(){
            connection = new ConnectServer ( this );   //connects to server
        }  // end connectButton

        
        
        
        public void messageToServer(String msg){
            connection.sendInfo(msg);
        }
        
      public void setHostInfo ()  {

    ipCheck=new File("ipCheck/ip");

    //read the port number from the ip file created
    if(ipCheck.exists()){
      		try{


     			read=new FileReader(ipCheck);
      			BufferedReader buffreader=new BufferedReader(read);
                        ip=buffreader.readLine();
   	  			buffreader.close();
   	  			

   	  		StringTokenizer separate = new StringTokenizer(ip,"+");            

            host=separate.nextToken("+");
            ip=separate.nextToken("+");
                
                port=Integer.parseInt(ip);
             //   System.out.println("ip read: "+ip);
      		}catch(Exception e){
    	    }

  }
    }
      
      
      

    @Override
     public void run(){
         
         
          while(true){
        Vector<FromPhone> fromPhone=new FromPhoneService().getAllFromPhone();       
        
        
        int length=fromPhone.size();
        
        System.out.println (length + "from database");
        
        String[] dataFromPhone;
        int status;
        for(int i=0;i<=length-1;i++){
            if(length==0){}
            else{
            //for(int j=0;j<=3;j++)
           // {
                status=fromPhone.get(i).getStatus();
                if (status==0){
                    ClientInterface.name.append("\n\t\t"+ fromPhone.get(i).getMsgID() +"\t\t"+ fromPhone.get(i).getSenderNumber());
                    String message=fromPhone.get(i).getMsgID() +":-:"+ fromPhone.get(i).getSenderNumber() +":-:"+ fromPhone.get(i).getMessage() +":-:"+ fromPhone.get(i).getStatus();
                  messageToServer(message) ; 
                  //ClientInterface.name.append("message sent"+message);
                }   
                
                else {
                    continue;
                }
           // }
        }
        }
          }
    }
/*
     public void actionPerformed(ActionEvent evt)
     {
         if(evt.getSource()==inter.goOut){
             
         /*inter.setVisible(false);

         fadisoft=new Client();

             fadisoft.logout();*/

            // b=1;
        // }
    // }



/**
 * this method request for port number and writes it to file. It also connects to the server after taking the port number.
 */
    public void ipConnect() {
    	
         String ip2=JOptionPane.showInputDialog(null, "Enter the IP Address of the\nserver to connect client", "IP REQUEST",
                JOptionPane.INFORMATION_MESSAGE);         
         
         ip=JOptionPane.showInputDialog(null, "Enter the Port Number of the\nserver to connect client", "PORT REQUEST",
                JOptionPane.INFORMATION_MESSAGE);

		ip2=ip2+"+"+ip;
   		
         if(ip==null || ip2==null){
             System.exit(1);
             return;
         }
         try{
			/**
             * Make Directory
             */
			ipCheck = new File("ipCheck/");
			ipCheck.mkdirs();

			/**
             * Writes the ip into the file
             */
			ipWrite = new FileWriter("ipCheck/ip");
			ipWrite.write(ip2+"\n");
			ipWrite.flush();
			ipWrite.close();
		}catch(Exception e){}
         setHostInfo ();
         connectToServer();

    }
}
