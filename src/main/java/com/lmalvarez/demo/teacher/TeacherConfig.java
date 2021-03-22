package com.lmalvarez.demo.teacher;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherConfig {
	@Bean
	CommandLineRunner commandLineRunner(TeacherRepository teacherRepository) {
		return args -> {
			Teacher cuchi = new Teacher("Cuchilla");

			teacherRepository.saveAll(List.of(cuchi));
		};
	}
}
