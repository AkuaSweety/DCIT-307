/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author damola
 */
public class Configuration {
   String[] config = new String[3]; 
    public String[] configDB(){
        
        
        try{
            File f= new File("setup//DB");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String in = reader.readLine();
            
            System.out.println(in);
            
            String[] split = in.split("::");
            config = split;
            reader.close();
            
            
        }
        catch(Exception e){
            //System.out.println(e.getMessage());
        }
        
        return config;
    }
    
    
    public String[] configClient(){
        
        
        try{
            File f= new File("setup//client");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String in = reader.readLine();
            
            //System.out.println(in);
            
            String[] split = in.split("::");
            config = split;
            reader.close();
            
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return config;
    }

}
