package nosejob.data;

import java.util.List;

public interface ClassData extends CodeData {
	List<String> getMethodNames();
	MethodData getMethod(String name);
	List<String> getFieldsNames();
	VariableData getField(String name);
}

