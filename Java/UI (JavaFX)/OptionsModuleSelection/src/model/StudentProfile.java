package model;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class StudentProfile {

	private String pNumber;
	private Name studentName;
	private String email;
	private LocalDate date;
	private Course course;
	private Set<Module> selectedModules;
	private Set<Module> reservedModules;

	public StudentProfile() {
		pNumber = "";
		studentName = new Name();
		email = "";
		date = null;
		course = null;
		selectedModules = new TreeSet<Module>();
		reservedModules = new TreeSet<Module>();
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	public Name getStudentName() {
		return studentName;
	}

	public void setStudentName(Name studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getSubmissionDate() {
		return date;
	}

	public void setSubmissionDate(LocalDate date) {
		this.date = date;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean addSelectedModule(Module m) {
		return selectedModules.add(m);
	}

	public Set<Module> getAllSelectedModules() {
		return selectedModules;
	}

	public void clearSelectedModules() {
		selectedModules.clear();
	}

	public boolean addReservedModule(Module m) {
		return reservedModules.add(m);
	}

	public Set<Module> getAllReservedModules() {
		return reservedModules;
	}

	public void clearReservedModules() {
		selectedModules.clear();
	}

	@Override
	public String toString() {
		return "StudentProfile:[pNumber=" + pNumber + ", studentName=" + studentName + ", email=" + email + ", date="
				+ date + ", course=" + course.actualToString() + ", selectedModules=" + selectedModules
				+ ", reservedModules=" + reservedModules + "]";
	}

}
