package com.ericsson.cifwk.taf.demo.test.dataRecords;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface TestCaseDetails extends DataRecord{

    String getFeature();
    String getReqId();
    String getTestCaseId();
    String getTitle();
    String getDescription();
    String getPrecondition();
    String getComponent();
    String getType();
    String getPriority();
    String getExecutionType();
    Boolean getHeaderExists();
    String getOptionName();
}
