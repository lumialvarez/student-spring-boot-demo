package com.lmalvarez.demo.teacher;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmalvarez.demo.exception.NotFoundException;


@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	public TeacherService() {
		super();
	}
	
	public List<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}
	
	public Teacher getTeacherById(Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " does not exists"));
		return teacher;
	}
	
	public void registerNewTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}
	
	public void deleteTeacher(Long teacherId) {
		boolean exists = teacherRepository.existsById(teacherId);
		if (!exists) {
			throw new NotFoundException("Teacher with id " + teacherId + " does not exists");
		}
		teacherRepository.deleteById(teacherId);

	}
	
	@Transactional
	public void updateTeacher(Long teacherId, String name) {
		Teacher teacher = getTeacherById(teacherId);

		if (name != null && name.length() > 0 && !Objects.equals(teacher.getName(), name)) {
			teacher.setName(name);
		}
	}
}
