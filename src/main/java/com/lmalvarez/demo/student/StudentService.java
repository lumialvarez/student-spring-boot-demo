package com.lmalvarez.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.demo.exception.ConflictException;
import com.lmalvarez.demo.exception.NotFoundException;
import com.lmalvarez.demo.subject.Subject;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentService() {
		super();
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Studentd with id " + studentId + " does not exists"));
		return student;
	}

	public void registerNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new ConflictException("Email "+ student.getEmail() +" already exists");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new NotFoundException("Studentd with id " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);

	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = getStudentById(studentId);

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new ConflictException("Email "+ email +" already exists");
			}
			student.setEmail(email);
		}

	}
}
