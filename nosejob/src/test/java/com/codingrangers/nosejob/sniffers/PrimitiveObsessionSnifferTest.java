package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
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

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod testMethod = testClass.createMethod("testMethod");

            ParsedVariable firstTestField = testMethod.createParameter("firstTestField");
            firstTestField.setIsPrimitive();

            ParsedVariable secondTestField = testMethod.createVariable("secondTestField");
            secondTestField.setIsPrimitive();

            testMethod.setReturnType("", false);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.67f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod firstTestMethod = testClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = testClass.createMethod("secondTestMethod");


            ParsedVariable firstTestField = firstTestMethod.createParameter("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = firstTestMethod.createVariable("secondTestField");
            secondTestField.setIsPrimitive();
            firstTestMethod.setReturnType("", false);


            ParsedVariable thirdTestField = secondTestMethod.createParameter("thirdTestField");
            thirdTestField.setIsPrimitive();
            ParsedVariable fourthTestField = secondTestMethod.createVariable("fourthTestField");
            secondTestMethod.setReturnType("", false);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromMethodsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

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

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.33f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoMethodsTest() {
            ParsedProject projectTest = new ParsedProject();

            ClassData testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellFromFieldsTest {

        @Test
        public void retrieveSmellsFromFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            ParsedVariable firstTestField = testClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = testClass.createField("secondTestField");
            secondTestField.setIsPrimitive();
            ParsedVariable thirdTestField = testClass.createField("thirdTestField");
            ParsedVariable fourthTestField = testClass.createField("fourthTestField");


            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromFieldsOfMultipleClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = firstTestClass.createField("secondTestField");
            ParsedVariable thirdTestField = secondTestClass.createField("thirdTestField");
            ParsedVariable fourthTestField = secondTestClass.createField("fourthTestField");

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.25f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromNoFieldsTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = projectTest.createClass("nosejob", "testClass", "C:\\tests");

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

    public static class retrieveSmellsFromClasses {
        @Test
        public void retrieveSmellsFromClassesEmptyFieldsNotInMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");


            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoFields() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = firstTestClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = secondTestClass.createMethod("secondTestMethod");

            firstTestMethod.setReturnType("", true);
            secondTestMethod.setReturnType("", false);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.5f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesNoMethods() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = secondTestClass.createField("secondTestField");
            secondTestField.setIsPrimitive();

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(1f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }

        @Test
        public void retrieveSmellsFromClassesTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass firstTestClass = projectTest.createClass("nosejob", "firstTestClass", "C:\\tests");
            ParsedClass secondTestClass = projectTest.createClass("nosejob", "secondTestClass", "C:\\tests");

            ParsedMethod firstTestMethod = firstTestClass.createMethod("firstTestMethod");
            ParsedMethod secondTestMethod = secondTestClass.createMethod("secondTestMethod");

            ParsedVariable firstTestField = firstTestClass.createField("firstTestField");
            firstTestField.setIsPrimitive();
            ParsedVariable secondTestField = secondTestClass.createField("secondTestField");
            secondTestField.setIsPrimitive();


            ParsedVariable thirdTestField = firstTestMethod.createParameter("thirdTestField");
            ParsedVariable fourthTestField = firstTestMethod.createVariable("fourthTestField");
            firstTestMethod.setReturnType("", true);


            ParsedVariable sixthTestField = secondTestMethod.createParameter("sixthTestField");
            ParsedVariable seventhTestField = secondTestMethod.createVariable("seventhTestField");
            secondTestMethod.setReturnType("", true);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.66f, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
