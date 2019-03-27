package com.codingrangers.nosejob.models.data.parsing;

import static org.junit.Assert.assertEquals;

import com.codingrangers.nosejob.models.data.ClassData;

import org.junit.Test;

/**
 * ParsedClassTest
 */

public class ParsedClassTest {
    @Test
    public void nameIsSetCorrectly(){
        String className = "ClassName";
        String namePrefix = "name.prefix";
        String fullyQualifiedName = "name.prefix.ClassName";
        ClassData parsedClass = new ParsedClass(namePrefix, className, "");

        assertEquals(className, parsedClass.getName());
        assertEquals(fullyQualifiedName, parsedClass.getFullyQualifiedName());

        className = "OtherClassName";
        namePrefix = "other.name.prefix";
        fullyQualifiedName = "other.name.prefix.OtherClassName";
        parsedClass = new ParsedClass(namePrefix, className, "");

        assertEquals(className, parsedClass.getName());
        assertEquals(fullyQualifiedName, parsedClass.getFullyQualifiedName());

        int abc = 12;
        assertEquals(12, abc);
    }

    
}