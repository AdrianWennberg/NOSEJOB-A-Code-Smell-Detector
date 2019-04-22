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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSingleGodClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            List<MethodReference> secondMockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSingleGiantGodClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNonGodlyClassTest() {
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

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsEmptyClasses() {
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

            when(firstMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsMultipleGodClassesTest() {
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
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(firstMockedClass.countMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}