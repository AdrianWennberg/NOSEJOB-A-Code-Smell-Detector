//package com.codingrangers.nosejob.sniffers;
//
//import com.codingrangers.nosejob.models.ClassData;
//import com.codingrangers.nosejob.parser.data.*;
//import com.codingrangers.nosejob.reports.SmellReport;
//import org.junit.Test;
//import org.junit.experimental.runners.Enclosed;
//import org.junit.runner.RunWith;
//
//import static org.hamcrest.CoreMatchers.instanceOf;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//
//@RunWith(Enclosed.class)
//public class PrimitiveObsessionSnifferTest {
//    public static class getSmellReportTests{
//
//        @Test
//        public void retrieveNonNullReport(){
//            ParsedProject projectTest = new ParsedProject();
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertThat(primitiveObsessionTest.getSmellReport(), instanceOf(SmellReport.class));
//        }
//    }
//
//    public static class retrieveSmellsFromMethodTests {
//
//        @Test
//        public void retrieveSmellsFromMethodTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");
//
//            projectTest.addClass(testClass);
//
//            ParsedMethod testMethod = new ParsedMethod(testClass, "testMethod");
//            testClass.addMethod(testMethod);
//
//            ParsedVariable firstTestField = new ParsedVariable(testMethod, "firstTestField");
//            firstTestField.setIsPrimitive();
//            testMethod.addParameter(firstTestField);
//            ParsedVariable secondTestField = new ParsedVariable(testMethod, "secondTestField");
//            secondTestField.setIsPrimitive();
//            testMethod.addVariable(secondTestField);
//            ParsedVariable thirdTestField = new ParsedVariable(testMethod, "thirdTestField");
//            testMethod.addReturnType(thirdTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.67f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromMethodsTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");
//
//            projectTest.addClass(testClass);
//
//            ParsedMethod firstTestMethod = new ParsedMethod(testClass, "firstTestMethod");
//            ParsedMethod secondTestMethod = new ParsedMethod(testClass, "secondTestMethod");
//            testClass.addMethod(firstTestMethod);
//            testClass.addMethod(secondTestMethod);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(firstTestMethod, "secondTestField");
//            secondTestField.setIsPrimitive();
//            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
//            firstTestMethod.addParameter(firstTestField);
//            firstTestMethod.addVariable(secondTestField);
//            firstTestMethod.addReturnType(thirdTestField);
//
//            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
//            fourthTestField.setIsPrimitive();
//            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
//            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
//            secondTestMethod.addParameter(fourthTestField);
//            secondTestMethod.addVariable(fifthTestField);
//            secondTestMethod.addReturnType(sixthTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
//            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");
//
//            firstTestClass.addMethod(firstTestMethod);
//            secondTestClass.addMethod(secondTestMethod);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(firstTestMethod, "secondTestField");
//            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
//            firstTestMethod.addParameter(firstTestField);
//            firstTestMethod.addVariable(secondTestField);
//            firstTestMethod.addReturnType(thirdTestField);
//            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
//            fourthTestField.setIsPrimitive();
//            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
//            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
//            secondTestMethod.addParameter(fourthTestField);
//            secondTestMethod.addVariable(fifthTestField);
//            secondTestMethod.addReturnType(sixthTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.33f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromNoMethodsTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");
//
//            projectTest.addClass(testClass);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//    }
//
//    public static class retrieveSmellFromFieldsTest {
//
//        @Test
//        public void retrieveSmellsFromFieldsTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");
//
//            projectTest.addClass(testClass);
//
//            ParsedVariable firstTestField = new ParsedVariable(testClass, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(testClass, "secondTestField");
//            secondTestField.setIsPrimitive();
//            ParsedVariable thirdTestField = new ParsedVariable(testClass, "thirdTestField");
//            ParsedVariable fourthTestField = new ParsedVariable(testClass, "fourthTestField");
//            testClass.addField(firstTestField);
//            testClass.addField(secondTestField);
//            testClass.addField(thirdTestField);
//            testClass.addField(fourthTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(firstTestClass, "secondTestField");
//            ParsedVariable thirdTestField = new ParsedVariable(secondTestClass, "thirdTestField");
//            ParsedVariable fourthTestField = new ParsedVariable(secondTestClass, "fourthTestField");
//            firstTestClass.addField(firstTestField);
//            firstTestClass.addField(secondTestField);
//            secondTestClass.addField(thirdTestField);
//            secondTestClass.addField(fourthTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.25f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromNoFieldsTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");
//
//            projectTest.addClass(testClass);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//    }
//
//    public static class retrieveSmellsFromClasses {
//        @Test
//        public void retrieveSmellsFromClassesEmptyFieldsNotInMethods() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromClassesNoFields() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
//            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");
//            firstTestClass.addMethod(firstTestMethod);
//            secondTestClass.addMethod(secondTestMethod);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(secondTestMethod, "secondTestField");
//            firstTestMethod.addReturnType(firstTestField);
//            secondTestMethod.addReturnType(secondTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromClassesNoMethods() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(secondTestClass, "secondTestField");
//            secondTestField.setIsPrimitive();
//            firstTestClass.addField(firstTestField);
//            secondTestClass.addField(secondTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(1f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
//
//        @Test
//        public void retrieveSmellsFromClassesTest() {
//            ParsedProject projectTest = new ParsedProject();
//
//            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
//            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");
//
//            projectTest.addClass(firstTestClass);
//            projectTest.addClass(secondTestClass);
//
//            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
//            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");
//            firstTestClass.addMethod(firstTestMethod);
//            secondTestClass.addMethod(secondTestMethod);
//
//            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
//            firstTestField.setIsPrimitive();
//            ParsedVariable secondTestField = new ParsedVariable(secondTestClass, "secondTestField");
//            secondTestField.setIsPrimitive();
//            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
//            ParsedVariable fourthTestField = new ParsedVariable(firstTestMethod, "fourthTestField");
//            ParsedVariable fifthTestField = new ParsedVariable(firstTestMethod, "fifthTestField");
//            fifthTestField.setIsPrimitive();
//            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
//            ParsedVariable seventhTestField = new ParsedVariable(secondTestMethod, "seventhTestField");
//            ParsedVariable eighthTestField = new ParsedVariable(secondTestMethod, "eighthTestField");
//            eighthTestField.setIsPrimitive();
//
//            firstTestClass.addField(firstTestField);
//            secondTestClass.addField(secondTestField);
//
//            firstTestMethod.addParameter(thirdTestField);
//            firstTestMethod.addVariable(fourthTestField);
//            firstTestMethod.addReturnType(fifthTestField);
//            secondTestMethod.addParameter(sixthTestField);
//            secondTestMethod.addVariable(seventhTestField);
//            secondTestMethod.addReturnType(eighthTestField);
//
//            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
//            primitiveObsessionTest.setProjectToSniff(projectTest);
//
//            assertEquals(0.66f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
//        }
