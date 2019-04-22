package com.codingrangers.nosejob.models;

import java.io.FileNotFoundException;

public interface CodeParser {
	ProjectData parseProject(String path) throws FileNotFoundException;
}
