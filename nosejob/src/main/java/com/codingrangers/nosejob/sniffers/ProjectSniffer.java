package com.codingrangers.nosejob.sniffers;

public class ProjectSniffer extends SnifferBase {
	public ProjectSniffer() {
		PrimitiveObsessionSniffer primitiveObsessionAnalyser = new PrimitiveObsessionSniffer();
		this.analysers.add(primitiveObsessionAnalyser);
	}
}
