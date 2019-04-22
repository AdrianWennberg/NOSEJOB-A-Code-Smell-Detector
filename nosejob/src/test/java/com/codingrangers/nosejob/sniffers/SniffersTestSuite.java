package com.codingrangers.nosejob.sniffers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ProjectSnifferTest.class,
        BloatedCodeSnifferTest.class,
        ViolationOfDataPrivacySnifferTest.class,
<<<<<<< HEAD
//        PrimitiveObsessionSnifferTest.class,
        InappropriateIntimacySnifferTest.class,
        FeatureEnvySnifferTest.class,
        GodComplexSnifferTest.class,
        DataOnlyClassesSnifferTest.class})
=======
        PrimitiveObsessionSnifferTest.class,
        InappropriateIntimacySnifferTest.class,
        FeatureEnvySnifferTest.class})
>>>>>>> Data only classes Sniffer Tested

public class SniffersTestSuite {}

