package nosejob.smells;

import java.util.List;

public interface ProjectReport {
	int getProjectScore();
	List<String> getSmellNames();
	SmellReport getSmellReport(String smellName);
}

