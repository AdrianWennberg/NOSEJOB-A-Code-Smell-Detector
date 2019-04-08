package com.codingrangers.nosejob.sniffers;

public class GlobalSniffer extends SnifferBase {
	public GlobalSniffer() {
		PrimitiveObsessionSniffer primitiveObsessionAnalyser = new PrimitiveObsessionSniffer();
		this.analysers.add(primitiveObsessionAnalyser);
	}
}
