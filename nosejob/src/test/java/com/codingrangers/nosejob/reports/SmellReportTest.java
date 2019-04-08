package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.ISmell;
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

    public static class getSmellNameTest{
        @Test
        public void getSmellNameTest(){
            String smellName = "test";

            SmellReport report = new SmellReport();
            report.setSmellName(smellName);

            assertEquals(smellName, report.getSmellName());
        }
    }

    public static class canAddSmellsTest {
        @Test
        public void addingNonSmellySmellTest(){
            ISmell mockedSmell = mock(ISmell.class);

            when(mockedSmell.isSmelly()).thenReturn(false);

            SmellReport report = new SmellReport();

            report.addSmell(mockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(0f, smells.size(), 0.01);
            assertEquals(0f, report.getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void canAddSmellTest() {
            ISmell mockedSmell = mock(ISmell.class);

            when(mockedSmell.isSmelly()).thenReturn(true);

            SmellReport report = new SmellReport();

            report.addSmell(mockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(1, smells.size());
        }
    }

    public static class canAddAllSmellsTest {
        @Test
        public void addingNonSmellySmellsTest(){
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(false);
            when(secondMockedSmell.isSmelly()).thenReturn(false);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(0, smells.size());
        }

        @Test
        public void canAddSmellsTest() {
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(true);
            when(secondMockedSmell.isSmelly()).thenReturn(true);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(2, smells.size());
            assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell));
        }

        @Test
        public void canAddMixedSmellsTest() {
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(true);
            when(secondMockedSmell.isSmelly()).thenReturn(false);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(1, smells.size());
            assertThat(smells, containsInAnyOrder(firstMockedSmell));
        }
    }

    public static class getSmellsTest {
        @Test
        public void getAllSmellySmellsTest(){
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);
            ISmell thirdMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(true);
            when(secondMockedSmell.isSmelly()).thenReturn(true);
            when(thirdMockedSmell.isSmelly()).thenReturn(true);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);
            report.addSmell(thirdMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(3, smells.size());
            assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell, thirdMockedSmell));
        }

        @Test
        public void getNoSmellySmellsTest() {
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);
            ISmell thirdMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(false);
            when(secondMockedSmell.isSmelly()).thenReturn(false);
            when(thirdMockedSmell.isSmelly()).thenReturn(false);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);
            report.addSmell(thirdMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(0, smells.size());
        }

        @Test
        public void getMixedSmellsTest() {
            ISmell firstMockedSmell = mock(ISmell.class);
            ISmell secondMockedSmell = mock(ISmell.class);
            ISmell thirdMockedSmell = mock(ISmell.class);

            when(firstMockedSmell.isSmelly()).thenReturn(true);
            when(secondMockedSmell.isSmelly()).thenReturn(true);
            when(thirdMockedSmell.isSmelly()).thenReturn(false);

            SmellReport report = new SmellReport();

            report.addSmell(firstMockedSmell);
            report.addSmell(secondMockedSmell);
            report.addSmell(thirdMockedSmell);

            List<ISmell> smells = report.getSmells();

            assertEquals(2, smells.size());
            assertThat(smells, containsInAnyOrder(firstMockedSmell, secondMockedSmell));
        }
    }

    public static class getTotalSmellSeverityTest {
        @Parameterized.Parameter()
        public float average;
        @Parameterized.Parameter(1)
        public List<ISmell> testSmells = new ArrayList<>();

        @Parameterized.Parameters
        public static Collection<Object[]> testData() {
            ISmell one = mock(ISmell.class);
            ISmell two = mock(ISmell.class);
            ISmell three = mock(ISmell.class);

            when(one.getSmellSeverity()).thenReturn(0f);
            when(two.getSmellSeverity()).thenReturn(0f);
            when(three.getSmellSeverity()).thenReturn(1f);

            List<ISmell> firstList = new ArrayList<>();
            firstList.add(one);
            firstList.add(two);
            firstList.add(three);

            ISmell four = mock(ISmell.class);
            ISmell five = mock(ISmell.class);
            ISmell six = mock(ISmell.class);

            when(four.getSmellSeverity()).thenReturn(1f);
            when(five.getSmellSeverity()).thenReturn(0f);
            when(six.getSmellSeverity()).thenReturn(0.5f);

            List<ISmell> secondList = new ArrayList<>();
            secondList.add(four);
            secondList.add(five);
            secondList.add(six);

            ISmell seven = mock(ISmell.class);
            ISmell eight = mock(ISmell.class);
            ISmell nine = mock(ISmell.class);

            when(seven.getSmellSeverity()).thenReturn(0.25f);
            when(eight.getSmellSeverity()).thenReturn(0.5f);
            when(nine.getSmellSeverity()).thenReturn(0.75f);

            List<ISmell> thirdList = new ArrayList<>();
            thirdList.add(seven);
            thirdList.add(eight);
            thirdList.add(nine);

            ISmell ten = mock(ISmell.class);
            ISmell eleven = mock(ISmell.class);
            ISmell twelve = mock(ISmell.class);

            when(ten.getSmellSeverity()).thenReturn(0.33f);
            when(eleven.getSmellSeverity()).thenReturn(0.33f);
            when(twelve.getSmellSeverity()).thenReturn(0.16f);

            List<ISmell> fourthList = new ArrayList<>();
            fourthList.add(ten);
            fourthList.add(eleven);
            fourthList.add(twelve);

            ISmell thirteen = mock(ISmell.class);
            ISmell fourteen = mock(ISmell.class);
            ISmell fifteen = mock(ISmell.class);

            when(thirteen.getSmellSeverity()).thenReturn(0.10f);
            when(fourteen.getSmellSeverity()).thenReturn(0.20f);
            when(fifteen.getSmellSeverity()).thenReturn(0f);

            List<ISmell> fifthList = new ArrayList<>();
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
            SmellReport report = new SmellReport();
            report.addSmells(testSmells);
            assertEquals(average, report.getTotalSmellSeverity(), 0.01);
        }
    }
}
