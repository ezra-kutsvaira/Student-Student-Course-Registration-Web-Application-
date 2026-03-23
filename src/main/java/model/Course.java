package model;

public class Course {
	//instance variables
	private int courseId;
	private String courseCode;
	private String courseName;
    private String lecturer;
    private int credits;
    private int capacity;
    private int enrolledCount;
    
    //default 
    public Course() {}
    
    public Course(int courseId, String courseCode, String courseName, String lecturer, int credits, int capacity) {
    	this(courseId, courseCode, courseName, lecturer, credits, capacity, 0);
    }
    
    //Constructor
    public Course(int courseId, String courseCode, String courseName, String lecturer, int credits, int capacity, int enrolledCount ) {
    	this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolledCount = enrolledCount;
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
    
	public int getEnrolledCount() {
		return enrolledCount;
	}

	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}

	public int getSlotsLeft() {
		return Math.max(capacity - enrolledCount, 0);
	}

	public boolean isFull() {
		return getSlotsLeft() == 0;
	}
    

}
