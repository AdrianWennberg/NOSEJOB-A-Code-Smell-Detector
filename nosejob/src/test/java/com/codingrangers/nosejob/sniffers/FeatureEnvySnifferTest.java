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
            ParsedProject projectTest = new ParsedProject();

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
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(mockedInternalList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassOneInternalCallsTest() {
            /**NOTE: This should have the same result as test above*/
            ParsedProject projectTest = new ParsedProject();

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
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(mockedInternalList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromEqualExternalAndInternalMethodCallsTest() {
            ParsedProject projectTest = new ParsedProject();

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
            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(mockedInternalList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromManyExternalMethodCallsTest() {
            ParsedProject projectTest = new ParsedProject();

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

            when(mockedTestClass.getMethodCallsTo(otherMockedClass.getFullyQualifiedName())).thenReturn(mockedExternalList);
            when(mockedTestClass.getInternalMethodCalls()).thenReturn(mockedInternalList);

            projectTest.addClass(mockedTestClass);
            projectTest.addClass(otherMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsNoReferencedFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            String testClass = "testClass";
            ClassData mockedTestClass = mock(ClassData.class);

            when(mockedTestClass.getName()).thenReturn(testClass);
            when(mockedTestClass.getFullyQualifiedName()).thenReturn(testClass);

            projectTest.addClass(mockedTestClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
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

            List<MethodReference> firstMockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> firstMockedInternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class));

            List<MethodReference> secondMockedExternalList = Arrays.asList(mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class), mock(MethodReference.class));
            List<MethodReference> secondMockedInternalList = Arrays.asList(mock(MethodReference.class));

            when(firstMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(firstMockedExternalList);
            when(firstMockedClass.getInternalMethodCalls()).thenReturn(firstMockedInternalList);

            when(secondMockedClass.getMethodCallsTo(thirdMockedClass.getFullyQualifiedName())).thenReturn(secondMockedExternalList);
            when(secondMockedClass.getInternalMethodCalls()).thenReturn(secondMockedInternalList);

            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList(mock(MethodReference.class)));
            when(thirdMockedClass.getInternalMethodCalls()).thenReturn(Arrays.asList());

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMultipleClassesWithNoMethodsTest() {
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

            when(firstMockedClass.getMethodCallsTo(secondMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(firstMockedClass.getInternalMethodCalls()).thenReturn(Arrays.asList());

            when(secondMockedClass.getMethodCallsTo(thirdMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(secondMockedClass.getInternalMethodCalls()).thenReturn(Arrays.asList());

            when(thirdMockedClass.getMethodCallsTo(firstMockedClass.getFullyQualifiedName())).thenReturn(Arrays.asList());
            when(thirdMockedClass.getInternalMethodCalls()).thenReturn(Arrays.asList());

            projectTest.addClass(firstMockedClass);
            projectTest.addClass(secondMockedClass);
            projectTest.addClass(thirdMockedClass);

            FeatureEnvySniffer featureEnvySnifferTest = new FeatureEnvySniffer();
            featureEnvySnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.0f, featureEnvySnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
   }
}
