package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IClassData;
import com.codingrangers.nosejob.models.IMethodData;
import com.codingrangers.nosejob.models.IProjectData;
import com.codingrangers.nosejob.models.IVariableData;
import com.codingrangers.nosejob.parser.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class PrimitiveObsessionSnifferTest {

    public static class retrieveSmellsFromMethodTests{

        @Test
        public void retrieveSmellsFromMethodTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            IMethodData testMethod = new ParsedMethod(testClass, "testMethod");

            IVariableData firstTestField = new ParsedVariable((ParsedMethod) testMethod, "firstTestField");
            ((ParsedVariable) firstTestField).setIsPrimitive();
            IVariableData secondTestField = new ParsedVariable((ParsedMethod) testMethod, "secondTestField");
            ((ParsedVariable) secondTestField).setIsPrimitive();
            IVariableData thirdTestField = new ParsedVariable((ParsedMethod) testMethod, "thirdTestField");

            ((ParsedProject) projectTest).addClass(testClass);

            ((ParsedClass) testClass).addMethod(testMethod);

            ((ParsedMethod) testMethod).addParameter(firstTestField);
            ((ParsedMethod) testMethod).addVariable(secondTestField);
            ((ParsedMethod) testMethod).addReturnType(thirdTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.67f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            IMethodData firstTestMethod = new ParsedMethod(testClass, "firstTestMethod");
            IMethodData secondTestMethod = new ParsedMethod(testClass, "secondTestMethod");

            IVariableData firstTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "firstTestField");
            ((ParsedVariable) firstTestField).setIsPrimitive();
            IVariableData secondTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "secondTestField");
            ((ParsedVariable) secondTestField).setIsPrimitive();
            IVariableData thirdTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "thirdTestField");
            IVariableData fourthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "fourthTestField");
            ((ParsedVariable) fourthTestField).setIsPrimitive();
            IVariableData fifthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "fifthTestField");
            IVariableData sixthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "sixthTestField");

            ((ParsedProject) projectTest).addClass(testClass);
            ((ParsedClass) testClass).addMethod(firstTestMethod);
            ((ParsedClass) testClass).addMethod(secondTestMethod);

            ((ParsedMethod) firstTestMethod).addParameter(firstTestField);
            ((ParsedMethod) firstTestMethod).addVariable(secondTestField);
            ((ParsedMethod) firstTestMethod).addReturnType(thirdTestField);
            ((ParsedMethod) secondTestMethod).addParameter(fourthTestField);
            ((ParsedMethod) secondTestMethod).addVariable(fifthTestField);
            ((ParsedMethod) secondTestMethod).addReturnType(sixthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.5, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            IClassData secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            IMethodData firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            IMethodData secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");

            IVariableData firstTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "firstTestField");
            ((ParsedVariable) firstTestField).setIsPrimitive();
            IVariableData secondTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "secondTestField");
            IVariableData thirdTestField = new ParsedVariable((ParsedMethod) firstTestMethod, "thirdTestField");
            IVariableData fourthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "fourthTestField");
            ((ParsedVariable) fourthTestField).setIsPrimitive();
            IVariableData fifthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "fifthTestField");
            IVariableData sixthTestField = new ParsedVariable((ParsedMethod) secondTestMethod, "sixthTestField");

            ((ParsedProject) projectTest).addClass(firstTestClass);
            ((ParsedProject) projectTest).addClass(secondTestClass);
            ((ParsedClass) firstTestClass).addMethod(firstTestMethod);
            ((ParsedClass) secondTestClass).addMethod(secondTestMethod);

            ((ParsedMethod) firstTestMethod).addParameter(firstTestField);
            ((ParsedMethod) firstTestMethod).addVariable(secondTestField);
            ((ParsedMethod) firstTestMethod).addReturnType(thirdTestField);
            ((ParsedMethod) secondTestMethod).addParameter(fourthTestField);
            ((ParsedMethod) secondTestMethod).addVariable(fifthTestField);
            ((ParsedMethod) secondTestMethod).addReturnType(sixthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.33f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ((ParsedProject) projectTest).addClass(testClass);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellFromFieldsTest {

        @Test
        public void retrieveSmellsFromFieldsTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            IVariableData firstTestField = new ParsedVariable((ParsedClass) testClass, "firstTestField");
            ((ParsedVariable) firstTestField).setIsPrimitive();
            IVariableData secondTestField = new ParsedVariable((ParsedClass) testClass, "secondTestField");
            ((ParsedVariable) secondTestField).setIsPrimitive();
            IVariableData thirdTestField = new ParsedVariable((ParsedClass) testClass, "thirdTestField");
            IVariableData fourthTestField = new ParsedVariable((ParsedClass) testClass, "fourthTestField");

            ((ParsedProject) projectTest).addClass(testClass);

            ((ParsedClass) testClass).addField(firstTestField);
            ((ParsedClass) testClass).addField(secondTestField);
            ((ParsedClass) testClass).addField(thirdTestField);
            ((ParsedClass) testClass).addField(fourthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            IClassData secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            IVariableData firstTestField = new ParsedVariable((ParsedClass) firstTestClass, "firstTestField");
            ((ParsedVariable) firstTestField).setIsPrimitive();
            IVariableData secondTestField = new ParsedVariable((ParsedClass) firstTestClass, "secondTestField");
            IVariableData thirdTestField = new ParsedVariable((ParsedClass) secondTestClass, "thirdTestField");
            IVariableData fourthTestField = new ParsedVariable((ParsedClass) secondTestClass, "fourthTestField");

            ((ParsedProject) projectTest).addClass(firstTestClass);
            ((ParsedProject) projectTest).addClass(secondTestClass);

            ((ParsedClass) firstTestClass).addField(firstTestField);
            ((ParsedClass) firstTestClass).addField(secondTestField);
            ((ParsedClass) secondTestClass).addField(thirdTestField);
            ((ParsedClass) secondTestClass).addField(fourthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ((ParsedProject) projectTest).addClass(testClass);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

//    public static class retrieveSmellFromClasses {
//
//    }
}
