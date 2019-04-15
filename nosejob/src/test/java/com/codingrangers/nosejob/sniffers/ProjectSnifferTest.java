<<<<<<< HEAD
package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.parser.data.ParsedProject;
=======
package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.parser.ParsedProject;
>>>>>>> Violation of Data privacy Tested
import com.codingrangers.nosejob.sniffers.ProjectSniffer;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class ProjectSnifferTest {
    public static class getProjectReportTest{
        @Test
<<<<<<< HEAD:nosejob/src/test/java/com/codingrangers/nosejob/reports/ProjectSnifferTest.java
        public void getProjectReport() {
            String primitiveObsessionName = "Primitive Obsession";
=======
        public void getProjectReportTest(){
            String bloatedCodeName = "Bloated Code";
>>>>>>> Violation of Data privacy implemented, need testing:nosejob/src/test/java/com/codingrangers/nosejob/sniffers/ProjectSnifferTest.java
            ParsedProject testProject = new ParsedProject();

            ProjectSniffer globalReport = new ProjectSniffer();
            globalReport.setProjectToAnalyse(testProject);

            assertEquals(bloatedCodeName, globalReport.getProjectReport().getSmellNames().get(0));
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(0).getTotalSmellSeverity(), 0.01);
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(1).getTotalSmellSeverity(), 0.01);
        }
    }
}
