package com.codingrangers.nosejob.models.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

public class TestDataStructureHelpers {

	@Test
	public void testCountPrimitives() {
		ArrayList<VariableData> variables = new ArrayList<VariableData>();

		assertEquals(0, DataStructureHelpers.countPrimitives(variables));

		VariableData testVariable = mock(VariableData.class);
		when(testVariable.isPrimitive()).thenReturn(true);
		variables.add(testVariable);

		assertEquals(1, DataStructureHelpers.countPrimitives(variables));

		testVariable = mock(VariableData.class);
		when(testVariable.isPrimitive()).thenReturn(true);
		variables.add(testVariable);

		assertEquals(2, DataStructureHelpers.countPrimitives(variables));

		testVariable = mock(VariableData.class);
		when(testVariable.isPrimitive()).thenReturn(false);
		variables.add(testVariable);

		assertEquals(2, DataStructureHelpers.countPrimitives(variables));
	}

}
