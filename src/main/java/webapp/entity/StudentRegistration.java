package webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class StudentRegistration {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	
	@Column(name="course_id")
	private int course_id;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="semester")
	private int Semester;
	
	@Column(name="registration_year")
	private int Registration_Year;
	
	@Column(name="exam_grade")
	private double exam_grade;
	
	@Column(name="project_grade")
	private double project_grade;
	
	@Column(name="overall_grade")
	private double overall_grade;
	
		
	// define constructors
	
	public StudentRegistration() {
		
	}
	
	public StudentRegistration(int ID, int course_id, String Name, int Semester, int Registration_Year, double exam_grade, double project_grade, double overall_grade) {
		this.ID = ID;
		this.course_id = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
		this.exam_grade = exam_grade;
		this.project_grade = project_grade;
		this.overall_grade = overall_grade;
	}


	public StudentRegistration(int course_id, String Name, int Semester, int Registration_Year, double exam_grade, double project_grade) {
		this.course_id = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
		this.exam_grade = exam_grade;
		this.project_grade = project_grade;

	}
	
	// for enrolling the student
	public StudentRegistration(int course_id, String Name, int Semester, int Registration_Year) {
		this.course_id = course_id;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;

	}
	// define getter/setter
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}
	
	public int getCourseID() {
		return course_id;
	}

	public void setCourseID(int course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getSemester() {
		return Semester;
	}
	
	public void setSemester(int Semester) {
		this.Semester = Semester;
	}
	
	public int getRegistration_Year() {
		return Registration_Year;
	}

	public void setRegistration_Year(int Registration_Year) {
		this.Registration_Year = Registration_Year;
	}
	
	public double getExamGrade() {
		return exam_grade;
	}

	public void setExamGrade(double exam_grade) {
		this.exam_grade = exam_grade;
	}
	
	public double getProjectGrade() {
		return project_grade;
	}

	public void setProjectGrade(double project_grade) {
		this.project_grade = project_grade;
	}
	
	public double getOverallGrade() {
		return overall_grade;
	}

	public void setOverallGrade(double overall_grade) {
		this.overall_grade = overall_grade;
	}
	
	// define tostring

	@Override
	public String toString() {
		return "Student [id=" + ID + ", course id=" + course_id +", Name=" + Name + ", Semester=" + Semester + ", Registration Year=" + Registration_Year + ", exam grade="+ exam_grade +", project grade="+ project_grade +", overall grade="+ overall_grade + "]";
	}
		
}
