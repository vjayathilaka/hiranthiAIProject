/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package courseSelection.ontology;

import jade.content.AgentAction;
import java.util.List;

/**
 *
 * @author Hiranthi
 */
public class RecomondationAction implements AgentAction{
    
    private int courseId;
    private List recomondations;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List getRecomondations() {
        return recomondations;
    }

    public void setRecomondations(List recomondations) {
        this.recomondations = recomondations;
    }
    
    
    
}
