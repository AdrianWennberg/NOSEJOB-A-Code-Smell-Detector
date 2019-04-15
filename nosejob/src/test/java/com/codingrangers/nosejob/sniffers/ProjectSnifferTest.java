package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.parser.ParsedProject;
import com.codingrangers.nosejob.sniffers.ProjectSniffer;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class ProjectSnifferTest {
    public static class getProjectReportTest{
        @Test
        public void getProjectReportTest(){
            String bloatedCodeName = "Bloated Code";
            ParsedProject testProject = new ParsedProject();

            ProjectSniffer globalReport = new ProjectSniffer();
            globalReport.setProjectToAnalyse(testProject);

            assertEquals(bloatedCodeName, globalReport.getProjectReport().getSmellNames().get(0));
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(0).getTotalSmellSeverity(), 0.01);
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(1).getTotalSmellSeverity(), 0.01);
        }
    }
}
