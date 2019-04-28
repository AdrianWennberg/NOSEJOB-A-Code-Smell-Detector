package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.FieldReference;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedProject;
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
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ParsedClass mockedTestClass = mock(ParsedClass.class);
            ParsedClass otherMockedClass = mock(ParsedClass.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);


            FieldReference firstMockedReference = mock(FieldReference.class);

            when(firstMockedReference.isInternal()).thenReturn(false);

            List<FieldReference> mockedList = Arrays.asList(firstMockedReference);
            when(mockedTestClass.getFieldReferencesTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedList);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromManyReferencedFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

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

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoReferencedFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";

            ParsedClass mockedTestClass = mock(ParsedClass.class);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(mockedProject);

            assertEquals(0f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);

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
            FieldReference secondMockedReference = mock(FieldReference.class);
            FieldReference thirdMockedReference = mock(FieldReference.class);

            when(secondMockedReference.isInternal()).thenReturn(false);

            List<FieldReference> mockedListFirstTestClass = Arrays.asList(firstMockedReference, secondMockedReference, thirdMockedReference);
            List<FieldReference> mockedListSecondTestClass = Arrays.asList(firstMockedReference, secondMockedReference);
            List<FieldReference> mockedListThirdTestClass = Arrays.asList();
            when(firstMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListFirstTestClass);
            when(secondMockedClass.getFieldReferencesTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedListSecondTestClass);
            when(thirdMockedClass.getFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListThirdTestClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.66f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

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
            when(firstMockedClass.countFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListFirstTestClass.size());
            when(secondMockedClass.countFieldReferencesTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedListSecondTestClass.size());
            when(thirdMockedClass.countFieldReferencesTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedListThirdTestClass.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);


            InappropriateIntimacySniffer inappropriateIntimacyTest = new InappropriateIntimacySniffer();
            inappropriateIntimacyTest.setProjectToSniff(mockedProject);

            assertEquals(0f, inappropriateIntimacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
