package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.sniffers.ProjectSniffer;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class ProjectSnifferTest {
    public static class getProjectReportTest{
        @Test
        public void getProjectReport() {
            String primitiveObsessionName = "Primitive Obsession";
            ParsedProject testProject = new ParsedProject();

            ProjectSniffer globalReport = new ProjectSniffer();
            globalReport.setProjectToAnalyse(testProject);

            assertEquals(primitiveObsessionName, globalReport.getProjectReport().getSmellNames().get(0));
        }
    }
}
