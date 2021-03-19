package com.lmalvarez.demo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.demo.model.Student;
import com.lmalvarez.demo.model.Subject;
import com.lmalvarez.demo.repository.StudentRepository;
import com.lmalvarez.demo.repository.SubjectRepository;

@Service
public class SubjectService {
	private final SubjectRepository subjectRepository;
	private final StudentService studentService;

	@Autowired
	public SubjectService(SubjectRepository subjectRepository, StudentService studentService) {
		super();
		this.subjectRepository = subjectRepository;
		this.studentService = studentService;
	}
	
	public List<Subject> getSubjects() {
		return subjectRepository.findAll();
	}
	
	public Subject getSubjectById(Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new IllegalStateException("Subject with id " + subjectId + " does not exists"));
		return subject;
	}

	public void registerNewSubject(Subject subject) {
		subjectRepository.save(subject);
	}
	
	public void deleteSubject(Long subjectId) {
		boolean exists = subjectRepository.existsById(subjectId);
		if (!exists) {
			throw new IllegalStateException("Subject with id " + subjectId + " does not exists");
		}
		subjectRepository.deleteById(subjectId);

	}
	
	@Transactional
	public void updateSubject(Long subjectId, String name) {
		Subject subject = getSubjectById(subjectId);

		if (name != null && name.length() > 0 && !Objects.equals(subject.getName(), name)) {
			subject.setName(name);
		}
	}
	
	@Transactional
	public void enrollStudentToSubject(Long subjectId, Long studentId) {
		Subject subject = getSubjectById(subjectId);
		Student student = studentService.getStudentById(studentId);
		subject.enrollStudent(student);
	}
}
