package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IClassData;
import com.codingrangers.nosejob.models.IMethodData;
import com.codingrangers.nosejob.models.IProjectData;
import com.codingrangers.nosejob.models.IVariableData;
import com.codingrangers.nosejob.parser.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class PrimitiveObsessionSnifferTest {

    public static class retrieveSmellsFromMethodTests{

        @Test
        public void retrieveSmellsFromMethodsTest() {
            IProjectData projectTest = new ParsedProject();

            IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            IMethodData testMethod = new ParsedMethod(testClass, "testMethod");

            IVariableData firstTestField = new ParsedVariable((ParsedCodeUnit) testMethod, "firstTestField");
            IVariableData secondTestField = new ParsedVariable((ParsedCodeUnit) testMethod, "secondTestField");
            IVariableData thirdTestField = new ParsedVariable((ParsedCodeUnit) testMethod, "thirdTestField");
            IVariableData forthTestField = new ParsedVariable((ParsedCodeUnit) testMethod, "forthTestField");
            IVariableData fifthTestField = new ParsedVariable((ParsedCodeUnit) testMethod, "fifthTestField");

            ((ParsedVariable) firstTestField).setIsPrimitive();
            ((ParsedVariable) thirdTestField).setIsPrimitive();
            ((ParsedVariable) fifthTestField).setIsPrimitive();

            ((ParsedProject) projectTest).addClass(testClass);

            ((ParsedClass) testClass).addMethod(testMethod);

            ((ParsedMethod) testMethod).addParameter(firstTestField);
            ((ParsedMethod) testMethod).addParameter(secondTestField);
            ((ParsedMethod) testMethod).addVariable(thirdTestField);
            ((ParsedMethod) testMethod).addVariable(forthTestField);
            ((ParsedMethod) testMethod).addReturnType(fifthTestField);

            PrimitiveObsessionSniffer prObsSnifferTest = new PrimitiveObsessionSniffer();
            prObsSnifferTest.setProjectToAnalyse(projectTest);

            assertEquals(0.6, prObsSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }

//    public static class retrieveSmellFromFieldsTest {
//
//    }

//    public static class retrieveSmellFromClasses {
//
//    }
}
