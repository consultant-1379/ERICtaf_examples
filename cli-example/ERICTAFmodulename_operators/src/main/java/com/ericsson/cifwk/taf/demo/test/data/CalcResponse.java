package com.ericsson.cifwk.taf.demo.test.data;

public class CalcResponse {

    private String versionDescription;
    private String result;
    private String operatorDescription;
    private int exitCode;

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String description) {
        this.versionDescription = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperatorDescription() {
        return operatorDescription;
    }

    public void setOperatorDescription(String result) {
        this.operatorDescription = result;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public String toString() {
        return "CalcResponse [versionDescription=" + versionDescription + ", operatorDescription="
                + operatorDescription + ", exitCode=" + exitCode + "]";
    }
}
