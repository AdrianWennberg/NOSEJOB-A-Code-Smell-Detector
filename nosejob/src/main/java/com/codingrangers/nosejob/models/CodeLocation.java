package com.codingrangers.nosejob.models;

public interface CodeLocation {

    int getStartLineNumber();

    int getEndLineNumber();

    int getLineCount();

    String getFilePath();
}
