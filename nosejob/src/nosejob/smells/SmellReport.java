package nosejob.smells;

import java.util.List;

public interface SmellReport {
	String getSmellName();
	int getTotalSmellSeverity();
	List<SmellInstance> getSmellInstances();
}


