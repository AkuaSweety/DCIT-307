
import java.net.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;


public class Client
{

ClientInterface sups;
    public Client ()  {
        //instantiation of client interface
        sups =new ClientInterface();

        //shows the interface
        sups.setVisible(true);
        
        
        Thread connect=new ClientThread();
        
        
        connect.start();
        }



      // end constructor

// **************  main

    public static void main ( String [ ] args )  {

         new Client ();

    }  // end main


    }

