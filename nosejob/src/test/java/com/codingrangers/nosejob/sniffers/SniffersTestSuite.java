/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.sniffers;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        ProjectSnifferTest.class,
        BloatedCodeSnifferTest.class,
        ViolationOfDataPrivacySnifferTest.class,
        InappropriateIntimacySnifferTest.class,
        FeatureEnvySnifferTest.class,
        GodComplexSnifferTest.class,
        DataOnlyClassesSnifferTest.class})

public class SniffersTestSuite {}
