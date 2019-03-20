package nosejob.data;

import java.util.List;

public interface MethodData extends CodeData {
	String getClassName();
	String getReturnType();
	boolean hasPrimitiveReturnType();
	List<VariableData> getParameters();
	List<VariableData> getLocalVariables();
	int getPrimitiveParameterCount();
	int getPrimitiveLocalCount();
}

