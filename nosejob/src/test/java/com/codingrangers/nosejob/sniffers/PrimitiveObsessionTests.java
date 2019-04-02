package com.codingrangers.nosejob.sniffers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedMethod;
import com.codingrangers.nosejob.parser.ParsedVariable;
import org.junit.Test;

public class PrimitiveObsessionInstanceTests {

    @Test
    public void countSeverityInClassFieldsTypesCorrectly() {
        ParsedClass mockedCodeData = new ParsedClass("", "", "");

        ParsedVariable primitiveField = new ParsedVariable(mockedCodeData, "primitiveVariable");
        primitiveField.setIsPrimitive();
        ParsedVariable nonPrimitiveField = new ParsedVariable(mockedCodeData, "nonPrimitiveVariable");

        mockedCodeData.addField(primitiveField);
        mockedCodeData.addField(nonPrimitiveField);

        PrimitiveObsessionInstance primitiveObsession = new PrimitiveObsessionInstance(mockedCodeData);

        assertEquals(1, primitiveObsession.countSeverityInClassFieldsTypes());
    }
/**TODO*/
//    @Test
//    public void countSeverityInClassMethodsReturnsCorrectly() {
//        ParsedClass mockedCodeData = new ParsedClass("", "", "");
//
//        String primitiveMethodSignature = "test";
//        ParsedMethod primitiveMethod = new ParsedMethod(mockedCodeData, primitiveMethodSignature);
//
//        ParsedVariable primitiveField = new ParsedVariable(mockedCodeData, "primitiveVariable");
//        primitiveField.setIsPrimitive();
//        primitiveMethod.addReturnType(primitiveField);
//
//        mockedCodeData.addMethod(primitiveMethod);
//
//        PrimitiveObsessionInstance primitiveObsession = new PrimitiveObsessionInstance(mockedCodeData);
//
//        assertEquals(1, primitiveObsession.countSeverityInMethodsReturnTypes());
//    }
}
