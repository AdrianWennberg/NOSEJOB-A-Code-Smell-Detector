package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.sniffers.GlobalSniffer;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class GlobalSnifferTest {
    public static class getProjectReportTest{
        @Test
        public void getProjectReport() {
            String primitiveObsessionName = "Primitive Obsession";
            ParsedProject testProject = new ParsedProject();

			GlobalSniffer globalReport = new GlobalSniffer();
			globalReport.setProjectToAnalyse(testProject);

			assertEquals(primitiveObsessionName, globalReport.getProjectReport().getSmellNames().get(0));
		}
	}
}
