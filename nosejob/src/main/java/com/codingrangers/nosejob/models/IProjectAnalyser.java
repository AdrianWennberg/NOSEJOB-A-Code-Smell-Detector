package com.codingrangers.nosejob.models;

public interface IProjectAnalyser {
    void setProjectToAnalyse(IProjectData projectData);

    IProjectReport getProjectReport();
}
