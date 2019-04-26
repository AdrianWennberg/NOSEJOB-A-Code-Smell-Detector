package com.codingrangers.nosejob.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

	private String location;

	public String getLocation() {
		Path unixPath = Paths.get("nosejob", "src", "main", "resources", "codestore");
		Path windowsPath = Paths.get("src", "main", "resources", "codestore");
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
