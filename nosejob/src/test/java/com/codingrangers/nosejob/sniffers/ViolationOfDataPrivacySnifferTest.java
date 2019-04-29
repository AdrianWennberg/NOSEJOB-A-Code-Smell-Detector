/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.reports.SmellReport;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;

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
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String fieldName = "mockedField";

            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            when(mockedTestClass.getFieldsNames()).thenReturn(Arrays.asList(fieldName));
            when(mockedTestClass.countFields()).thenReturn(1);
            when(mockedTestClass.countPublicFields()).thenReturn(1);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(1f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String fieldName = "field";
            String otherFieldName = "otherField";

            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            when(mockedTestClass.getFieldsNames()).thenReturn(Arrays.asList(fieldName, otherFieldName));
            when(mockedTestClass.countFields()).thenReturn(2);
            when(mockedTestClass.countPublicFields()).thenReturn(1);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";

            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            when(mockedTestClass.getFieldsNames()).thenReturn(Arrays.asList());

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherTestClass = "otherTestClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedTestClass.getName()).thenReturn(otherTestClass);
            when(otherMockedTestClass.getFullyQualifiedName()).thenReturn(otherTestClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherTestClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherTestClass)).thenReturn(otherMockedTestClass);

            when(mockedTestClass.getFieldsNames()).thenReturn(Arrays.asList("field"));
            when(mockedTestClass.countFields()).thenReturn(1);
            when(mockedTestClass.countPublicFields()).thenReturn(1);

            when(otherMockedTestClass.getFieldsNames()).thenReturn(Arrays.asList("field"));
            when(otherMockedTestClass.countFields()).thenReturn(1);
            when(otherMockedTestClass.countPublicFields()).thenReturn(0);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromPrivateFieldsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherTestClass = "otherTestClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedTestClass.getName()).thenReturn(otherTestClass);
            when(otherMockedTestClass.getFullyQualifiedName()).thenReturn(otherTestClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherTestClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherTestClass)).thenReturn(otherMockedTestClass);

            when(mockedTestClass.getFieldsNames()).thenReturn(Arrays.asList("field"));
            when(mockedTestClass.countFields()).thenReturn(1);
            when(mockedTestClass.countPublicFields()).thenReturn(0);

            when(otherMockedTestClass.getFieldsNames()).thenReturn(Arrays.asList("field"));
            when(otherMockedTestClass.countFields()).thenReturn(1);
            when(otherMockedTestClass.countPublicFields()).thenReturn(0);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(0.0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherTestClass = "otherTestClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedTestClass.getName()).thenReturn(otherTestClass);
            when(otherMockedTestClass.getFullyQualifiedName()).thenReturn(otherTestClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherTestClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherTestClass)).thenReturn(otherMockedTestClass);

            ViolationOfDataPrivacySniffer violationOfDataPrivacyTest = new ViolationOfDataPrivacySniffer();
            violationOfDataPrivacyTest.setProjectToSniff(mockedProject);

            assertEquals(0f, violationOfDataPrivacyTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}