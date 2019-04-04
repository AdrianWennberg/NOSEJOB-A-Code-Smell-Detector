package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IClassData;
import com.codingrangers.nosejob.models.IMethodData;
import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedMethod;
import org.junit.Test;

public class PrimitiveObsessionAnalyserTest {
    @Test
    public void testMethodDiagnoser(){
        IClassData testClass = new ParsedClass("nosejob", "testClass", "C:\\tests");

        IMethodData testMethod = new ParsedMethod(testClass, "testMethod");
    }
}
