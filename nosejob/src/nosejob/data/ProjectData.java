package nosejob.data;

import java.util.List;

public interface ProjectData {
	List<String> getClassNames();
	ClassData getClassData(String className);
 }



