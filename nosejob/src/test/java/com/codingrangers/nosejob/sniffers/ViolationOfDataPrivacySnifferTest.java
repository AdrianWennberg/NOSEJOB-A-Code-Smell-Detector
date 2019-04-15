package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedProject;
import com.codingrangers.nosejob.parser.ParsedVariable;
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
public class ViolationOfDataPrivacySnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertThat(violationOfDataPrivacyTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromMethodTests {
        @Test
        public void retrieveSmellsFromFieldTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedVariable mockedTestField = mock(ParsedVariable.class);
            when(mockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PUBLIC);
            when(mockedTestField.getName()).thenReturn("mockedField");
            testClass.addField(mockedTestField);

            projectTest.addClass(testClass);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(1f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedVariable firstMockedTestField = mock(ParsedVariable.class);
            when(firstMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PUBLIC);
            when(firstMockedTestField.getName()).thenReturn("firstMockedField");
            testClass.addField(firstMockedTestField);

            ParsedVariable secondMockedTestField = mock(ParsedVariable.class);
            when(secondMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PROTECTED);
            when(secondMockedTestField.getName()).thenReturn("secondMockedField");
            testClass.addField(secondMockedTestField);

            projectTest.addClass(testClass);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");

            projectTest.addClass(testClass);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedVariable firstMockedTestField = mock(ParsedVariable.class);
            when(firstMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PUBLIC);
            when(firstMockedTestField.getName()).thenReturn("firstMockedField");
            firstTestClass.addField(firstMockedTestField);

            ParsedVariable secondMockedTestField = mock(ParsedVariable.class);
            when(secondMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PROTECTED);
            when(secondMockedTestField.getName()).thenReturn("secondMockedField");
            secondTestClass.addField(secondMockedTestField);

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addField(firstMockedTestField);
            secondTestClass.addField(secondMockedTestField);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromPrivateFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedVariable firstMockedTestField = mock(ParsedVariable.class);
            when(firstMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PRIVATE);
            when(firstMockedTestField.getName()).thenReturn("firstMockedField");
            firstTestClass.addField(firstMockedTestField);

            ParsedVariable secondMockedTestField = mock(ParsedVariable.class);
            when(secondMockedTestField.getAccessSpecifier()).thenReturn(AccessSpecifier.PROTECTED);
            when(secondMockedTestField.getName()).thenReturn("secondMockedField");
            secondTestClass.addField(secondMockedTestField);

            firstTestClass.addField(firstMockedTestField);
            secondTestClass.addField(secondMockedTestField);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
