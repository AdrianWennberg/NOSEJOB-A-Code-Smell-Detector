<<<<<<< HEAD
package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.parser.data.ParsedProject;
=======
package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.parser.ParsedProject;
<<<<<<< HEAD
>>>>>>> Violation of Data privacy Tested
=======
import com.codingrangers.nosejob.reports.SmellReport;
>>>>>>> God Complex started
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
<<<<<<< HEAD:nosejob/src/test/java/com/codingrangers/nosejob/reports/ProjectSnifferTest.java
        public void getProjectReport() {
            String primitiveObsessionName = "Primitive Obsession";
=======
        public void getProjectReportTest(){
            String primitiveObsessionName = "Primitive Obsession";
            String bloatedCodeName = "Bloated Code";
<<<<<<< HEAD
>>>>>>> Violation of Data privacy implemented, need testing:nosejob/src/test/java/com/codingrangers/nosejob/sniffers/ProjectSnifferTest.java
=======
            String violationOfDataPrivacyName = "Violation Of Data Privacy";
<<<<<<< HEAD
>>>>>>> God Complex started
=======
            String inappropriateIntimacyName = "Inappropriate Intimacy";
<<<<<<< HEAD
>>>>>>> God Complex Sniffer Tested
=======
            String featureEnvyName = "Feature Envy";
<<<<<<< HEAD
>>>>>>> Data only classes Sniffer Tested
=======
            String godComplexName = "God Complex";
>>>>>>> opps
            ParsedProject testProject = new ParsedProject();

            ProjectSniffer globalReport = new ProjectSniffer();
            globalReport.setProjectToAnalyse(testProject);

            assertThat(globalReport.getProjectReport().getSmellNames(), containsInAnyOrder(primitiveObsessionName,
                    bloatedCodeName,
                    violationOfDataPrivacyName,
                    inappropriateIntimacyName,
                    featureEnvyName,
                    godComplexName));
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(0).getTotalSmellSeverity(), 0.01);
            assertEquals(0f, globalReport.getProjectReport().getSmellReports().get(1).getTotalSmellSeverity(), 0.01);
        }
    }
}
