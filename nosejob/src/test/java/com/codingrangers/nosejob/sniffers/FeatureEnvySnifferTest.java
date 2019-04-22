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
public class FeatureEnvySnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertThat(featureEnvySnifferTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromClassTests {
        @Test
        public void retrieveSmellsFromClassNoInternalCallsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> mockedInternalList = Arrays.asList();
            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(mockedInternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOneInternalCallsTest() {
            /**NOTE: This should have the same result as test above*/
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> mockedInternalList = Arrays.asList(mock(MethodReference.class));
            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(mockedInternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);
            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromEqualExternalAndInternalMethodCallsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> mockedInternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));
            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(mockedInternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromManyExternalMethodCallsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            String otherClass = "otherClass";

            ClassData mockedTestClass = mock(ClassData.class);
            ClassData otherMockedClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);
            when(otherMockedClass.getName()).thenReturn(otherClass);
            when(otherMockedClass.getFullyQualifiedName()).thenReturn(otherClass);

            List<MethodReference> mockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> mockedInternalList = Arrays.asList(mock(MethodReference.class));

            when(mockedTestClass.countMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList.size());
            when(mockedTestClass.countInternalMethodCalls()).thenReturn(mockedInternalList.size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass, otherClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);
            when(mockedProject.getClassData(otherClass)).thenReturn(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoReferencedFieldsTest() {
            ProjectData mockedProject = mock(ProjectData.class);

            String testClass = "testClass";
            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(testClass));
            when(mockedProject.getClassData(testClass)).thenReturn(mockedTestClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
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

            List<MethodReference> firstMockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> firstMockedInternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));

            List<MethodReference> secondMockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> secondMockedInternalList = Arrays.asList(mock(MethodReference.class));

            when(firstMockedClass.countMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(firstMockedExternalList.size());
            when(firstMockedClass.countInternalMethodCalls()).thenReturn(firstMockedInternalList.size());

            when(secondMockedClass.countMethodCallsTo(thirdMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList.size());
            when(secondMockedClass.countInternalMethodCalls()).thenReturn(secondMockedInternalList.size());

            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList(mock(MethodReference.class)).size());
            when(thirdMockedClass.countInternalMethodCalls()).thenReturn(Arrays.asList().size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
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

            when(firstMockedClass.countMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());
            when(firstMockedClass.countInternalMethodCalls()).thenReturn(Arrays.asList().size());

            when(secondMockedClass.countMethodCallsTo(thirdMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());
            when(secondMockedClass.countInternalMethodCalls()).thenReturn(Arrays.asList().size());

            when(thirdMockedClass.countMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList().size());
            when(thirdMockedClass.countInternalMethodCalls()).thenReturn(Arrays.asList().size());

            when(mockedProject.getClassNames()).thenReturn(Arrays.asList(firstTestClass, secondTestClass, thirdTestClass));
            when(mockedProject.getClassData(firstTestClass)).thenReturn(firstMockedClass);
            when(mockedProject.getClassData(secondTestClass)).thenReturn(secondMockedClass);
            when(mockedProject.getClassData(thirdTestClass)).thenReturn(thirdMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(mockedProject);

            assertEquals(0.0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}