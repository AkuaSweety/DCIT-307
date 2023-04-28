
import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SOA
 */
public class Response {

//parameters to check response of server
private static int msgID;
private String  senderNumber,message;
//private static long time_response;

//private static long sec,min,hour;


// Time conversion constants

StringTokenizer separate;
//Verify user;

    public Response(){}

     public Response(String receive) {
                 System.out.println("Received message: "+receive);
            //ClientInterface.name.append("\nReceived message: "+receive);
                separate = new StringTokenizer(receive,":-:");
                //while(separate.hasMoreTokens()){

            msgID=Integer.parseInt(separate.nextToken(":-:"));
            senderNumber=separate.nextToken(":-:");
            message=separate.nextToken(":-:");
            
            ToPhone toPhone = new ToPhone();
            toPhone.setMsgID(msgID);
            toPhone.setSenderNumber(senderNumber);
            toPhone.setMessage(message);
            toPhone.setStatus(0);
            
            FromPhone fromPhone=new FromPhone();
            fromPhone.setMsgID(msgID);
            
            boolean state=new ToPhoneService().InsertToPhone(toPhone);
            if(state==true){
                System.out.println("message written into ToPhoneTable");
            
            //write to Cummulative table
                state=new FromPhoneService().DeleteFromPhone(fromPhone);
                
                if(state==true){
                
                System.out.println("message deleted into FromPhoneTable");   
                
                }
            
            }
            
      //  area.Setarea_name(txtAreaName.getText().toString());
      //  area.Setarea_desc(txtAreaDec.getText().toString());
      //  boolean state = new AreaService().Add(area);
      //  if(state == true){
      //      alertAdd.setVisible(state);
      //      txtAreaDec.setText("");
      //      txtAreaName.setText("");
      // 
            
            
            //}


}
}
