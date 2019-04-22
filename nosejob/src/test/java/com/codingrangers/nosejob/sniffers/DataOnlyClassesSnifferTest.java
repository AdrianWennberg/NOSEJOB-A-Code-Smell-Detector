package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodReference;
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
            ParsedProject projectTest = new ParsedProject();

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<String> declaredMethods = Arrays.asList("one", "two", "three", "four", "five");

            when(mockedTestClass.getMethodSignatures()).thenReturn(declaredMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyExternalMethodCalls() {
            ParsedProject projectTest = new ParsedProject();

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

            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOnlyInternalMethodCalls() {
            ParsedProject projectTest = new ParsedProject();

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

            when(mockedTestClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClass() {
            ParsedProject projectTest = new ParsedProject();

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

            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(externalMethods);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(internalMethodsCalls);
            when(mockedTestClass.getMethodSignatures()).thenReturn(declaredMethods);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.375f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoMethods() {
            ParsedProject projectTest = new ParsedProject();

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            DataOnlyClassesSniffer dataOnlyClassesSnifferTest = new DataOnlyClassesSniffer();
            dataOnlyClassesSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.75f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

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

            assertEquals(0.25f, dataOnlyClassesSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}

