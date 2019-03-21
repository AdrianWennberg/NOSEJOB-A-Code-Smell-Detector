package nosejob.smells;

import nosejob.data.CodeData;

public interface SmellInstance {
	CodeData getLocation();
	int getSmellSeverity();
}

