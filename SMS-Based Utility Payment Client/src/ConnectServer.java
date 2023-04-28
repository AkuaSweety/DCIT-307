
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;


 class ConnectServer  {

//  declaration
/**
 * object of the ClientThread class
 */
ClientThread source;
/**
 * object of the Response class that takes the response from the server
 */
Response serverResponse;
/**
 * static object of the Socket class that creates the socket to connect to server
 */
static Socket connection;
/**
 * object of the Client InputStream that takes the input stream from the server
 */
InputStream inStream;
/**
 * object of the DataInputStream class that takes the input message from the server
 */
DataInputStream inDataStream;
/**
 * object of the OutputStream class takes the out stream to the server
 */
OutputStream outStream;
/**
 * object of the DataOutputStream class takes the out stream message to the server
 */
DataOutputStream outDataStream;


/**
 * received message from server
 */
private String receive;
/**
 * boolean to check for error in connect. It is true if there is error else false
 */
public boolean error;


/**
 * constructor to connect server
 * @param c is the Client that it should connect to server
 */

    ConnectServer ( ClientThread c)  {                  

        super ();

        source = (ClientThread ) c;

        connectServer ();

    }  // end constructor




/**
 * method that connects to the server
 */

  public void connectServer ()  {

    if(connection==null){

    try  {
      connection = new Socket ( ClientThread.host,ClientThread.port );

    }

     catch ( IOException except)  {
      JOptionPane.showMessageDialog(null, "Error connecting to server.\n\nPlease check server and restart application.", "SERVER CONNECTION ERROR", JOptionPane.ERROR_MESSAGE);

      ClientThread check=new ClientThread("a");
      check.ipConnect();

      error=true;
      //except.printStackTrace ();
    }
    }

    try  {
      outStream = connection.getOutputStream ();
      outDataStream = new DataOutputStream ( outStream );
      inStream = connection.getInputStream ();
      inDataStream = new DataInputStream ( inStream );
    }  // end try

    catch ( IOException except)  {

      JOptionPane.showMessageDialog(null, "Error connecting to server.\n\nPlease check server and restart application.", "SERVER CONNECTION ERROR", JOptionPane.ERROR_MESSAGE);
      
      ClientThread check=new ClientThread("a");
      check.ipConnect();
      error=true;
    }
    // end catch

  }  // end connectServer


/**
 * sends message to the server
 * @param send is the message to server
 */

  public void sendInfo (String send)  {

    try {

        outDataStream.writeUTF ( send );  //sends the message
        System.out.println (send);
        receive=inDataStream.readUTF ();
        //source.setReply(receive);  ///reads the reply
        System.out.println ( receive);
        serverResponse=new Response(receive);

    }  // end try for input

    catch(NullPointerException e){
        
    }

    catch ( EOFException except ) {
        closeConnection ();
    }  // end catch IOException
    catch ( IOException e )  {
        return;
    }  // end catch IOException
    catch(Exception e){
        ClientThread check=new ClientThread("a");
        /**
         * if there is an error in connection, the application uses this to request for
         * new port number
         */
        check.ipConnect();
    }

  }  // end sendReceive


/**
 * this method closes the connection to the server if there is an i\o exception
 */

  public void closeConnection () {

    try {
      connection.close ();
     }  catch ( IOException except )  {
        System.out.println ( "  Error closing connection\n" );
     }

  }  // end closeConnection


}  // end ConnectServer