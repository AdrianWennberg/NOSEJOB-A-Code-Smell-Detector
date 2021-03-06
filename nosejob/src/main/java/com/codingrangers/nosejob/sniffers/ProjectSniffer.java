/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.sniffers;

import java.util.Arrays;

public class ProjectSniffer extends SnifferBase {
	public ProjectSniffer() {
		PrimitiveObsessionSniffer primitiveObsessionAnalyser = new PrimitiveObsessionSniffer();
		BloatedCodeSniffer bloatedCodeSniffer = new BloatedCodeSniffer();
		ViolationOfDataPrivacySniffer violationOfDataPrivacySniffer = new ViolationOfDataPrivacySniffer();
		InappropriateIntimacySniffer inappropriateIntimacySniffer = new InappropriateIntimacySniffer();
		FeatureEnvySniffer featureEnvySniffer = new FeatureEnvySniffer();
		GodComplexSniffer godComplexSniffer = new GodComplexSniffer();
		DataOnlyClassesSniffer dataOnlyClassesSniffer = new DataOnlyClassesSniffer();
		this.analysers.addAll(Arrays.asList(primitiveObsessionAnalyser,
				bloatedCodeSniffer,
				violationOfDataPrivacySniffer,
				inappropriateIntimacySniffer,
				featureEnvySniffer,
				godComplexSniffer,
				dataOnlyClassesSniffer));
	}
}
