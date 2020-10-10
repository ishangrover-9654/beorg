package com.ishan.rd.beorg;

import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.ishan.rd.beorg.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableArangoRepositories("com.ishan.rd.beorg.repository.arango")
@EnableMongoRepositories("com.ishan.rd.beorg.repository.mongo")
public class Beorg2Application  {

	public static void main(String[] args) {
		SpringApplication.run(Beorg2Application.class, args);
	}

}
