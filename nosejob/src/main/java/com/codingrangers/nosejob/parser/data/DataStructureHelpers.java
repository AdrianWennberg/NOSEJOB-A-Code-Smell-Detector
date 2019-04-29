/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.VariableData;

class DataStructureHelpers {
	static int countPrimitives(Iterable<VariableData> variables) {
		int primitiveCount = 0;
		for (VariableData var : variables) {
			if (var.isPrimitive())
				primitiveCount++;
		}
		return primitiveCount;
	}
}
