package com.codingrangers.nosejob.sniffers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        BloatedCodeSnifferTest.class,
        ViolationOfDataPrivacySnifferTest.class,
        PrimitiveObsessionSnifferTest.class,
        InappropriateIntimacySnifferTest.class})

public class SniffersTestSuite {}
