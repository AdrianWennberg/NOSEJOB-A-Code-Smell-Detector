package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.parser.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class PrimitiveObsessionSnifferTest {

    public static class retrieveSmellsFromMethodTests {

        @Test
        public void retrieveSmellsFromMethodTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod testMethod = new ParsedMethod(testClass, "testMethod");

            ParsedVariable firstTestField = new ParsedVariable(testMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(testMethod, "secondTestField");
            secondTestField.setIsPrimitive();
            ParsedVariable thirdTestField = new ParsedVariable(testMethod, "thirdTestField");

            projectTest.addClass(testClass);

            testClass.addMethod(testMethod);

            testMethod.addParameter(firstTestField);
            testMethod.addVariable(secondTestField);
            testMethod.addReturnType(thirdTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.67f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod firstTestMethod = new ParsedMethod(testClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(testClass, "secondTestMethod");

            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(firstTestMethod, "secondTestField");
            secondTestField.setIsPrimitive();
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
            fourthTestField.setIsPrimitive();
            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");

            projectTest.addClass(testClass);
            testClass.addMethod(firstTestMethod);
            testClass.addMethod(secondTestMethod);

            firstTestMethod.addParameter(firstTestField);
            firstTestMethod.addVariable(secondTestField);
            firstTestMethod.addReturnType(thirdTestField);
            secondTestMethod.addParameter(fourthTestField);
            secondTestMethod.addVariable(fifthTestField);
            secondTestMethod.addReturnType(sixthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");

            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(firstTestMethod, "secondTestField");
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
            fourthTestField.setIsPrimitive();
            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);

            firstTestMethod.addParameter(firstTestField);
            firstTestMethod.addVariable(secondTestField);
            firstTestMethod.addReturnType(thirdTestField);
            secondTestMethod.addParameter(fourthTestField);
            secondTestMethod.addVariable(fifthTestField);
            secondTestMethod.addReturnType(sixthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.33f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            projectTest.addClass(testClass);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellFromFieldsTest {

        @Test
        public void retrieveSmellsFromFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedVariable firstTestField = new ParsedVariable(testClass, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(testClass, "secondTestField");
            secondTestField.setIsPrimitive();
            ParsedVariable thirdTestField = new ParsedVariable(testClass, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(testClass, "fourthTestField");

            projectTest.addClass(testClass);

            testClass.addField(firstTestField);
            testClass.addField(secondTestField);
            testClass.addField(thirdTestField);
            testClass.addField(fourthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(firstTestClass, "secondTestField");
            ParsedVariable thirdTestField = new ParsedVariable(secondTestClass, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(secondTestClass, "fourthTestField");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            firstTestClass.addField(firstTestField);
            firstTestClass.addField(secondTestField);
            secondTestClass.addField(thirdTestField);
            secondTestClass.addField(fourthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.25f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            projectTest.addClass(testClass);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromClassesEmptyFieldsNotInMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoFields() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");

            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(secondTestMethod, "secondTestField");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);

            firstTestMethod.addReturnType(firstTestField);
            secondTestMethod.addReturnType(secondTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(secondTestClass, "secondTestField");
            secondTestField.setIsPrimitive();

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addField(firstTestField);
            secondTestClass.addField(secondTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(1f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");

            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(secondTestClass, "secondTestField");
            secondTestField.setIsPrimitive();
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(firstTestMethod, "fourthTestField");
            ParsedVariable fifthTestField = new ParsedVariable(firstTestMethod, "fifthTestField");
            fifthTestField.setIsPrimitive();
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
            ParsedVariable seventhTestField = new ParsedVariable(secondTestMethod, "seventhTestField");
            ParsedVariable eighthTestField = new ParsedVariable(secondTestMethod, "eighthTestField");
            eighthTestField.setIsPrimitive();

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);
            firstTestClass.addField(firstTestField);
            secondTestClass.addField(secondTestField);
            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);

            firstTestMethod.addParameter(thirdTestField);
            firstTestMethod.addVariable(fourthTestField);
            firstTestMethod.addReturnType(fifthTestField);
            secondTestMethod.addParameter(sixthTestField);
            secondTestMethod.addVariable(seventhTestField);
            secondTestMethod.addReturnType(eighthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.66f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
