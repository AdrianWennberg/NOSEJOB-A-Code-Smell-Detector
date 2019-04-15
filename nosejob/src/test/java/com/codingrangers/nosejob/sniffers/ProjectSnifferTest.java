package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.parser.ParsedProject;
import com.codingrangers.nosejob.reports.SmellReport;
import com.codingrangers.nosejob.sniffers.ProjectSniffer;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ProjectSnifferTest {
    public static class getProjectReportTest{
        @Test
        public void getProjectReportTest(){
            String primitiveObsessionName = "Primitive Obsession";
            String bloatedCodeName = "Bloated Code";
            String violationOfDataPrivacyName = "Violation Of Data Privacy";
            ParsedProject testProject = new ParsedProject();

            ProjectSniffer globalReport = new ProjectSniffer();
            globalReport.setProjectToAnalyse(testProject);

            assertThat(globalReport.getProjectReport().getSmellNames(), containsInAnyOrder(primitiveObsessionName, bloatedCodeName, violationOfDataPrivacyName));
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(0).getTotalSmellSeverity(), 0.01);
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(1).getTotalSmellSeverity(), 0.01);
        }
    }
}
