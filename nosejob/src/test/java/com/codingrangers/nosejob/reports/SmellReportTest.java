package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.Smell;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class SmellReportTest {

	public static class GetSmellNameTest {
		@Test
		public void getSmellNameTest() {
			String smellName = "test";

			SmellReport report = new SmellReport();
			report.setSmellName(smellName);

			assertEquals(smellName, report.getSmellName());
		}
	}

	public static class canAddSmellsTest {
		@Test
		public void addingNonSmellySmellTest() {
			Smell mockedSmell = mock(Smell.class);

			when(mockedSmell.isSmelly()).thenReturn(false);

			SmellReport report = new SmellReport();

			report.addSmell(mockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(0f, smells.size(), 0.01);
			assertEquals(0f, report.getTotalSmellSeverity(), 0.01);
		}

		@Test
		public void canAddSmellTest() {
			Smell mockedSmell = mock(Smell.class);

			when(mockedSmell.isSmelly()).thenReturn(true);

			SmellReport report = new SmellReport();

			report.addSmell(mockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(1, smells.size());
		}
	}

	public static class canAddAllSmellsTest {
		@Test
		public void addingNonSmellySmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(false);
			when(secondMockedSmell.isSmelly()).thenReturn(false);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(0, smells.size());
		}

		@Test
		public void canAddSmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(true);
			when(secondMockedSmell.isSmelly()).thenReturn(true);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(2, smells.size());
			assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell));
		}

		@Test
		public void canAddMixedSmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(true);
			when(secondMockedSmell.isSmelly()).thenReturn(false);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(1, smells.size());
			assertThat(smells, containsInAnyOrder(firstMockedSmell));
		}
	}

	public static class getSmellsTest {
		@Test
		public void getAllSmellySmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);
			Smell thirdMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(true);
			when(secondMockedSmell.isSmelly()).thenReturn(true);
			when(thirdMockedSmell.isSmelly()).thenReturn(true);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);
			report.addSmell(thirdMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(3, smells.size());
			assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell, thirdMockedSmell));
		}

		@Test
		public void getNoSmellySmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);
			Smell thirdMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(false);
			when(secondMockedSmell.isSmelly()).thenReturn(false);
			when(thirdMockedSmell.isSmelly()).thenReturn(false);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);
			report.addSmell(thirdMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(0, smells.size());
		}

		@Test
		public void getMixedSmellsTest() {
			Smell firstMockedSmell = mock(Smell.class);
			Smell secondMockedSmell = mock(Smell.class);
			Smell thirdMockedSmell = mock(Smell.class);

			when(firstMockedSmell.isSmelly()).thenReturn(true);
			when(secondMockedSmell.isSmelly()).thenReturn(true);
			when(thirdMockedSmell.isSmelly()).thenReturn(false);

			SmellReport report = new SmellReport();

			report.addSmell(firstMockedSmell);
			report.addSmell(secondMockedSmell);
			report.addSmell(thirdMockedSmell);

			List<Smell> smells = report.getSmells();

			assertEquals(2, smells.size());
			assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell));
		}
	}

	public static class getTotalSmellSeverityTest {
		@Parameterized.Parameter()
		public float average;
		@Parameterized.Parameter(1)
		public List<Smell> testSmells = new ArrayList<>();

		@Parameterized.Parameters
		public static Collection<Object[]> testData() {
			Smell one = mock(Smell.class);
			Smell two = mock(Smell.class);
			Smell three = mock(Smell.class);

			when(one.getSmellSeverity()).thenReturn(0f);
			when(two.getSmellSeverity()).thenReturn(0f);
			when(three.getSmellSeverity()).thenReturn(1f);

			List<Smell> firstList = new ArrayList<>();
			firstList.add(one);
			firstList.add(two);
			firstList.add(three);

			Smell four = mock(Smell.class);
			Smell five = mock(Smell.class);
			Smell six = mock(Smell.class);

			when(four.getSmellSeverity()).thenReturn(1f);
			when(five.getSmellSeverity()).thenReturn(0f);
			when(six.getSmellSeverity()).thenReturn(0.5f);

			List<Smell> secondList = new ArrayList<>();
			secondList.add(four);
			secondList.add(five);
			secondList.add(six);

			Smell seven = mock(Smell.class);
			Smell eight = mock(Smell.class);
			Smell nine = mock(Smell.class);

			when(seven.getSmellSeverity()).thenReturn(0.25f);
			when(eight.getSmellSeverity()).thenReturn(0.5f);
			when(nine.getSmellSeverity()).thenReturn(0.75f);

			List<Smell> thirdList = new ArrayList<>();
			thirdList.add(seven);
			thirdList.add(eight);
			thirdList.add(nine);

			Smell ten = mock(Smell.class);
			Smell eleven = mock(Smell.class);
			Smell twelve = mock(Smell.class);

			when(ten.getSmellSeverity()).thenReturn(0.33f);
			when(eleven.getSmellSeverity()).thenReturn(0.33f);
			when(twelve.getSmellSeverity()).thenReturn(0.16f);

			List<Smell> fourthList = new ArrayList<>();
			fourthList.add(ten);
			fourthList.add(eleven);
			fourthList.add(twelve);

			Smell thirteen = mock(Smell.class);
			Smell fourteen = mock(Smell.class);
			Smell fifteen = mock(Smell.class);

			when(thirteen.getSmellSeverity()).thenReturn(0.10f);
			when(fourteen.getSmellSeverity()).thenReturn(0.20f);
			when(fifteen.getSmellSeverity()).thenReturn(0f);

			List<Smell> fifthList = new ArrayList<>();
			fifthList.add(thirteen);
			fifthList.add(fourteen);
			fifthList.add(fifteen);

			Object[][] data = new Object[][] { { 0.33f, firstList }, { 0.5f, secondList }, { 0.5f, thirdList },
					{ 0.273f, fourthList }, { 0.15f, fifthList } };
			return Arrays.asList(data);
		}

		@Test
		public void parametrizedGetTotalSmellSeverityTest() {
			SmellReport report = new SmellReport();
			report.addSmells(testSmells);
			assertEquals(average, report.getTotalSmellSeverity(), 0.01);
		}
	}
}
