package com.lmalvarez.demo.teacher;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "api/v1/teacher")
public class TeacherController {
	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@GetMapping
	public List<Teacher> getSubjects() {
		return teacherService.getTeachers();
	}
	
	@GetMapping(path = "{teacherId}")
	public Teacher getSubjectById(@PathVariable("teacherId") Long teacherId) {
		return teacherService.getTeacherById(teacherId);
	}

	@PostMapping
	public void registerNewTeacher(@Valid @RequestBody Teacher teacher) {
		teacherService.registerNewTeacher(teacher);
	}

	@DeleteMapping(path = "{teacherId}")
	public void deleteTeacher(@PathVariable("teacherId") Long teacherId) {
		teacherService.deleteTeacher(teacherId);
	}

	@PutMapping(path = "{teacherId}")
	public void updateTeacher(@PathVariable("teacherId") Long teacherId, @RequestParam(required = false) String name) {
		teacherService.updateTeacher(teacherId, name);
	}
}
