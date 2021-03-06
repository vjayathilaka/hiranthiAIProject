package courseSelection.ontology;

import java.util.List;

import jade.content.AgentAction;
import jade.content.Concept;

public class StudentCourseAction implements AgentAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
	private Course course;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
