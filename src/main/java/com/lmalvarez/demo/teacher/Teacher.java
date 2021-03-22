package com.lmalvarez.demo.teacher;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lmalvarez.demo.subject.Subject;

@Entity(name = "Teacher")
@Table(name = "teacher")
public class Teacher {
	@Id
	@SequenceGenerator(name = "teacher_sequence", sequenceName = "teacher_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
	@Column(name = "id", updatable = false)
	private Long id;
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name", nullable = false, columnDefinition = "TEXT")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "teacher")
	private Set<Subject> subjects = new HashSet<>();

	public Teacher() {
		super();
	}

	public Teacher(String name) {
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

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

}
