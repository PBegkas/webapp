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
	
	@Column(name="name")
	private String Name;
	
	@Column(name="semester")
	private int Semester;
	
	@Column(name="Registration year")
	private int Registration_Year;
	
		
	// define constructors
	
	public StudentRegistration() {
		
	}
	
	public StudentRegistration(int ID, String Name, int Semester, int Registration_Year) {
		this.ID = ID;
		this.Name = Name;
		this.Semester = Semester;
		this.Registration_Year = Registration_Year;
	}


	public StudentRegistration(String Name, int Semester, int Registration_Year) {
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
	// define tostring

	@Override
	public String toString() {
		return "Employee [id=" + ID + ", Name=" + Name + ", Semester=" + Semester + ", Registration Year=" + Registration_Year +"]";
	}
		
}
