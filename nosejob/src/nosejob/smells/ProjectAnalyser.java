package nosejob.smells;

import nosejob.data.ProjectData;

public interface ProjectAnalyser {
	ProjectReport analyzeCode(ProjectData data);
}


