package courseSelection.mail;


import courseSelection.Constants;
import courseSelection.jdbc.connection.JDBCConnection1;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Flags;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class MailReaderAgent extends Agent{
    
    String host = "pop.gmail.com";// change accordingly
    String mailStoreType = "pop3";
    String password = "0nly4@vijitha";// change accordingly

    
    
    public void setup() {
        addBehaviour(new MailChecker());
    }
    
    private class MailChecker extends CyclicBehaviour {

        @Override
        public void action() {
            
             try {
                //create properties field
                Properties properties = new Properties();

                properties.put("mail.pop3.host", host);
                properties.put("mail.pop3.port", "995");
                properties.put("mail.pop3.starttls.enable", "true");
                Session emailSession = Session.getDefaultInstance(properties);

                //create the POP3 store object and connect with the pop server
                Store store = emailSession.getStore("pop3s");

                store.connect(host, "kenulih@gmail.com", password);

                //create the folder object and open it
                Folder emailFolder = store.getFolder("INBOX");
                emailFolder.open(Folder.READ_ONLY);

                // retrieve the messages from the folder in an array and print it
                //Message[] messages = emailFolder.getMessages();
                Message messages[] = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.RECENT), false));
                System.out.println("messages.length---" + messages.length);

                    // Sort messages from recent to oldest
                  Arrays.sort(messages, new Comparator<Message>() {

                      @Override
                      public int compare(Message m1, Message m2) {
                          int state = 0;
                          try {
                              state = m1.getSentDate().compareTo( m2.getSentDate());
                          } catch (MessagingException ex) {
                              Logger.getLogger(MailReaderAgent.class.getName()).log(Level.SEVERE, null, ex);
                          }
                          return state;
                      }

                  });

                for (int i = 0, n = messages.length; i < n; i++) {
                   Message message = messages[i];
                   System.out.println("---------------------------------");
                   System.out.println("Email Number " + (i + 1));
                   System.out.println("Subject: " + message.getSubject());
                   System.out.println("From: " + message.getFrom()[0]);
                   System.out.println("Text: " + message.getContent());
                   String[] data = getJobTitle(message.getContent());
                   //message.getContent();
                   if(data[0] != "")
                    insertJobAndCourse(getEmailAdderss(message.getFrom()[0].toString()), data[0], data[1]);
                }

                //close the store and folder objects
                emailFolder.close(false);
                store.close();
                 
            } catch (NoSuchProviderException e) {
               e.printStackTrace();
            } catch (MessagingException e) {
               e.printStackTrace();
            } catch (Exception e) {
               e.printStackTrace();
            }
            block(Constants.MAIL_READER_INTERVAL);
        }
    }
    
    public int insertCourseId(String courseId) {
        JDBCConnection1 conn = JDBCConnection1.getDbCon();
        String query = "insert into course_data(course_name) values('"+courseId+"')";
        int id=0;
        try {
            id = conn.insert(query);
        } catch (SQLException ex) {
            Logger.getLogger(MailReaderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int insertJobId(String jobTitle) {
        JDBCConnection1 conn = JDBCConnection1.getDbCon();
        String query = "insert into job_details(job_title) values('"+jobTitle+"')";
        int id=0;
        try {
            id = conn.insert(query);
        } catch (SQLException ex) {
            Logger.getLogger(MailReaderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private int getJobId(String jobTitle) throws SQLException {
        JDBCConnection1 connection = JDBCConnection1.getDbCon();
        ResultSet rs = connection.query("select * from job_details where job_title='"+jobTitle+"'");
        int id =0;
        while(rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }
    
    private int getCourseId(String courseName) throws SQLException {
        JDBCConnection1 connection = JDBCConnection1.getDbCon();
        ResultSet rs = connection.query("select * from course_data where course_name='"+courseName+"'");
        int id =0;
        while(rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }
    
    public void insertJobAndCourse(String email, String jobTitle, String courseName) throws SQLException {
        int jobId = getJobId(jobTitle);
        int courseId = getCourseId(courseName);
        if(jobId==0) {
            jobId = insertJobId(jobTitle);
        }
        if(courseId==0){
            courseId = insertCourseId(courseName);
        }
        
        JDBCConnection1 conn = JDBCConnection1.getDbCon();
        String query = "UPDATE STUDENT_DATA SET COURSE_ID="+courseId+", SELECTED_COURSE='"+courseName+"' , JOB_ID="+jobId+" , JOB_TITLE='"+jobTitle+"' WHERE EMAIL='"+email+"'";
        try {
            conn.insert(query);
        } catch (SQLException ex) {
            Logger.getLogger(MailReaderAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getEmailAdderss(String s) {
        s = s.substring(s.indexOf("<") + 1);
        if(s.indexOf(">") != -1) {
            s = s.substring(0, s.indexOf(">"));
        } else {
            s = "";
        }
        
        return s;
    }
    
    private String[] getJobTitle(Object o) throws MessagingException, IOException{
        String[] dataArr = new String[2];
        String s="";
        if(o instanceof  Multipart){
            Multipart content = (Multipart) o;
            for(int i=0; i<content.getCount(); i++){
                BodyPart bp = content.getBodyPart(i);
                s = s + bp.getContent().toString();
            }
        } else {
            s = o.toString();
        }
        
        s = s.substring(s.indexOf("{") + 1);
        if(s.indexOf("}") != -1) {
            s = s.substring(0, s.indexOf("}"));
        } else {
            s = "";
        }
        //job title: Software Engeneer selected course: Pysical Science
        
        s = s.substring(s.indexOf("job title:") + 10);
        if(s.indexOf("selected course:") != -1) {
            dataArr = s.split("selected course:");
        } else {
            s = "";
        } 
        return dataArr;
    }
    }
