package courseSelection.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.CFReflectiveIntrospector;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;

public class CourseSelectionOntology extends Ontology{
	
	public static final String ONTOLOGY_NAME = "CouseSelection-ontology";
	
	public static final String STUDENT = "Student";
	public static final String STUDENT_NAME = "name";
	public static final String STUDENT_ZSCORE = "zScore";
	public static final String STUDENT_DISTRICT_ID = "districtId";
	public static final String STUDENT_SCHEME_ID = "schemeId";
	
	public static final String COURSE = "Course";
	public static final String ID = "id";
	public static final String COURSE_NAME = "courseName";
	public static final String COURSE_ZSCORE = "zScore";
	public static final String COURSE_ZSCORE_DIFF = "zScoreDiff";
	
	public static final String STUDENT_COURSE = "StudentCourseAction";
	public static final String STUDENT_COURSE_STUDENT = "student";
	public static final String STUDENT_COURSE_COURSE = "course";
	
	// The singleton instance of this ontology
	private static Ontology theInstance = new CourseSelectionOntology();
	// Retrieve the singleton Book-trading ontology instance
	public static Ontology getInstance() {
		return theInstance;
	}

	
	private CourseSelectionOntology() {
		// The Book-trading ontology extends the basic ontology
		super(ONTOLOGY_NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());
		try {
			add(new ConceptSchema(STUDENT), Student.class);
			add(new ConceptSchema(COURSE), Course.class);
			add(new AgentActionSchema(STUDENT_COURSE), StudentCourseAction.class);
			
			// Structure of the schema for the Book concept
			ConceptSchema cs = (ConceptSchema) getSchema(STUDENT);
	    	cs.add(STUDENT_NAME, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
	    	cs.add(STUDENT_ZSCORE, (PrimitiveSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
	    	cs.add(STUDENT_DISTRICT_ID, (PrimitiveSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
	    	cs.add(STUDENT_SCHEME_ID, (PrimitiveSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
	    	
	    	cs = (ConceptSchema)getSchema(COURSE);
	    	cs.add(ID, (PrimitiveSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
	    	cs.add(COURSE_NAME, (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
	    	cs.add(COURSE_ZSCORE, (PrimitiveSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
	    	cs.add(COURSE_ZSCORE_DIFF, (PrimitiveSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
	    	
			AgentActionSchema as = (AgentActionSchema)getSchema(STUDENT_COURSE);
			as.add(STUDENT_COURSE_STUDENT, (ConceptSchema)getSchema(STUDENT));
			as.add(STUDENT_COURSE_COURSE, (ConceptSchema)getSchema(COURSE)); 	

		} catch (OntologyException oe) {
			oe.printStackTrace();
		}
	}

}
