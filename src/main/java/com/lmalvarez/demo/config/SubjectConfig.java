package com.lmalvarez.demo.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lmalvarez.demo.model.Subject;
import com.lmalvarez.demo.repository.SubjectRepository;

@Configuration
public class SubjectConfig {
	@Bean
	CommandLineRunner commandLineRunnerSubject(SubjectRepository subjectRepository) {
		return args -> {
			Subject math = new Subject("Math");

			subjectRepository.saveAll(List.of(math));
		};
	}
}
