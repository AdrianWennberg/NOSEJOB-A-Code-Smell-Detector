package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.VariableData;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestDataStructureHelpers {

	@Test
	public void testCountPrimitives() {
		ArrayList<VariableData> variables = new ArrayList<>();

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
