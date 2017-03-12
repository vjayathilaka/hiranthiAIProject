/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package courseSelection.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.CFReflectiveIntrospector;
import jade.content.onto.Ontology;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

/**
 *
 * @author Hiranthi
 */
public class RecomondationOntology extends Ontology{
    private static final String NAME = "recomondation-ontology";
    private static Ontology theInstance = new RecomondationOntology();
    
    private static final String RECOMONDATION = "Recomondation";
    private static final String RECOMONDATION_ACTION = "RecomondationAction";
    
    public static Ontology getInstance() {
        return theInstance;
    }
    
    private RecomondationOntology() {
        super(NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());
        
        try {
            add(new ConceptSchema(RECOMONDATION), Recomondation.class);
            add(new AgentActionSchema(RECOMONDATION_ACTION), RecomondationAction.class);
            
            ConceptSchema cs = (ConceptSchema)getSchema(RECOMONDATION);
            cs.add("jobTitle", (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            cs.add("salary", (PrimitiveSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            cs.add("company", (PrimitiveSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
            
            AgentActionSchema as = (AgentActionSchema)getSchema(RECOMONDATION_ACTION);
            as.add("courseId", (PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            as.add("recomondations",(ConceptSchema)getSchema(RECOMONDATION),0,ObjectSchema.UNLIMITED,BasicOntology.SET, ObjectSchema.OPTIONAL);
            ///cs.add("recomondation", (ConceptSchema) getSchema(RECOMONDATION));
            //as.add("stuAge", (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
            //as.add("recomondation", (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
