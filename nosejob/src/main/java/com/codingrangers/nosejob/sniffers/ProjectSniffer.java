package com.codingrangers.nosejob.sniffers;

public class ProjectSniffer extends SnifferBase {
	public ProjectSniffer() {
		PrimitiveObsessionSniffer primitiveObsessionAnalyser = new PrimitiveObsessionSniffer();
		BloatedCodeSniffer bloatedCodeSniffer = new BloatedCodeSniffer();
		ViolationOfDataPrivacySniffer violationOfDataPrivacySniffer = new ViolationOfDataPrivacySniffer();
		this.analysers.add(primitiveObsessionAnalyser);
		this.analysers.add(bloatedCodeSniffer);
		this.analysers.add(violationOfDataPrivacySniffer);
	}
}
