package com.codingrangers.nosejob.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

	private String location;

	public String getLocation() {
		Path unixPath = Paths.get("nosejob", "src", "codestore");
		Path windowsPath = Paths.get("src", "codestore");
		if (Files.exists(unixPath)) {
			this.location = unixPath.toString();
		} else if (Files.exists(windowsPath)) {
			this.location = windowsPath.toString();
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
