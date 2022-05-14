package webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int ID;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="syllabus")
	private String Syllabus;
	
	@Column(name="academic_year")
	private String Academic_year;
	
	@Column(name="semester")
	private int Semester;
	
	@Column(name="description")
	private String Description;
	
		
	// define constructors
	
	public Course() {
		
	}
	
	public Course(int ID, String Name, String Syllabus, String Academic_year, int Semester, String Description) {
		this.ID = ID;
		this.Name = Name;
		this.Syllabus = Syllabus;
		this.Academic_year = Academic_year;
		this.Semester = Semester;
		this.Description = Description;
	}


	public Course(String Name, String Syllabus, String Academic_year, int Semester, String Description) {
		this.Name = Name;
		this.Syllabus = Syllabus;
		this.Academic_year = Academic_year;
		this.Semester = Semester;
		this.Description = Description;
	}

	// define getter/setter
	
	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getSyllabus() {
		return Syllabus;
	}

	public void setSyllabus(String Syllabus) {
		this.Syllabus = Syllabus;
	}

	public String getAcademic_year() {
		return Academic_year;
	}

	public void setAcademic_year(String Academic_year) {
		this.Academic_year = Academic_year;
	}

	public int getSemester() {
		return Semester;
	}
	
	public void setSemester(int Semester) {
		this.Semester = Semester;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}
	// define tostring

	@Override
	public String toString() {
		return "Employee [id=" + ID + ", Name=" + Name + ", Syllabus=" + Syllabus + ", Academic year=" + Academic_year + ", Semester=" + Semester + ", Description=" + Description +"]";
	}
		
}
