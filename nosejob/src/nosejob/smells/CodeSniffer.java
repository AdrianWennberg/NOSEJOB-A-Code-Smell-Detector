package nosejob.smells;

import nosejob.data.ProjectData;

public interface CodeSniffer {
	SmellReport analyzeCode(ProjectData data);
}


