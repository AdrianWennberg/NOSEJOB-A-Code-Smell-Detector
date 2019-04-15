package com.codingrangers.nosejob.reports;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class GlobalReportTest {
	public static class addSmellReportToProjectReportTest {
		@Test
		public void AddSmellReportTest() {
			String reportName = "firstTestName";
			SmellReport mockedReport = mock(SmellReport.class);
			when(mockedReport.getSmellName()).thenReturn(reportName);

			GlobalReport globalReport = new GlobalReport();

			globalReport.addSmellReportToProjectReport(mockedReport);

			assertEquals(1, globalReport.getSmellReports().size());
			assertEquals(reportName, globalReport.getSmellReports().get(0).getSmellName());
			assertThat(globalReport.getSmellReports(), containsInAnyOrder(mockedReport));
		}
	}

	public static class getProjectScoreTest {
		@Parameterized.Parameter()
		public float average;
		@Parameterized.Parameter(1)
		public List<SmellReport> testSmellReports = new ArrayList<>();

		@Parameterized.Parameters
		public static Collection<Object[]> testData() {
			SmellReport one = mock(SmellReport.class);
			SmellReport two = mock(SmellReport.class);
			SmellReport three = mock(SmellReport.class);

			when(one.getTotalSmellSeverity()).thenReturn(0f);
			when(two.getTotalSmellSeverity()).thenReturn(0f);
			when(three.getTotalSmellSeverity()).thenReturn(1f);

			List<SmellReport> firstList = new ArrayList<>();
			firstList.add(one);
			firstList.add(two);
			firstList.add(three);

			SmellReport four = mock(SmellReport.class);
			SmellReport five = mock(SmellReport.class);
			SmellReport six = mock(SmellReport.class);

			when(four.getTotalSmellSeverity()).thenReturn(1f);
			when(five.getTotalSmellSeverity()).thenReturn(0f);
			when(six.getTotalSmellSeverity()).thenReturn(0.5f);

			List<SmellReport> secondList = new ArrayList<>();
			secondList.add(four);
			secondList.add(five);
			secondList.add(six);

			SmellReport seven = mock(SmellReport.class);
			SmellReport eight = mock(SmellReport.class);
			SmellReport nine = mock(SmellReport.class);

			when(seven.getTotalSmellSeverity()).thenReturn(0.25f);
			when(eight.getTotalSmellSeverity()).thenReturn(0.5f);
			when(nine.getTotalSmellSeverity()).thenReturn(0.75f);

			List<SmellReport> thirdList = new ArrayList<>();
			thirdList.add(seven);
			thirdList.add(eight);
			thirdList.add(nine);

			SmellReport ten = mock(SmellReport.class);
			SmellReport eleven = mock(SmellReport.class);
			SmellReport twelve = mock(SmellReport.class);

			when(ten.getTotalSmellSeverity()).thenReturn(0.33f);
			when(eleven.getTotalSmellSeverity()).thenReturn(0.33f);
			when(twelve.getTotalSmellSeverity()).thenReturn(0.16f);

			List<SmellReport> fourthList = new ArrayList<>();
			fourthList.add(ten);
			fourthList.add(eleven);
			fourthList.add(twelve);

			SmellReport thirteen = mock(SmellReport.class);
			SmellReport fourteen = mock(SmellReport.class);
			SmellReport fifteen = mock(SmellReport.class);

			when(thirteen.getTotalSmellSeverity()).thenReturn(0.10f);
			when(fourteen.getTotalSmellSeverity()).thenReturn(0.20f);
			when(fifteen.getTotalSmellSeverity()).thenReturn(0f);

			List<SmellReport> fifthList = new ArrayList<>();
			fifthList.add(thirteen);
			fifthList.add(fourteen);
			fifthList.add(fifteen);

			Object[][] data = new Object[][] { { 0.33f, firstList }, { 0.5f, secondList }, { 0.5f, thirdList },
					{ 0.273f, fourthList }, { 0.15f, fifthList } };
			return Arrays.asList(data);
		}

		@Test
		public void parametrizedGetTotalSmellSeverityTest() {
			GlobalReport globalReport = new GlobalReport();
			for (SmellReport report : testSmellReports) {
				globalReport.addSmellReportToProjectReport(report);
			}
			assertEquals(average, globalReport.getProjectScore(), 0.01);
		}
	}

	public static class getSmellNamesTest {
		@Test
		public void getSmellNamesTest() {
			String firstReportName = "firstTestName";
			String secondReportName = "secondTestName";
			SmellReport firstMockedReport = mock(SmellReport.class);
			SmellReport secondMockedReport = mock(SmellReport.class);
			when(firstMockedReport.getSmellName()).thenReturn(firstReportName);
			when(secondMockedReport.getSmellName()).thenReturn(secondReportName);

			GlobalReport globalReport = new GlobalReport();

			globalReport.addSmellReportToProjectReport(firstMockedReport);
			globalReport.addSmellReportToProjectReport(secondMockedReport);

			assertEquals(2, globalReport.getSmellNames().size());
			assertEquals(firstReportName, globalReport.getReportForSpecifiedSmell(firstReportName).getSmellName());
			assertThat(globalReport.getSmellNames(),
					containsInAnyOrder(firstMockedReport.getSmellName(), secondMockedReport.getSmellName()));
		}
	}

	public static class getSmellReportsTest {
		@Test
		public void getSmellReportsTest() {
			String firstReportName = "firstTestName";
			String secondReportName = "secondTestName";
			SmellReport firstMockedReport = mock(SmellReport.class);
			SmellReport secondMockedReport = mock(SmellReport.class);
			when(firstMockedReport.getSmellName()).thenReturn(firstReportName);
			when(secondMockedReport.getSmellName()).thenReturn(secondReportName);

			GlobalReport globalReport = new GlobalReport();

			globalReport.addSmellReportToProjectReport(firstMockedReport);
			globalReport.addSmellReportToProjectReport(secondMockedReport);

			assertEquals(2, globalReport.getSmellReports().size());
			assertThat(globalReport.getSmellReports(), containsInAnyOrder(firstMockedReport, secondMockedReport));
		}
	}

	public static class getReportForSpecifiedSmellTest {
		@Test
		public void getReportForSpecifiedSmellTest() {
			String firstReportName = "firstTestName";
			SmellReport firstMockedReport = mock(SmellReport.class);
			when(firstMockedReport.getSmellName()).thenReturn(firstReportName);

			GlobalReport globalReport = new GlobalReport();

			globalReport.addSmellReportToProjectReport(firstMockedReport);

			assertEquals(firstReportName, globalReport.getReportForSpecifiedSmell(firstReportName).getSmellName());
		}
	}
}
