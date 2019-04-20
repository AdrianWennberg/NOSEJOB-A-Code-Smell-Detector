package com.codingrangers.nosejob.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.nio.file.Path;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

	private String location;

	public String getLocation() {
		Path path = Path.of("classpath:codestore");
		this.location = path.toString();
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
