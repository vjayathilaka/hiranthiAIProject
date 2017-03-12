package courseSelection.course; 

import courseSelection.Constants;
import courseSelection.agentCreation.SubjectCombination;
import courseSelection.jdbc.connection.JDBCConnection1;
import courseSelection.mail.MailReaderAgent;
import java.util.List; 
import java.util.Map;

import courseSelection.ontology.Course;
import courseSelection.ontology.CourseSelectionOntology;
import courseSelection.ontology.Recomondation;
import courseSelection.ontology.Student;
import courseSelection.ontology.StudentCourseAction;
import jade.content.ContentElementList;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseAgent extends Agent {

	private static final long serialVersionUID = 1L;

	private Codec codec = new SLCodec();
	private Ontology ontology = CourseSelectionOntology.getInstance();

        /*private int courseId;
	private String courseName;
        private ArrayList<ArrayList<SubjectCombination>> courseList;
	private List offeredUniversities;
        private int isEnglishComp;
        private int isMathsComp;
        private int proposedIntake;
        private int schemaId;
        private Map<Integer, Float> districtZScoreMap;*/

	protected void setup() {

		// Register language and ontology
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);

                Object[] argumentsOfAgent = getArguments();
                
                /*courseName = (String) argumentsOfAgent[0];
                courseList = (ArrayList<ArrayList<SubjectCombination>>) argumentsOfAgent[1];
                offeredUniversities = (List) argumentsOfAgent[2];
                isEnglishComp = (int) argumentsOfAgent[3];
                isMathsComp = (int) argumentsOfAgent[4];
                proposedIntake = (int) argumentsOfAgent[5];
                courseId = (int) argumentsOfAgent[6];
                districtZScoreMap = (Map<Integer, Float>) argumentsOfAgent[7];
                schemaId = (int) argumentsOfAgent[8];*/

		// Register the Course agent service in the yellow pages
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("course-selection");
		sd.setName("JADE-course-selection");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Add the behaviour serving queries from student agents
		addBehaviour(new OfferRequestsServer());
        }

	// Put agent clean-up operations here
	protected void takeDown() {
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
		System.out.println("Course agent " + getAID().getName() + " terminating.");
	}

	private class OfferRequestsServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				ContentManager cm = myAgent.getContentManager();
				try {
					Action act = (Action) cm.extractContent(msg);
					StudentCourseAction scAction = (StudentCourseAction) act.getAction();
					processStudentData(msg, act, myAgent);

				} catch (CodecException | OntologyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}
        
        private synchronized void processStudentData(ACLMessage message, Action action, Agent agent) {
            
            	StudentCourseAction studentCourseAction = (StudentCourseAction) action.getAction();
		Student student = studentCourseAction.getStudent();
                saveStudentData(student);
                List<Course> courseList = getCourseList();
                for(Course course : courseList){
                        Course c = studentCourseAction.getCourse();
                        c.setId(course.getId());
                        c.setCourseName(course.getCourseName());
                        sendReplyMessage(ACLMessage.INFORM, message, action, agent);
                }
        }
        
        private synchronized void saveStudentData(Student s) {
            JDBCConnection1 conn = JDBCConnection1.getDbCon();
            String query = "insert into student_data(name, subject1, subject2, subject3, district, zscore, gender, age, olEnglish"
                    + ", olMaths, email, city, attempt) values("
                    + "'"+s.getName()+"'"
                    + ","+s.getSubject1()
                    + ","+s.getSubject2()
                    + ","+s.getSubject3()
                    + ","+s.getDistrictId()
                    + ","+s.getzScore()
                    + ",'"+s.getGender()+"'"
                    + ","+s.getAge()
                    + ",'"+s.getoLEnglish()+"'"
                    + ",'"+s.getoLMaths()+"'"
                    + ",'"+s.getEmail()+"'"
                    + ",'"+s.getCity()+"'"
                    + ","+s.getAttempt()
                    + ")";

            try {
               int id = conn.insert(query);
            } catch (SQLException ex) {
                Logger.getLogger(MailReaderAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private synchronized List<Course> getCourseList() {
            List<Course> courseList = new ArrayList();
            Course c1 = new Course();
            c1.setId(1);
            c1.setCourseName("course 1");
            courseList.add(c1);
            Course c2 = new Course();
            c2.setId(2);
            c2.setCourseName("course 2");
            courseList.add(c2);
            Course c3 = new Course();
            c3.setId(3);
            c3.setCourseName("course 3");
            courseList.add(c3);
            Course c4 = new Course();
            c4.setId(4);
            c4.setCourseName("course 4");
            courseList.add(c4);
            
            return courseList;
        }

	private synchronized void sendReplyMessage(int performatice, ACLMessage message, Action action, Agent agent) {
		ACLMessage reply = message.createReply();
		reply.setPerformative(performatice);


		ContentElementList cel = new ContentElementList();
		cel.add(action);

		try {
			agent.getContentManager().fillContent(reply, cel);
		} catch (CodecException | OntologyException e) {
			e.printStackTrace();
		}

		agent.send(reply);
	}
        
       /*private synchronized void processStudentData(ACLMessage message, Action action, Agent agent) {
		StudentCourseAction studentCourseAction = (StudentCourseAction) action.getAction();
		Student student = studentCourseAction.getStudent();
                List<Integer> studentSubList = new ArrayList<>();
                studentSubList.add(student.getSubject1());
                studentSubList.add(student.getSubject2());
                studentSubList.add(student.getSubject3());
                boolean flag = false;
                //check for subject combination
                boolean overalMach = false;
                FIRSTFOR:for(ArrayList<SubjectCombination> combinationList : courseList){
                    int count = 0;

                    for(SubjectCombination subjectCom : combinationList) {
                        if(subjectCom.isContainAllSubjects(studentSubList)){
                            count++;
                        } 
                        if(count == combinationList.size()){
                            overalMach = true;
                            break FIRSTFOR;
                        }
                    }
                }
                
                //student subjects are same. it should be omited
                boolean isSubjectsAreNotSame = true;
                if(student.getSubject1() == student.getSubject2() || student.getSubject1() == student.getSubject3()
                        || student.getSubject2() == student.getSubject3())
                    isSubjectsAreNotSame = false;
                
                //check schema is correct
                if((schemaId == 0 || student.getSchemeId() == 0 || student.getSchemeId() == schemaId) && overalMach && isSubjectsAreNotSame){
                    //check district z-score is near
                    if(student.getzScore()== 0.0 || districtZScoreMap.get(student.getDistrictId()) == null || (districtZScoreMap.get(student.getDistrictId())) < student.getzScore()){
                        //check requred O/L english results
                        if(isEnglishComp == 0 || "A".equals(student.getoLEnglish()) || "B".equals(student.getoLEnglish()) || "C".equals(student.getoLEnglish())){
                            if(isMathsComp == 0 || "A".equals(student.getoLMaths()) || "B".equals(student.getoLMaths()) || "C".equals(student.getoLMaths())){
                                flag = true;
                            }
                        }
                        
                    }
                    
                }
                
		if(flag){
                                Course c = studentCourseAction.getCourse();
                                c.setCourseName(courseName);
                                c.setId(courseId);
                                c.setUniversities(offeredUniversities);
                                c.setProposedIntake(proposedIntake);
                                if(student.getDistrictId() > 0 && districtZScoreMap.get(student.getDistrictId()) != null)
                                    c.setzScore(districtZScoreMap.get(student.getDistrictId()));
                                if(isMathsComp == 1){
                                    c.setOlMaths("C");
                                } else {
                                    c.setOlMaths("N/A");
                                }    
                                if(isEnglishComp == 1){
                                    c.setOlEnglish("C");
                                } else {
                                    c.setOlEnglish("N/A");
                                }
                                sendReplyMessage(ACLMessage.INFORM, message, action, agent);
		} else {
			sendReplyMessage(ACLMessage.REFUSE, message, action, agent);
		}
	}*/
}
