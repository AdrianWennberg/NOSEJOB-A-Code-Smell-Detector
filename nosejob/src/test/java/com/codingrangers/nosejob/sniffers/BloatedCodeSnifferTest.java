package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodData;
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
public class BloatedCodeSnifferTest {

    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);

            assertThat(bloatedCodeTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromMethodTests {
        @Test
        public void retrieveSmellsFromMethodTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String testMethod = "mockedMethod";

            ClassData mockedTestClass = mock(ClassData.class);

<<<<<<< HEAD
            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            MethodData mockedTestMethod = mock(MethodData.class);
            when(mockedTestMethod.getLineCount()).thenReturn(16);
            when(mockedTestMethod.getClassName()).thenReturn(testClass);
            when(mockedTestMethod.getFullyQualifiedName()).thenReturn(testMethod);

            when(mockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(testMethod));
            when(mockedTestClass.getMethod(testMethod)).thenReturn(mockedTestMethod);

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);

=======
            projectTest.addClass(testClass);

            ParsedMethod mockedTestMethod = mock(ParsedMethod.class);
            when(mockedTestMethod.getLineCount()).thenReturn(16);
            when(mockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(mockedTestMethod.getFullyQualifiedName()).thenReturn("mockedMethod");
            testClass.addMethod(mockedTestMethod);

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);

>>>>>>> Feature Envy implemented, need Testing
            assertEquals(0.1f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String firstTestMethod = "firstMockedTestMethod";
            String secondTestMethod= "secondMockedTestMethod";

            ClassData mockedTestClass = mock(ClassData.class);

<<<<<<< HEAD
            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
=======
            projectTest.addClass(testClass);

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(16);
            when(firstMockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            testClass.addMethod(firstMockedTestMethod);
>>>>>>> Feature Envy implemented, need Testing

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

<<<<<<< HEAD
            MethodData firstMockedTestMethod = mock(MethodData.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(16);
            when(firstMockedTestMethod.getClassName()).thenReturn(testClass);
            when(firstMockedTestMethod.getName()).thenReturn(firstTestMethod);

<<<<<<< HEAD
            MethodData secondMockedTestMethod = mock(MethodData.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(21);
            when(secondMockedTestMethod.getClassName()).thenReturn(testClass);
            when(secondMockedTestMethod.getName()).thenReturn(secondTestMethod);
=======
            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(21);
            when(secondMockedTestMethod.getClassName()).thenReturn(testClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            testClass.addMethod(secondMockedTestMethod);
>>>>>>> Data only classes Sniffer Tested

            when(mockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(firstTestMethod, secondTestMethod));
            when(mockedTestClass.getMethod(firstTestMethod)).thenReturn(firstMockedTestMethod);
            when(mockedTestClass.getMethod(secondTestMethod)).thenReturn(secondMockedTestMethod);

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);

=======
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);

>>>>>>> Feature Envy implemented, need Testing
            assertEquals(0.15f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";

            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

<<<<<<< HEAD
            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);
=======
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);
>>>>>>> Feature Envy implemented, need Testing

            assertEquals(0f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherClass = "otherClass";
            String firstTestMethod = "firstMockedTestMethod";
            String secondTestMethod= "secondMockedTestMethod";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

<<<<<<< HEAD
            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(55);
            when(firstMockedTestMethod.getClassName()).thenReturn(firstTestClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            firstTestClass.addMethod(firstMockedTestMethod);
>>>>>>> Feature Envy implemented, need Testing

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedTestClass);

<<<<<<< HEAD
            MethodData firstMockedTestMethod = mock(MethodData.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(55);
            when(firstMockedTestMethod.getClassName()).thenReturn(testClass);
            when(firstMockedTestMethod.getName()).thenReturn(firstTestMethod);

            MethodData secondMockedTestMethod = mock(MethodData.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(10);
            when(secondMockedTestMethod.getClassName()).thenReturn(otherClass);
            when(secondMockedTestMethod.getName()).thenReturn(secondTestMethod);

            when(mockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(firstTestMethod));
            when(mockedTestClass.getMethod(firstTestMethod)).thenReturn(firstMockedTestMethod);
            when(otherMockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(secondTestMethod));
            when(otherMockedTestClass.getMethod(secondTestMethod)).thenReturn(secondMockedTestMethod);

<<<<<<< HEAD
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);
=======
            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(10);
            when(secondMockedTestMethod.getClassName()).thenReturn(secondTestClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            secondTestClass.addMethod(secondMockedTestMethod);
>>>>>>> Data only classes Sniffer Tested

=======
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);

>>>>>>> Feature Envy implemented, need Testing
            assertEquals(0.5f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromSmallMethodsOfMultipleClassesTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherClass = "otherClass";
            String firstTestMethod = "firstMockedTestMethod";
            String secondTestMethod= "secondMockedTestMethod";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

<<<<<<< HEAD
            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedMethod firstMockedTestMethod = mock(ParsedMethod.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(15);
            when(firstMockedTestMethod.getClassName()).thenReturn(firstTestClass.getName());
            when(firstMockedTestMethod.getName()).thenReturn("firstMockedTestMethod");
            firstTestClass.addMethod(firstMockedTestMethod);
>>>>>>> Feature Envy implemented, need Testing

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedTestClass);

<<<<<<< HEAD
<<<<<<< HEAD
            MethodData firstMockedTestMethod = mock(MethodData.class);
            when(firstMockedTestMethod.getLineCount()).thenReturn(15);
            when(firstMockedTestMethod.getClassName()).thenReturn(testClass);
            when(firstMockedTestMethod.getName()).thenReturn(firstTestMethod);

            MethodData secondMockedTestMethod = mock(MethodData.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(30);
            when(secondMockedTestMethod.getClassName()).thenReturn(otherClass);
            when(secondMockedTestMethod.getName()).thenReturn(secondTestMethod);

            when(mockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(firstTestMethod));
            when(mockedTestClass.getMethod(firstTestMethod)).thenReturn(firstMockedTestMethod);
            when(otherMockedTestClass.getMethodSignatures()).thenReturn(Arrays.asList(secondTestMethod));
            when(otherMockedTestClass.getMethod(secondTestMethod)).thenReturn(secondMockedTestMethod);
=======
            ParsedMethod secondMockedTestMethod = mock(ParsedMethod.class);
            when(secondMockedTestMethod.getLineCount()).thenReturn(30);
            when(secondMockedTestMethod.getClassName()).thenReturn(secondTestClass.getName());
            when(secondMockedTestMethod.getName()).thenReturn("secondMockedTestMethod");
            secondTestClass.addMethod(secondMockedTestMethod);
>>>>>>> Data only classes Sniffer Tested

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);

=======
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);

>>>>>>> Feature Envy implemented, need Testing
            assertEquals(0.25f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
            ProjectData mockedProject = mock(ProjectData.class);
            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

<<<<<<< HEAD
            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedTestClass);

            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(mockedProject);
=======
            BloatedCodeSniffer bloatedCodeTest = new BloatedCodeSniffer();
            bloatedCodeTest.setProjectToSniff(projectTest);
>>>>>>> Feature Envy implemented, need Testing

            assertEquals(0f, bloatedCodeTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

    }
}
