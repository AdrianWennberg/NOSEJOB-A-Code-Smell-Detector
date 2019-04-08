package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.SmellReportBody;

public class BloatedCodeSniffer extends GeneralSniffer {
    @Override
    public SmellReportBody getSmellReport() {
        return null;
    }

    @Override
    public void setProjectToAnalyse(ProjectData codeData) {

    }
}
