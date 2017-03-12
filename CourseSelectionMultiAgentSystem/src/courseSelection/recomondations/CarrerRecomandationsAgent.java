package courseSelection.recomondations;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import courseSelection.Constants;
import courseSelection.course.CourseAgent;
import courseSelection.ontology.Course;
import courseSelection.ontology.CourseSelectionOntology;
import courseSelection.ontology.Recomondation;
import courseSelection.ontology.RecomondationAction;
import courseSelection.ontology.RecomondationOntology;
import courseSelection.ontology.Student;
import courseSelection.ontology.StudentCourseAction;
import jade.content.ContentElementList;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class CarrerRecomandationsAgent extends Agent{
    
    	private Codec codec = new SLCodec();
	private Ontology ontology = RecomondationOntology.getInstance();
    
    public void setup(){
        
        // Register language and ontology
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        Object[] argumentsOfAgent = getArguments();
        
        // Register the Course agent service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("carrer-recomondations");
        sd.setName("JADE-carrer-recomondations");
        dfd.addServices(sd);
        try {
                DFService.register(this, dfd);
        } catch (FIPAException fe) {
                fe.printStackTrace();
        }

        // Add the behaviour serving queries from student agents 
	addBehaviour(new OfferRequestsRecomondations());
                
        
    }
    
    private class OfferRequestsRecomondations extends CyclicBehaviour{

        @Override
        public void action() {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
            ACLMessage msg = myAgent.receive(mt);
            if (msg != null) {
                    ContentManager cm = myAgent.getContentManager();
                    try {
                            Action act = (Action) cm.extractContent(msg);
                            RecomondationAction recomondationAction = (RecomondationAction) act.getAction();
                            processRecomondation(msg, act, myAgent);

                    } catch (Codec.CodecException | OntologyException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
            } else {
                    block();
            }
        }
    }
    
    private synchronized void processRecomondation(ACLMessage msg, Action action, Agent agent) {
                RecomondationAction recoAction = (RecomondationAction) action.getAction();

                List<Recomondation> recoList = getRecomondations(recoAction.getCourseId());
                List r = recoAction.getRecomondations();
                //recoAction.setRecomondations(recoList);
                for(Recomondation recomondation : recoList){
                        r.add(recomondation);
                        
                }
                
                sendReplyMessage(ACLMessage.PROPOSE, msg, action, agent);
                
    }
    
    private synchronized List<Recomondation> getRecomondations(int courseId) {
        
        List<Long> recomondedIdList = getRecomondationItems(courseId);

        List<Recomondation> recomondationList = getRecomondationsByIds(recomondedIdList);
        
//        List<Recomondation> recoList = new ArrayList<>();
//        Recomondation r1 = new Recomondation();
//        r1.setJobTitle("Software Eng");
//        r1.setCompany("Virtusa");
//        r1.setSalary(10);
//        recoList.add(r1);
//        
//        Recomondation r2 = new Recomondation();
//        r1.setJobTitle("Software Eng2");
//        r1.setCompany("Virtusa2");
//        r1.setSalary(10);
//        recoList.add(r2);
//        
//        Recomondation r3 = new Recomondation();
//        r1.setJobTitle("Software Eng3");
//        r1.setCompany("Virtusa3");
//        r1.setSalary(10);
//        recoList.add(r3);
        return recomondationList;
    }
    
    private synchronized List<Recomondation> getRecomondationsByIds(List<Long> ids){
            Connection conn = null;
            Statement stmt = null;
            List<Recomondation> rlist = new ArrayList<>();
            if(ids.size() <= 0)
                return rlist;
            try{
               Class.forName("com.mysql.jdbc.Driver");
               conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD);

               stmt = conn.createStatement();
               
               String sql = "SELECT * FROM courseselection.student_data where job_id in (" + ids.get(0);
               for(Long id : ids){
                   if(id == ids.get(0))continue;
                   sql += ","+id;
               }
               sql += ")";
               ResultSet rs = stmt.executeQuery(sql);
               while(rs.next()){
                   Recomondation r = new Recomondation();
                   r.setJobTitle(rs.getString("job_title"));
                   r.setCompany(rs.getString("company"));
                   r.setSalary(rs.getInt("salary"));
                   rlist.add(r);
               }
               
            }catch(Exception e){
                 e.printStackTrace();
            }
        return rlist;
    }
    
    private synchronized void sendReplyMessage(int performatice, ACLMessage message, Action action, Agent agent) {
            ACLMessage reply = message.createReply();
            reply.setPerformative(performatice);


            ContentElementList cel = new ContentElementList();
            cel.add(action);

            try {
                    agent.getContentManager().fillContent(reply, cel);
            } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
            }

            agent.send(reply);
    }
    
   private  List<Long> getRecomondationItems(int courseId){
      List<Long> recomondedIdList = new ArrayList<>();
      try{
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUrl(Constants.MAHOUT_URL);
            dataSource.setUser(Constants.USERNAME);
            dataSource.setPassword(Constants.PASSWORD);
            //dataSource.setDatabaseName(Constants.DB_NAME);

            JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, Constants.MAHOUT_TABLE, "user_id","item_id", "preference", "timestamp");
          
         //Creating data model
         //DataModel datamodel = new FileDataModel(new File("E:\\MSC-AI\\project\\agentsystem\\CourseSelectionMultiAgentSystem\\data.csv")); //data
      
         //Creating UserSimilarity object.
         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(dataModel);
      
         //Creating UserNeighbourHHood object.
         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(0.1, usersimilarity, dataModel);
      
         //Create UserRecomender
         UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, userneighborhood, usersimilarity);
        
         List<RecommendedItem> recommendations = recommender.recommend(courseId, Constants.RECOMONDATION_COUNT);
			
         for (RecommendedItem recommendation : recommendations) {
            recomondedIdList.add(recommendation.getItemID());
         }
         System.out.println("done");
      }catch(Exception e){System.out.println(e);}
      return recomondedIdList;
   }
  }
