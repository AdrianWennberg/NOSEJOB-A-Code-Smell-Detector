/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Configuration
@ConfigurationProperties("storage")
public class StorageProperties {

	private String location;

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void resolveLocation() {
		Path unixPath = Paths.get("nosejob", "src", "main", "resources", "codestore");
		Path windowsPath = Paths.get("src", "main", "resources", "codestore");
		if (Files.exists(unixPath)) {
			String path = unixPath.toString().concat("/").concat(getRandomString());
			new File(path).mkdir();
			this.location = path;
		} else if (Files.exists(windowsPath)) {
			String path = windowsPath.toString().concat("/").concat(getRandomString());
			new File(path).mkdir();
			this.location = path;
		}
	}

	private String getRandomString() {
		int lowerAsciiBound = 97;
		int upperAscciBound = 122;
		Random random = new Random();
		StringBuilder sb = new StringBuilder(10);
		for (int i = 0; i < 10; i++) {
			int randomLimitedInt = lowerAsciiBound
					+ (int) (random.nextFloat() * (upperAscciBound - lowerAsciiBound + 1));
			sb.append((char) randomLimitedInt);
		}
		return sb.toString();
	}

}
