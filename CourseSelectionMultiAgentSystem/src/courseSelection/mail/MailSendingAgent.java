package courseSelection.mail;

import com.mysql.jdbc.JDBC4Connection;
import courseSelection.Constants;
import courseSelection.jdbc.connection.JDBCConnection1;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.wrapper.AgentController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.math3.analysis.function.Constant;

public class MailSendingAgent extends Agent{
    private Session session;
    public void setup() {
        System.out.println("MalilProcessingAgent");

        final String username = "kenulih@gmail.com";
        final String password = "0nly4@vijitha";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
          });
        addBehaviour(new MailProcessBehavior());
    }
    
    private class MailProcessBehavior extends CyclicBehaviour {

        @Override
        public void action() {

		try {
                    try {
                        if(getEmailAdresses() != ""){
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("kenulih@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(getEmailAdresses()));
                            message.setSubject("Please send your job title");
                            message.setContent("Please send job title", "text/html");

                            Transport.send(message);

                            System.out.println("Done");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MailSendingAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                block(Constants.MAIL_SENDER_INTERVAL);
        } 
    }
    
    public String getEmailAdresses() throws SQLException {
        JDBCConnection1 connection = JDBCConnection1.getDbCon();
        ResultSet rs = connection.query("select email from student_data where job_title is null");
        String emails = "";
        while(rs.next()){
            emails = emails + ", " + rs.getString("email");
        }
       return emails;
    }
}
