package com.codingrangers.nosejob.sniffers;

public class ProjectSniffer extends SnifferBase {
	public ProjectSniffer() {
		PrimitiveObsessionSniffer primitiveObsessionAnalyser = new PrimitiveObsessionSniffer();
		BloatedCodeSniffer bloatedCodeSniffer = new BloatedCodeSniffer();
		this.analysers.add(primitiveObsessionAnalyser);
		this.analysers.add(bloatedCodeSniffer);
	}
}
