package com.codingrangers.nosejob.helpers;

import com.codingrangers.nosejob.models.VariableData;

public class DataStructureHelpers {
	public static int countPrimitives(Iterable<VariableData> variables) {
		int primitiveCount = 0;
		for (VariableData var : variables) {
			if (var.isPrimitive())
				primitiveCount++;
		}
		return primitiveCount;
	}
}
