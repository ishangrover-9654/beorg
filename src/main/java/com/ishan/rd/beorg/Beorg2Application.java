package com.ishan.rd.beorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableArangoRepositories(basePackageClasses = MedIssueTagRepository.class)
public class Beorg2Application  {


	/*@Autowired
	MedIssueTagRepository medIssueTagRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(Beorg2Application.class, args);
	}


}
