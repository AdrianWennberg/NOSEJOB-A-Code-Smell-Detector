package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.ISmellReport;
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
public class GlobalReportTest {
    public static class addSmellReportToProjectReportTest{
        @Test
        public void AddSmellReportTest(){
            String reportName = "firstTestName";
            ISmellReport mockedReport = mock(ISmellReport.class);
            when(mockedReport.getSmellName()).thenReturn(reportName);

            GlobalReport globalReport = new GlobalReport();

            globalReport.addSmellReportToProjectReport(mockedReport);

            assertEquals(1, globalReport.getSmellReports().size());
            assertEquals(reportName, globalReport.getSmellReports().get(0).getSmellName());
            assertThat(globalReport.getSmellReports(), containsInAnyOrder(mockedReport));
        }
    }

    public static class getProjectScoreTest{
        @Parameterized.Parameter()
        public float average;
        @Parameterized.Parameter(1)
        public List<ISmellReport> testSmellReports = new ArrayList<>();

        @Parameterized.Parameters
        public static Collection<Object[]> testData() {
            ISmellReport one = mock(ISmellReport.class);
            ISmellReport two = mock(ISmellReport.class);
            ISmellReport three = mock(ISmellReport.class);

            when(one.getTotalSmellSeverity()).thenReturn(0f);
            when(two.getTotalSmellSeverity()).thenReturn(0f);
            when(three.getTotalSmellSeverity()).thenReturn(1f);

            List<ISmellReport> firstList = new ArrayList<>();
            firstList.add(one);
            firstList.add(two);
            firstList.add(three);

            ISmellReport four = mock(ISmellReport.class);
            ISmellReport five = mock(ISmellReport.class);
            ISmellReport six = mock(ISmellReport.class);

            when(four.getTotalSmellSeverity()).thenReturn(1f);
            when(five.getTotalSmellSeverity()).thenReturn(0f);
            when(six.getTotalSmellSeverity()).thenReturn(0.5f);

            List<ISmellReport> secondList = new ArrayList<>();
            secondList.add(four);
            secondList.add(five);
            secondList.add(six);

            ISmellReport seven = mock(ISmellReport.class);
            ISmellReport eight = mock(ISmellReport.class);
            ISmellReport nine = mock(ISmellReport.class);

            when(seven.getTotalSmellSeverity()).thenReturn(0.25f);
            when(eight.getTotalSmellSeverity()).thenReturn(0.5f);
            when(nine.getTotalSmellSeverity()).thenReturn(0.75f);

            List<ISmellReport> thirdList = new ArrayList<>();
            thirdList.add(seven);
            thirdList.add(eight);
            thirdList.add(nine);

            ISmellReport ten = mock(ISmellReport.class);
            ISmellReport eleven = mock(ISmellReport.class);
            ISmellReport twelve = mock(ISmellReport.class);

            when(ten.getTotalSmellSeverity()).thenReturn(0.33f);
            when(eleven.getTotalSmellSeverity()).thenReturn(0.33f);
            when(twelve.getTotalSmellSeverity()).thenReturn(0.16f);

            List<ISmellReport> fourthList = new ArrayList<>();
            fourthList.add(ten);
            fourthList.add(eleven);
            fourthList.add(twelve);

            ISmellReport thirteen = mock(ISmellReport.class);
            ISmellReport fourteen = mock(ISmellReport.class);
            ISmellReport fifteen = mock(ISmellReport.class);

            when(thirteen.getTotalSmellSeverity()).thenReturn(0.10f);
            when(fourteen.getTotalSmellSeverity()).thenReturn(0.20f);
            when(fifteen.getTotalSmellSeverity()).thenReturn(0f);

            List<ISmellReport> fifthList = new ArrayList<>();
            fifthList.add(thirteen);
            fifthList.add(fourteen);
            fifthList.add(fifteen);

            Object[][] data = new Object[][]{{0.33f, firstList},
                    {0.5f, secondList},
                    {0.5f, thirdList},
                    {0.273f, fourthList},
                    {0.15f, fifthList}};
            return Arrays.asList(data);
        }
        @Test
        public void parametrizedGetTotalSmellSeverityTest(){
            GlobalReport globalReport = new GlobalReport();
            for (ISmellReport report : testSmellReports) {
                globalReport.addSmellReportToProjectReport(report);
            }
            assertEquals(average, globalReport.getProjectScore(), 0.01);
        }
    }

    public static class getSmellNamesTest{
        @Test
        public void getSmellNamesTest(){
            String firstReportName = "firstTestName";
            String secondReportName = "secondTestName";
            ISmellReport firstMockedReport = mock(ISmellReport.class);
            ISmellReport secondMockedReport = mock(ISmellReport.class);
            when(firstMockedReport.getSmellName()).thenReturn(firstReportName);
            when(secondMockedReport.getSmellName()).thenReturn(secondReportName);

            GlobalReport globalReport = new GlobalReport();

            globalReport.addSmellReportToProjectReport(firstMockedReport);
            globalReport.addSmellReportToProjectReport(secondMockedReport);

            assertEquals(2, globalReport.getSmellNames().size());
            assertEquals(firstReportName, globalReport.getReportForSpecifiedSmell(firstReportName).getSmellName());
            assertThat(globalReport.getSmellNames(), containsInAnyOrder(firstMockedReport.getSmellName(), secondMockedReport.getSmellName()));
        }
    }

    public static class getSmellReportsTest{
        @Test
        public void getSmellReportsTest(){
            String firstReportName = "firstTestName";
            String secondReportName = "secondTestName";
            ISmellReport firstMockedReport = mock(ISmellReport.class);
            ISmellReport secondMockedReport = mock(ISmellReport.class);
            when(firstMockedReport.getSmellName()).thenReturn(firstReportName);
            when(secondMockedReport.getSmellName()).thenReturn(secondReportName);

            GlobalReport globalReport = new GlobalReport();

            globalReport.addSmellReportToProjectReport(firstMockedReport);
            globalReport.addSmellReportToProjectReport(secondMockedReport);

            assertEquals(2, globalReport.getSmellReports().size());
            assertThat(globalReport.getSmellReports(), containsInAnyOrder(firstMockedReport, secondMockedReport));
        }
    }

    public static class getReportForSpecifiedSmellTest{
        @Test
        public void getReportForSpecifiedSmellTest(){
            String firstReportName = "firstTestName";
            ISmellReport firstMockedReport = mock(ISmellReport.class);
            when(firstMockedReport.getSmellName()).thenReturn(firstReportName);

            GlobalReport globalReport = new GlobalReport();

            globalReport.addSmellReportToProjectReport(firstMockedReport);

            assertEquals(firstReportName, globalReport.getReportForSpecifiedSmell(firstReportName).getSmellName());
        }
    }
}
