package com.lmalvarez.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {
	@Id
	@SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;
	@ManyToMany
	@JoinTable(name = "student_enrolled", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "FK_subject_student_enrolled"))
	private Set<Student> enrolledStudents = new HashSet<>();

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

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", enrolledStudents=" + enrolledStudents + "]";
	}

}
