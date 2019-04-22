package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodReference;
<<<<<<< HEAD
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedProject;
=======
import com.codingrangers.nosejob.parser.ParsedProject;
>>>>>>> Data only classes Sniffer Tested
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

<<<<<<< HEAD
=======
/**TODO*/
>>>>>>> Data only classes Sniffer Tested
@RunWith(Enclosed.class)
public class GodComplexSnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

<<<<<<< HEAD
            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(projectTest);

            assertThat(godComplexSnifferTest.getSmellReport(), instanceOf(SmellReport.class));
=======
            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertThat(godComplexSniffer.getSmellReport(), instanceOf(SmellReport.class));
>>>>>>> Data only classes Sniffer Tested
        }
    }

    public static class retrieveSmellsFromClassTests {
        @Test
        public void retrieveSmellsFromSingleBigGodClassTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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
<<<<<<< HEAD
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
=======
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
>>>>>>> Data only classes Sniffer Tested
        }

        @Test
        public void retrieveSmellsFromSingleGodClassTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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
<<<<<<< HEAD
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
=======
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
>>>>>>> Data only classes Sniffer Tested

            List<MethodReference> secondMockedExternalList = Arrays.asList(mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class),
                    mock(MethodReference.class));
<<<<<<< HEAD
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
=======
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(0.5f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
>>>>>>> Data only classes Sniffer Tested
        }

        @Test
        public void retrieveSmellsFromSingleGiantGodClassTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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
<<<<<<< HEAD
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(1f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
=======
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
>>>>>>> Data only classes Sniffer Tested
        }

        @Test
        public void retrieveSmellsFromNonGodlyClassTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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
<<<<<<< HEAD
            when(secondMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            GodComplexSniffer godComplexSnifferTest = new GodComplexSniffer();
            godComplexSnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0f, godComplexSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
=======
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(0f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
>>>>>>> Data only classes Sniffer Tested
        }

        @Test
        public void retrieveSmellsEmptyClasses() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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

<<<<<<< HEAD
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
=======
            when(firstMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(0f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
>>>>>>> Data only classes Sniffer Tested
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsMultipleGodClassesTest() {
<<<<<<< HEAD
            ProjectData mockedProject = mock(ProjectData.class);
=======
            ParsedProject projectTest = new ParsedProject();
>>>>>>> Data only classes Sniffer Tested

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
<<<<<<< HEAD
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
=======
            when(secondMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            when(firstMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(thirdMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
            godComplexSniffer.setProjectToSniff(projectTest);

            assertEquals(1f, godComplexSniffer.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
   }
>>>>>>> Data only classes Sniffer Tested
}
