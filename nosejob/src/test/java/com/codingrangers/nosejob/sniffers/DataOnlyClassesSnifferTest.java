package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodReference;
<<<<<<< HEAD
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedProject;
=======
import com.codingrangers.nosejob.parser.ParsedProject;
>>>>>>> opps
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
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");

<<<<<<< HEAD
            when(mockedTestClass.countMethods()).thenReturn(declaredMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);
=======
            when(mockedTestClass.getMethodSignatures()).thenReturn(declaredMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyExternalMethodCalls() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> externalMethods = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));

<<<<<<< HEAD
            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);
=======
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyInternalMethodCalls() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> internalMethodsCalls = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));

<<<<<<< HEAD
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);
=======
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClass() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> externalMethods = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");
            List<MethodReference> internalMethodsCalls = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class));

<<<<<<< HEAD
            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(internalMethodsCalls.size());
            when(mockedTestClass.countMethods()).thenReturn(declaredMethods.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);
=======
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);
            when(mockedTestClass.getMethodSignatures()).thenReturn(declaredMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoMethods() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

<<<<<<< HEAD
            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(mockedProject);
=======
            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.75f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> opps

            String testClass = "testClass";
            String otherClass = "otherClass";
            String emptyClass = "emptyClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);
            ClassData emptyMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);
            when(emptyMockedClass.getName()).thenReturn(emptyClass);
            when(emptyMockedClass.getFullyQualifiedName()).thenReturn(emptyClass);

            List<MethodReference> externalMethods = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");
            List<MethodReference> internalMethodsCalls = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class));

<<<<<<< HEAD
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
=======
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);
            when(mockedTestClass.getMethodSignatures()).thenReturn(declaredMethods);
            when(otherMockedClass.getMethodCallsTo(mockedTestClass.getFullyQualifiedName())).thenReturn(externalMethods);
            when(otherMockedClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);
            when(otherMockedClass.getMethodSignatures()).thenReturn(declaredMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);
            projectTest.addClass(emptyMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);
>>>>>>> opps

            assertEquals(0.25f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}

