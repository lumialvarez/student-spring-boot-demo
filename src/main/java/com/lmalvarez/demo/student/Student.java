package com.lmalvarez.demo.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmalvarez.demo.subject.Subject;

@Entity(name = "Student")
@Table(name = "student",
	uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = { "email" }) })
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be a valid email")
	@Column(name = "email", nullable = false, columnDefinition = "TEXT")
	private String email;
	@NotNull(message = "dob (Date of birth) is mandatory")
	@Past(message = "dob must be a past date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birth_date", nullable = false, columnDefinition = "date")
	private LocalDate dob;
	@Transient
	private Integer age;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "enrolledStudents")
	private Set<Subject> subjects = new HashSet<>();

	public Student() {
		super();
	}

	public Student(Long id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age
				+ ", subjects=" + subjects + "]";
	}

}
