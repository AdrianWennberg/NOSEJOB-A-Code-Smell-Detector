package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodReference;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.reports.SmellReport;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class DataOnlyClassesSnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertThat(dataOnlyClassesSnifferTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromClassTests {
        @Test
        public void retrieveSmellsFromClassOnlyDeclaredMethods() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(2);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");

            when(mockedTestClass.countMethods()).thenReturn(declaredMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyExternalMethodCalls() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(2);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> externalMethods = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));

            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyInternalMethodCalls() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(4);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> internalMethodsCalls = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class));

            when(mockedTestClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.25f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClass() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(4);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> externalMethods = Arrays.asList(mock(MethodReference.class));
            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");
            List<MethodReference> internalMethodsCalls = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class));

            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());
            when(mockedTestClass.countMethods()).thenReturn(declaredMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.125f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoMethods() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(2);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);
            when(otherMockedClass.countFields()).thenReturn(2);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";
            String emptyClass = "emptyClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);
            ClassData emptyMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(mockedTestClass.countFields()).thenReturn(1);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);
            when(otherMockedClass.countFields()).thenReturn(2);
            when(emptyMockedClass.getName()).thenReturn(emptyClass);
            when(emptyMockedClass.getFullyQualifiedName()).thenReturn(emptyClass);

            List<MethodReference> externalMethods = Collections.emptyList();
            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four");
            List<MethodReference> internalMethodsCalls = Arrays.asList();

            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());
            when(mockedTestClass.countMethods()).thenReturn(declaredMethods.size());
            when(otherMockedClass.countMethodCallsTo(mockedTestClass.getFullyQualifiedName())).thenReturn(externalMethods.size());
            when(otherMockedClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());
            when(otherMockedClass.countMethods()).thenReturn(declaredMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass, emptyClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);
            when(mockedProject.getClassData(emptyClass)).thenReturn(emptyMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.33f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
