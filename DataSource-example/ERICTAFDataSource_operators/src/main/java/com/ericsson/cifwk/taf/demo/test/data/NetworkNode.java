package com.ericsson.cifwk.taf.demo.test.data;

import com.ericsson.cifwk.taf.datasource.DataRecord;

public interface NetworkNode extends DataRecord{
    String getUsername();
    String getPassword();
    String getManagedElementId();
    String getIpAddress();
}
