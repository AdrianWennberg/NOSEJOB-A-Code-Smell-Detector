package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.codingrangers.nosejob.reports.SmellReport;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PrimitiveObsessionSnifferTest {
    public static class getSmellReportTests{

        @Test
        public void retrieveNonNullReport(){
            ParsedProject projectTest = new ParsedProject();

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertThat(primitiveObsessionTest.getSmellReport(), instanceOf(SmellReport.class));
        }
    }

    public static class retrieveSmellsFromMethodTests {

        @Test
        public void retrieveSmellsFromMethodTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

<<<<<<< HEAD
            ParsedMethod testMethod = testClass.createMethod("testMethod");
=======
            projectTest.addClass(testClass);

            ParsedMethod testMethod = new ParsedMethod(testClass, "testMethod");
            testClass.addMethod(testMethod);
>>>>>>> Feature Envy implemented, need Testing

            ParsedVariable firstTestField = testMethod.createParameter("firstTestField");
            firstTestField.setIsPrimitive();
<<<<<<< HEAD

            ParsedVariable secondTestField = testMethod.createVariable("secondTestField");
            secondTestField.setIsPrimitive();

            testMethod.setReturnType("", false);
=======
            testMethod.addParameter(firstTestField);
            ParsedVariable secondTestField = new ParsedVariable(testMethod, "secondTestField");
            secondTestField.setIsPrimitive();
            testMethod.addVariable(secondTestField);
            ParsedVariable thirdTestField = new ParsedVariable(testMethod, "thirdTestField");
            testMethod.addReturnType(thirdTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.67f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

<<<<<<< HEAD
            ParsedMethod firstTestMethod = testClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = testClass.createMethod("secondTestMethod");
=======
            projectTest.addClass(testClass);

            ParsedMethod firstTestMethod = new ParsedMethod(testClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(testClass, "secondTestMethod");
            testClass.addMethod(firstTestMethod);
            testClass.addMethod(secondTestMethod);
>>>>>>> Feature Envy implemented, need Testing


            ParsedVariable firstTestField = firstTestMethod.createParameter("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = firstTestMethod.createVariable("secondTestField");
            secondTestField.setIsPrimitive();
<<<<<<< HEAD
            firstTestMethod.setReturnType("", false);


            ParsedVariable thirdTestField = secondTestMethod.createParameter("thirdTestField");
            thirdTestField.setIsPrimitive();
            ParsedVariable fourthTestField = secondTestMethod.createVariable("fourthTestField");
            secondTestMethod.setReturnType("", false);
=======
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            firstTestMethod.addParameter(firstTestField);
            firstTestMethod.addVariable(secondTestField);
            firstTestMethod.addReturnType(thirdTestField);

            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
            fourthTestField.setIsPrimitive();
            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
            secondTestMethod.addParameter(fourthTestField);
            secondTestMethod.addVariable(fifthTestField);
            secondTestMethod.addReturnType(sixthTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

<<<<<<< HEAD
            ParsedMethod firstTestMethod = firstTestClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = secondTestClass.createMethod("secondTestMethod");


            ParsedVariable firstTestField = firstTestMethod.createParameter("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = firstTestMethod.createVariable("secondTestField");
            firstTestMethod.setReturnType("", false);


            ParsedVariable thirdTestField = secondTestMethod.createParameter("thirdTestField");
            thirdTestField.setIsPrimitive();
            ParsedVariable fourthTestField = secondTestMethod.createVariable("fourthTestField");
            secondTestMethod.setReturnType("", false);
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");

            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);

            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(firstTestMethod, "secondTestField");
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            firstTestMethod.addParameter(firstTestField);
            firstTestMethod.addVariable(secondTestField);
            firstTestMethod.addReturnType(thirdTestField);
            ParsedVariable fourthTestField = new ParsedVariable(secondTestMethod, "fourthTestField");
            fourthTestField.setIsPrimitive();
            ParsedVariable fifthTestField = new ParsedVariable(secondTestMethod, "fifthTestField");
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
            secondTestMethod.addParameter(fourthTestField);
            secondTestMethod.addVariable(fifthTestField);
            secondTestMethod.addReturnType(sixthTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.33f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ClassData testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellFromFieldsTest {

        @Test
        public void retrieveSmellsFromFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

<<<<<<< HEAD
            ParsedVariable firstTestField = testClass.createField("firstTestField");
=======
            projectTest.addClass(testClass);

            ParsedVariable firstTestField = new ParsedVariable(testClass, "firstTestField");
>>>>>>> Feature Envy implemented, need Testing
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = testClass.createField("secondTestField");
            secondTestField.setIsPrimitive();
<<<<<<< HEAD
            ParsedVariable thirdTestField = testClass.createField("thirdTestField");
            ParsedVariable fourthTestField = testClass.createField("fourthTestField");

=======
            ParsedVariable thirdTestField = new ParsedVariable(testClass, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(testClass, "fourthTestField");
            testClass.addField(firstTestField);
            testClass.addField(secondTestField);
            testClass.addField(thirdTestField);
            testClass.addField(fourthTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

<<<<<<< HEAD
            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = firstTestClass.createField("secondTestField");
            ParsedVariable thirdTestField = secondTestClass.createField("thirdTestField");
            ParsedVariable fourthTestField = secondTestClass.createField("fourthTestField");
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(firstTestClass, "secondTestField");
            ParsedVariable thirdTestField = new ParsedVariable(secondTestClass, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(secondTestClass, "fourthTestField");
            firstTestClass.addField(firstTestField);
            firstTestClass.addField(secondTestField);
            secondTestClass.addField(thirdTestField);
            secondTestClass.addField(fourthTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.25f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromClassesEmptyFieldsNotInMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");


            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoFields() {
            ParsedProject projectTest = new ParsedProject();

<<<<<<< HEAD
            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = firstTestClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = secondTestClass.createMethod("secondTestMethod");

            firstTestMethod.setReturnType("", true);
            secondTestMethod.setReturnType("", false);
=======
            ParsedClass firstTestClass = new ParsedClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = new ParsedClass("nosejob", "secondTestClass", "C:\\tests");

            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");
            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);

            ParsedVariable firstTestField = new ParsedVariable(firstTestMethod, "firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = new ParsedVariable(secondTestMethod, "secondTestField");
            firstTestMethod.addReturnType(firstTestField);
            secondTestMethod.addReturnType(secondTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

<<<<<<< HEAD
            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedVariable firstTestField = new ParsedVariable(firstTestClass, "firstTestField");
>>>>>>> Feature Envy implemented, need Testing
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = secondTestClass.createField("secondTestField");
            secondTestField.setIsPrimitive();
<<<<<<< HEAD

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);
=======
            firstTestClass.addField(firstTestField);
            secondTestClass.addField(secondTestField);

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);
>>>>>>> Feature Envy implemented, need Testing

            assertEquals(1f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

<<<<<<< HEAD
            ParsedMethod firstTestMethod = firstTestClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = secondTestClass.createMethod("secondTestMethod");
=======
            projectTest.addClass(firstTestClass);
            projectTest.addClass(secondTestClass);

            ParsedMethod firstTestMethod = new ParsedMethod(firstTestClass, "firstTestMethod");
            ParsedMethod secondTestMethod = new ParsedMethod(secondTestClass, "secondTestMethod");
            firstTestClass.addMethod(firstTestMethod);
            secondTestClass.addMethod(secondTestMethod);
>>>>>>> Feature Envy implemented, need Testing

            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = secondTestClass.createField("secondTestField");
            secondTestField.setIsPrimitive();
<<<<<<< HEAD


            ParsedVariable thirdTestField = firstTestMethod.createParameter("thirdTestField");
            ParsedVariable fourthTestField = firstTestMethod.createVariable("fourthTestField");
            firstTestMethod.setReturnType("", true);


            ParsedVariable sixthTestField = secondTestMethod.createParameter("sixthTestField");
            ParsedVariable seventhTestField = secondTestMethod.createVariable("seventhTestField");
            secondTestMethod.setReturnType("", true);
=======
            ParsedVariable thirdTestField = new ParsedVariable(firstTestMethod, "thirdTestField");
            ParsedVariable fourthTestField = new ParsedVariable(firstTestMethod, "fourthTestField");
            ParsedVariable fifthTestField = new ParsedVariable(firstTestMethod, "fifthTestField");
            fifthTestField.setIsPrimitive();
            ParsedVariable sixthTestField = new ParsedVariable(secondTestMethod, "sixthTestField");
            ParsedVariable seventhTestField = new ParsedVariable(secondTestMethod, "seventhTestField");
            ParsedVariable eighthTestField = new ParsedVariable(secondTestMethod, "eighthTestField");
            eighthTestField.setIsPrimitive();

            firstTestClass.addField(firstTestField);
            secondTestClass.addField(secondTestField);

            firstTestMethod.addParameter(thirdTestField);
            firstTestMethod.addVariable(fourthTestField);
            firstTestMethod.addReturnType(fifthTestField);
            secondTestMethod.addParameter(sixthTestField);
            secondTestMethod.addVariable(seventhTestField);
            secondTestMethod.addReturnType(eighthTestField);
>>>>>>> Feature Envy implemented, need Testing

            PrimitiveObsessionSniffer primitiveObsessionTest = new PrimitiveObsessionSniffer();
            primitiveObsessionTest.setProjectToSniff(projectTest);

            assertEquals(0.66f, primitiveObsessionTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
