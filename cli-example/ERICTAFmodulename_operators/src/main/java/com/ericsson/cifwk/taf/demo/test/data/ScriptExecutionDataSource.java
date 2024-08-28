/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.cifwk.taf.demo.test.data;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import com.ericsson.cifwk.taf.annotations.DataSource;
import com.google.common.io.Resources;

public class ScriptExecutionDataSource {

    @DataSource
    public List<Map<String, Object>> records() throws IOException {
        URL resource = Resources.getResource("scripts/cli-tool-remote-script.sh");
        String scriptContent = Resources.toString(resource, Charset.defaultCharset());

        List<Map<String, Object>> records = new ArrayList<>();
        Map<String, Object> record = new HashMap<>();
        List<String> scriptResult = new ArrayList<>();
        scriptResult.add("Output of same_file.txt contents");
        scriptResult.add("Creating text file");
        scriptResult.add("Appended String");
        record.put("scriptContent", scriptContent);
        record.put("scriptName", "TAF_CLI_Tool_Func_004_test_script.sh");
        record.put("scriptFolder", "test-scripts");
        record.put("scriptResult", scriptResult);
        records.add(record);
        return records;
    }
}
