package org.eeasonloo.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 短信发送工具类
 */
public class MailUtils {
    public static final String VALIDATE_CODE = "001";//发送短信验证码
    public static final String ORDER_NOTICE = "002";//体检预约成功通知

    /**
     * Send Mail
	 * params to be edited
     */
    public static void sendShortMessage(String templateCode, String receiverEmail, String param) {
        // Recipient's email ID needs to be mentioned.
        String to = receiverEmail;

        // Sender's email ID needs to be mentioned
        String from = "easonloo1998@gmail.com" ;

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("easonloo1998@gmail.com", "Looyanyann");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			if(templateCode.equals(VALIDATE_CODE)){
				// Set Subject: header field
				message.setSubject("Verification Code" + param);

				// Now set the actual message
				message.setText("Ignore this, if u didnt make appointment to our SCU_Healthcare System. Sorry for your time!");

			}

            if(templateCode.equals(ORDER_NOTICE)){
				// Set Subject: header field
				message.setSubject("Your Appointment is Made Successfully");

				// Now set the actual message
				message.setText("Thank you for making appointment to our SCU_Healthcare System. See you soon!");

			}



            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
