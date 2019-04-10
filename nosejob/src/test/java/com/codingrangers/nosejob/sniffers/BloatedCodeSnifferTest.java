package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedMethod;
import com.codingrangers.nosejob.parser.ParsedProject;
import com.codingrangers.nosejob.reports.SmellReport;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class BloatedCodeSnifferTest {

    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertThat(blCdSnifferTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromMethodTests {
        @Test
        public void retrieveSmellsFromMethodTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod mockedTestMethod = mock(ParsedMethod.class);
            when(mockedTestMethod.getLineCount()).thenReturn(11);
            when(mockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(mockedTestMethod.getFullyQualifiedName()).thenReturn("mockedMethod");
            projectTest.addClass(testClass);

            testClass.addMethod(mockedTestMethod);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.16f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(11);
            when(firstMockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            testClass.addMethod(firstMockedTestMethod);

            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(16);
            when(secondMockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            testClass.addMethod(secondMockedTestMethod);

            projectTest.addClass(testClass);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.24f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");

            projectTest.addClass(testClass);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.0f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(11);
            when(firstMockedTestMethod.getClassName()).thenReturn(firstTestClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            firstTestClass.addMethod(firstMockedTestMethod);

            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(5);
            when(secondMockedTestMethod.getClassName()).thenReturn(secondTestClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            secondTestClass.addMethod(secondMockedTestMethod);

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addMethod(firstMockedTestMethod);
            secondTestClass.addMethod(secondMockedTestMethod);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.08f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSmallMethodsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(10);
            when(firstMockedTestMethod.getClassName()).thenReturn(firstTestClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            firstTestClass.addMethod(firstMockedTestMethod);

            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(5);
            when(secondMockedTestMethod.getClassName()).thenReturn(secondTestClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            secondTestClass.addMethod(secondMockedTestMethod);

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addMethod(firstMockedTestMethod);
            secondTestClass.addMethod(secondMockedTestMethod);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.0f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.0f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
