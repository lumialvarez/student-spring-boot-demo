package com.lmalvarez.demo.subject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.demo.exception.NotFoundException;
import com.lmalvarez.demo.student.Student;
import com.lmalvarez.demo.student.StudentRepository;
import com.lmalvarez.demo.student.StudentService;
import com.lmalvarez.demo.teacher.Teacher;
import com.lmalvarez.demo.teacher.TeacherService;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherservice;

	public SubjectService() {
		super();
	}
	
	public List<Subject> getSubjects() {
		return subjectRepository.findAll();
	}
	
	public Subject getSubjectById(Long subjectId) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new NotFoundException("Subject with id " + subjectId + " does not exists"));
		return subject;
	}

	public void registerNewSubject(Subject subject) {
		subjectRepository.save(subject);
	}
	
	public void deleteSubject(Long subjectId) {
		boolean exists = subjectRepository.existsById(subjectId);
		if (!exists) {
			throw new NotFoundException("Subject with id " + subjectId + " does not exists");
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
	
	@Transactional
	public void assignTeacherToSubject(Long subjectId, Long teacherId) {
		Subject subject = getSubjectById(subjectId);
		Teacher teacher = teacherservice.getTeacherById(teacherId);
		subject.setTeacher(teacher);
	}
}
