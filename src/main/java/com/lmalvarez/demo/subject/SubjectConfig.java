package com.lmalvarez.demo.subject;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
