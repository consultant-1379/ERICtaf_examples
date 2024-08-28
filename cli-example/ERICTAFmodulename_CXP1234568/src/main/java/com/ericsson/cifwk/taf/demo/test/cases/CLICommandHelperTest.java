/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2015
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.datasource.TafDataSources.fromClass;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.datasource.TestDataSource;
import com.ericsson.cifwk.taf.demo.test.data.ScriptExecutionDataSource;
import com.ericsson.cifwk.taf.demo.test.flows.CliCommandHelperFlows;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

public class CLICommandHelperTest extends TafTestBase {

    public static final String EXAMPLE_TAF_CLICOMMANDHELPER_001 = "EXAMPLE_TAF_CLICommandHelper_001";
    public static final String RECORDS_DS = "records";
    public static final String REMOTE_SCRIPT_EXECUTION_SCENARIO = "RemoteScriptExecutionScenario";

    @Inject
    private CliCommandHelperFlows cliCommandHelperFlows;

    @Inject
    TestContext testContext;

    @TestId(id = EXAMPLE_TAF_CLICOMMANDHELPER_001)
    @Test
    public void testRemoteScriptExecution() {

        TestDataSource<DataRecord> dataSource = fromClass(ScriptExecutionDataSource.class);
        testContext.addDataSource(RECORDS_DS, dataSource);

        TestScenario scenario = scenario(REMOTE_SCRIPT_EXECUTION_SCENARIO)
                .addFlow(cliCommandHelperFlows.remoteScriptFlow(dataSource(RECORDS_DS)))
                .build();

        TestScenarioRunner runner = runner()
                .withListener(new LoggingScenarioListener())
                .build();

        runner.start(scenario);
    }
}
