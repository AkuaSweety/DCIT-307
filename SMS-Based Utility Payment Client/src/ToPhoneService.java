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


public class ToPhoneService {
    
	Connection con = null;
        String[] config = new Configuration().configDB();
	String url = config[2];
	String driver =config[3];
	String user =config[0];
	String pass =config[1];
        
	public boolean InsertToPhone(ToPhone toPhone){
		boolean insert=true;
		try{
		Class.forName(driver).newInstance();
		con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
                                String sqlState = String.format("INSERT INTO ToPhone (msgID,senderNumber,message,status) VALUES (%d,'%s','%s',%d)",toPhone.getMsgID(),toPhone.getSenderNumber(),toPhone.getMessage(),toPhone.getStatus());
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
        
        
        
	public boolean UpdateToPhone(ToPhone toPhone){
		boolean update=true;
		try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				//String sqlState = String.format("UPDATE ToPhone SET msgID=%d,senderNumber='%s',message='%s' status=%d WHERE username='%s'",toPhone.getUsername(),toPhone.getPassword(),toPhone.getStatus(),toPhone.getPrivilege(),toPhone.getUsername());
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
        
        
  
        
	public boolean DeleteToPhone(ToPhone toPhone){
		boolean delete=true;
		try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				String sqlState = String.format("DELETE FROM ToPhone WHERE msgID='%s'",toPhone.getMsgID());
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

	public Vector<ToPhone> getAllToPhone(){
		Vector<ToPhone> data = new Vector<ToPhone>();
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery("SELECT * FROM ToPhone");
				while(result.next())
				{
					ToPhone toPhone = new ToPhone();
					toPhone.setMsgID(result.getInt(1));
					toPhone.setSenderNumber(result.getString(2));
					toPhone.setMessage(result.getString(3));
                                        toPhone.setStatus(result.getInt(4));
					data.add(toPhone);
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

        
        
	public Vector<ToPhone> getByIDToPhone(ToPhone senderNumber){
		Vector<ToPhone> data = new Vector<ToPhone>();
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			try{
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery("SELECT * FROM ToPhone WHERE username ="+senderNumber.getSenderNumber());
				while(result.next())
				{
					ToPhone toPhone = new ToPhone();
					toPhone.setMsgID(result.getInt(1));
					toPhone.setSenderNumber(result.getString(2));
					toPhone.setMessage(result.getString(3));
                                        toPhone.setStatus(result.getInt(4));
					data.add(toPhone);
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
