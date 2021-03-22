package com.lmalvarez.demo.subject;

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
@RequestMapping(path = "api/v1/subject")
public class SubjectController {
	private final SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService subjectService) {
		super();
		this.subjectService = subjectService;
	}

	@GetMapping
	public List<Subject> getSubjects() {
		return subjectService.getSubjects();
	}
	
	@GetMapping(path = "{subjectId}")
	public Subject getSubjectById(@PathVariable("subjectId") Long subjectId) {
		return subjectService.getSubjectById(subjectId);
	}

	@PostMapping
	public void registerNewSubject(@Valid @RequestBody Subject subject) {
		subjectService.registerNewSubject(subject);
	}

	@DeleteMapping(path = "{subjectId}")
	public void deleteSubject(@PathVariable("subjectId") Long subjectId) {
		subjectService.deleteSubject(subjectId);
	}

	@PutMapping(path = "{subjectId}")
	public void updateSubject(@PathVariable("subjectId") Long subjectId, @RequestParam(required = false) String name) {
		subjectService.updateSubject(subjectId, name);
	}
	
	@PutMapping(path = "{subjectId}/student/{studentId}")
	public void enrollStudentToSubject(@PathVariable("subjectId") Long subjectId, @PathVariable("studentId") Long studentId) {
		subjectService.enrollStudentToSubject(subjectId, studentId);
	}
	
	@PutMapping(path = "{subjectId}/teacher/{teacherId}")
	public void assignTeacherToSubject(@PathVariable("subjectId") Long subjectId, @PathVariable("teacherId") Long teacherId) {
		subjectService.assignTeacherToSubject(subjectId, teacherId);
	}
}
