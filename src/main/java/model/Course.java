package model;

public class Course {
	//instance variables
	private int courseId;
	private String courseCode;
	private String courseName;
    private String lecturer;
    private int credits;
    private int capacity;
    
    //default 
    public Course() {}

    //Constructor
    public Course(int courseId, String courseCode, String courseName, String lecturer, int credits, int capacity) {
    	this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.credits = credits;
        this.capacity = capacity;
    }
    
    //Constructor without ID for overloading
    public Course(String courseCode, String courseName, String lecturer, int credits, int capacity) {
    	this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.credits = credits;
        this.capacity = capacity;
    }
    
    //Getters and Setters
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
    
 
    

}
