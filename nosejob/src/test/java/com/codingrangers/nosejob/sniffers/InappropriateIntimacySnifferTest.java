package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.FieldReference;
import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedProject;
import com.codingrangers.nosejob.reports.SmellReport;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class InappropriateIntimacySnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertThat(inappropriateIntimacyTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromClassTests {
        @Test
        public void retrieveSmellsFromClassOneReferencedFieldTest() {
            ParsedProject projectTest = new ParsedProject();

            String testClass = "testClass";
            String otherClass = "otherClass";

            ParsedClass mockedTestClass = mock(ParsedClass.class);
            ParsedClass otherMockedClass = mock(ParsedClass.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            FieldReference firstMockedReference = mock(FieldReference.class);
            when(firstMockedReference.isInternal()).thenReturn(true);

            List<FieldReference> mockedList = Arrays.asList(firstMockedReference);
            when(mockedTestClass.getFieldReferencesTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromManyReferencedFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            FieldReference firstMockedReference = mock(FieldReference.class);
            when(firstMockedReference.isInternal()).thenReturn(true);
            FieldReference secondMockedReference = mock(FieldReference.class);
            when(secondMockedReference.isInternal()).thenReturn(false);
            FieldReference thirdMockedReference = mock(FieldReference.class);
            when(thirdMockedReference.isInternal()).thenReturn(true);

            List<FieldReference> mockedList = Arrays.asList(firstMockedReference, secondMockedReference, thirdMockedReference);
            when(mockedTestClass.getFieldReferencesTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoReferencedFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");

            projectTest.addClass(testClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            String firstTestClass = "firstTestClass";
            String secondTestClass = "secondTestClass";
            String thirdTestClass = "thirdTestClass";

            ClassData firstMockedClass = mock(ClassData.class);
            ClassData secondMockedClass = mock(ClassData.class);
            ClassData thirdMockedClass = mock(ClassData.class);

            when(firstMockedClass.getName()).thenReturn(firstTestClass);
            when(firstMockedClass.getFullyQualifiedName()).thenReturn(firstTestClass);
            when(secondMockedClass.getName()).thenReturn(secondTestClass);
            when(secondMockedClass.getFullyQualifiedName()).thenReturn(secondTestClass);
            when(thirdMockedClass.getName()).thenReturn(thirdTestClass);
            when(thirdMockedClass.getFullyQualifiedName()).thenReturn(thirdTestClass);

            FieldReference firstMockedReference = mock(FieldReference.class);
            when(firstMockedReference.isInternal()).thenReturn(true);
            FieldReference secondMockedReference = mock(FieldReference.class);
            when(secondMockedReference.isInternal()).thenReturn(true);
            FieldReference thirdMockedReference = mock(FieldReference.class);
            when(thirdMockedReference.isInternal()).thenReturn(false);

            List<FieldReference> mockedListFirstTestClass = Arrays.asList(firstMockedReference, secondMockedReference, thirdMockedReference);
            List<FieldReference> mockedListSecondTestClass = Arrays.asList(firstMockedReference, secondMockedReference);
            List<FieldReference> mockedListThirdTestClass = Arrays.asList();
            when(firstMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListFirstTestClass);
            when(secondMockedClass.getFieldReferencesTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedListSecondTestClass);
            when(thirdMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListThirdTestClass);

            projectTest.addClass( firstMockedClass );
            projectTest.addClass( secondMockedClass);
            projectTest.addClass( thirdMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertEquals(0.33f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            String firstTestClass = "firstTestClass";
            String secondTestClass = "secondTestClass";
            String thirdTestClass = "thirdTestClass";

            ClassData firstMockedClass = mock(ClassData.class);
            ClassData secondMockedClass = mock(ClassData.class);
            ClassData thirdMockedClass = mock(ClassData.class);

            when(firstMockedClass.getName()).thenReturn(firstTestClass);
            when(firstMockedClass.getFullyQualifiedName()).thenReturn(firstTestClass);
            when(secondMockedClass.getName()).thenReturn(secondTestClass);
            when(secondMockedClass.getFullyQualifiedName()).thenReturn(secondTestClass);
            when(thirdMockedClass.getName()).thenReturn(thirdTestClass);
            when(thirdMockedClass.getFullyQualifiedName()).thenReturn(thirdTestClass);

            List<FieldReference> mockedListFirstTestClass = Arrays.asList();
            List<FieldReference> mockedListSecondTestClass = Arrays.asList();
            List<FieldReference> mockedListThirdTestClass = Arrays.asList();
            when(firstMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListFirstTestClass);
            when(secondMockedClass.getFieldReferencesTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedListSecondTestClass);
            when(thirdMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListThirdTestClass);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(projectTest);

            assertEquals(0f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
