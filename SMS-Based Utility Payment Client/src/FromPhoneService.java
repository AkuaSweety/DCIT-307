/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SOA-NETBOOK
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class FromPhoneService {
    
	Connection con = null;
        String[] config = new Configuration().configDB();
	String url = config[2];
	String driver =config[3];
	String user =config[0];
	String pass=config[1];
        
        
        
	public boolean InsertFromPhone(FromPhone fromPhone){
		boolean insert=true;
		try{
		Class.forName(driver).newInstance();
		con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
                                String sqlState = String.format("INSERT INTO FromPhone (senderNumber,message,status) VALUES ('%s','%s',%d)",fromPhone.getSenderNumber(),fromPhone.getMessage(),fromPhone.getStatus());
				st.executeUpdate(sqlState);
				con.close();
			}
			catch(SQLException s){
			insert=false;
			}
		}
		catch(Exception e){
			insert=false;
		}
		return insert;
	}
        
        
        
	public boolean UpdateFromPhone(FromPhone fromPhone){
		boolean update=true;
		try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				//String sqlState = String.format("UPDATE FromPhone SET msgID=%d,senderNumber='%s',message='%s' status=%d WHERE username='%s'",fromPhone.getUsername(),fromPhone.getPassword(),fromPhone.getStatus(),fromPhone.getPrivilege(),fromPhone.getUsername());
				//st.executeUpdate(sqlState);
				con.close();
			}
			catch(SQLException s){
				update=false;
			}
		}
		catch(Exception e){
			update=false;
		}
		return update;
	}
        
        
  
        
	public boolean DeleteFromPhone(FromPhone fromPhone){
		boolean delete=true;
		try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				String sqlState = String.format("DELETE FROM FromPhone WHERE msgID=%d",fromPhone.getMsgID());
				st.execute(sqlState);
				con.close();
			}
			catch(SQLException s){
				delete=false;
			}
		}
		catch(Exception e){
			delete=false;
		}
		return delete;
	}

	public Vector<FromPhone> getAllFromPhone(){
		Vector<FromPhone> data = new Vector<FromPhone>();
		try{
                    
                    /*System.out.println("url " + url);
                    System.out.println("driver " + driver);
                    System.out.println("pass " + pass);
                    System.out.println("user " + user);*/
        
			Class.forName(driver);
                       con = DriverManager.getConnection(url, user, pass);
                       try{
				Statement st = con.createStatement();
                                  
                                ResultSet result = st.executeQuery("SELECT * FROM FromPhone");
				while(result.next())
				{
                                   // System.out.println("inside getall");
					FromPhone fromPhone = new FromPhone();
					fromPhone.setMsgID(result.getInt(1));
					fromPhone.setSenderNumber(result.getString(2));
					fromPhone.setMessage(result.getString(3));
                                        fromPhone.setStatus(result.getInt(4));
					data.add(fromPhone);
                                        
                                        //System.out.println (result.getInt(1) + "  " +result.getString(2)+ "  "+result.getString(3)+ "  "+result.getInt(4));
				}
				con.close();
			}
			catch(SQLException s){
                            System.out.println("SQL Connection error");
				System.out.println(s.getMessage());
			}
		}
		catch(Exception e){
                    System.out.println("inside getall connection");
			System.out.println(e.getMessage());
		}
		return data;
	}

        
        
	public Vector<FromPhone> getByIDFromPhone(FromPhone senderNumber){
		Vector<FromPhone> data = new Vector<FromPhone>();
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery("SELECT * FROM FromPhone WHERE username ="+senderNumber.getSenderNumber());
				while(result.next())
				{
					FromPhone fromPhone = new FromPhone();
					fromPhone.setMsgID(result.getInt(1));
					fromPhone.setSenderNumber(result.getString(2));
					fromPhone.setMessage(result.getString(3));
                                        fromPhone.setStatus(result.getInt(4));
					data.add(fromPhone);
				}
				con.close();
			}
			catch(SQLException s){
				System.out.println(s.getMessage());
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return data;
	}
        
     
}
