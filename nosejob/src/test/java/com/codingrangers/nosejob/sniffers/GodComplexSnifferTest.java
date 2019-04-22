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
public class GodComplexSnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertThat(godComplexSnifferTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromClassTests {
        @Test
        public void retrieveSmellsFromSingleBigGodClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSingleGodClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            List<MethodReference> secondMockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSingleGiantGodClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNonGodlyClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsEmptyClasses() {
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

            when(firstMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsMultipleGodClassesTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            when(firstMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
   }
}
