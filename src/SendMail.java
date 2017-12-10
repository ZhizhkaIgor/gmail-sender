import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendMail{  
 public static void main(String[] args) {  

  final String user="lifeforlovedate@gmail.com";//change accordingly  
  final String password="julia1968";//change accordingly  
  int count = 1;
  Date sendDate = new Date();
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
   
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user, password);  
      }  
    });  
   
   PrintWriter out = null;
   String to="";//change accordingly
   String status = "";
   
   
   try {
	   out = new PrintWriter(new FileOutputStream(new File("statistics.txt"), true));
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	  
   for (String email : Emails.emails) {
   	 to = email;
   //Compose the message  
    try { 
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("LIFE for LOVE");  
		     message.setContent(MailHtml.mailHtml, "text/html; charset=utf-8");
		       
		    //send the message  
		     Transport.send(message);  
		  
		     status = String.format("Done Date: %s number: %s email: %s", sendDate, count, to);  
		     out.write(status +"\n");
			 count++;
			 System.out.println(status);
	    
     } catch (Exception e) {
    	 e.printStackTrace();
    	 status = String.format("Error Date: %s number: %s email: %s", sendDate, count, to);  
         out.write(status +"\n");
    	 count++;
    	 System.out.println(status);
     } 
   }
    System.out.println("All list emails done, get more");
    out.write("--------------------------\n");
    out.close();
  }  
}  