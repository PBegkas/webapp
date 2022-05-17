package webapp.entity;

public class GradeParameters {
	
	// define fields
	
	private double projectWeight;
	
	private double projectBase;
	
	private double examWeight;
	
	private double examBase;
	
	
	//define constructors
	
	public GradeParameters() {
		
	}
	
	public GradeParameters(double ProjectWeight, double ProjectBase, double ExamWeight, double ExamBase) {
		this.projectWeight = ProjectWeight;
		this.projectBase = ProjectBase;
		this.examWeight = ExamWeight;
		this.examBase = ExamBase;
	}
	
	
	// define getter/setter
	
	public double getProjectWeight() {
		return this.projectWeight;
	}
	
	public void setProjectWeight(double ProjectWeight) {
		this.projectWeight = ProjectWeight;
	}
	
	public double getProjectBase() {
		return this.projectBase;
	}
	
	public void setProjectBase(double ProjectBase) {
		this.projectBase = ProjectBase;
	}
	
	public double getExamWeight() {
		return this.examWeight;
	}
	
	public void setExamWeight(double ExamWeight) {
		this.examWeight = ExamWeight;
	}
	
	public double getExamBase() {
		return this.examBase;
	}
	
	public void setExamBase(double ExamBase) {
		this.examBase = ExamBase;
	}
	
	// define toString
	
	@Override
	public String toString() {
		return "Grade parameters [ project weight = " + projectWeight + ", project base = " + projectBase + 
				", exam weight = " + examWeight + ", exam base = " + examBase + " ]"; 
	}

}
