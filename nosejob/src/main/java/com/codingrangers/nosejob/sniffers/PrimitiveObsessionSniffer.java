package com.codingrangers.nosejob.sniffers;

public class PrimitiveObsessionSniffer extends SnifferBase{
    public PrimitiveObsessionSniffer(){
        this.analyzers.add(new PrimitiveObsessionAnalyser());
    }
}
