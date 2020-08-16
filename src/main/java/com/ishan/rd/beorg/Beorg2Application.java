package com.ishan.rd.beorg;

import com.ishan.rd.beorg.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class Beorg2Application  {

	public static void main(String[] args) {
		SpringApplication.run(Beorg2Application.class, args);
	}

}
