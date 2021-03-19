package com.lmalvarez.demo.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lmalvarez.demo.model.Student;
import com.lmalvarez.demo.repository.StudentRepository;

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunnerStudent(StudentRepository studentRepository) {
		return args -> {
			Student mariam = new Student("Mariam", "ma@gmail.com", LocalDate.of(2000, Month.JANUARY, 2));
			
			Student alex = new Student("Alex", "al@gmail.com", LocalDate.of(2003, Month.AUGUST, 3));
			
			studentRepository.saveAll(List.of(mariam, alex));
		};
	}
}
