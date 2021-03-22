package com.lmalvarez.demo.subject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.lmalvarez.demo.student.Student;
import com.lmalvarez.demo.teacher.Teacher;

@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {
	@Id
	@SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;
	@ManyToMany
	@JoinTable(
			name = "student_enrolled", 
			joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), 
			foreignKey = @ForeignKey(name = "FK_subject_to_student_enrolled"), 
			inverseForeignKey =  @ForeignKey(name = "FK_student_to_student_enrolled")
	)
	private Set<Student> enrolledStudents = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_subject_to_teacher"))
	private Teacher teacher;

	public Subject() {
		super();
	}

	public Subject(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void enrollStudent(Student student) {
		enrolledStudents.add(student);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", enrolledStudents=" + enrolledStudents + "]";
	}

}
