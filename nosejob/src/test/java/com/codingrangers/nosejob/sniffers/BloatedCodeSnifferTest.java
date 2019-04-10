package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedMethod;
import com.codingrangers.nosejob.parser.ParsedProject;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class BloatedCodeSnifferTest {
    public static class retrieveSmellsFromMethodTests {

        @Test
        public void retrieveSmellsFromMethodTest() {
            ParsedProject projectTest = new ParsedProject();

            ParsedClass testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

            ParsedMethod testMethod = mock(ParsedMethod.class);
            when(testMethod.getLineCount()).thenReturn(11);
            when(testMethod.getClassName()).thenReturn(testClass.getName());
            when(testMethod.getFullyQualifiedName()).thenReturn("mockedMethod");
            projectTest.addClass(testClass);

            testClass.addMethod(testMethod);

            BloatedCodeSniffer blCdSnifferTest = new BloatedCodeSniffer();
            blCdSnifferTest.setProjectToSniff(projectTest);

            assertEquals(0.16f, blCdSnifferTest.getSmellReport().getTotalSmellSeverity(), 0.01);
        }
    }
}
