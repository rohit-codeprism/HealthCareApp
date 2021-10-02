package in.nit.rohit.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean send(String to[], String cc[], String bcc[], String subject, String text, Resource files[])
	{
		boolean send = false;
		try {
			//1. Create one empty message object 
		      MimeMessage message = mailSender.createMimeMessage(); 
		      //2 fill details(message, attachmentExist)
		      // if attachment are there write true or write false
		      MimeMessageHelper helper = new MimeMessageHelper(message,files != null && files.length > 0);
		      
		      helper.setTo(to);
		      if(cc != null)
		          helper.setCc(cc);
		      if(bcc != null)
		    	  helper.setBcc(bcc);
		      
		      helper.setSubject(subject);  
		      helper.setText(text);
		      
		      if(files != null)
		      {
		    	  for(Resource rob : files) {
		    		  helper.addAttachment(rob.getFilename(),rob);
		    		  
		    	  }
		      }
		      
		      // 3. send the email
		      
		      mailSender.send(message);
		      
		      send = true;
		      
		      
		      
			
		}catch(Exception e)
		{
			e.printStackTrace();
			send = false;
		}
		
		return send;
	}
	
	/***
	 * Overloaded message
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param files
	 * @return
	 */
	public boolean send(
			String to,
			String subject,
			String text,
			Resource file)
	{
		return send(
				new String[] {to},
				null, null,
				subject, text,
				file != null ? new Resource[] {file}: null);
	}
	
	public boolean send(
			String to, 
			String subject,
			String text)
	{
		return send(to,subject,text,null); 
	}

}
