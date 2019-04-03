package com.codingrangers.nosejob.helpers;

import com.codingrangers.nosejob.models.IVariableData;

public class DataStructureHelpers {
	public static int countPrimitives(Iterable<IVariableData> variables) {
		int primitiveCount = 0;
		for (IVariableData var : variables) {
			if (var.isPrimitive())
				primitiveCount++;
		}
		return primitiveCount;
	}
}
