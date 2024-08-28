package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.demo.test.steps.CLICommandHelperTestSteps;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.TafDataSourceDefinitionBuilder;

/**
 * Flows to execute scripts on a server
 */
public class CliCommandHelperFlows {

    private static final String EXECUTE_REMOTE_SCRIPT_FLOW = "ExecuteRemoteScript";

    @Inject
    private CLICommandHelperTestSteps cliCommandHelperTestSteps;

    /**
     * This flow executes a script on a remote host.
     * <p>
     * This flow contains the following test steps:
     * {@link CLICommandHelperTestSteps.StepIds#CREATE_SCRIPT_ON_REMOTE_HOST}
     * {@link CLICommandHelperTestSteps.StepIds#EXECUTE_SCRIPT_WITH_COMMAND}
     * {@link CLICommandHelperTestSteps.StepIds#DELETE_FOLDER_AND_SCRIPT_FROM_REMOTE_HOST}
     * @param dataSource the data source to use with the flow
     * @return the built flow
     */
    public TestStepFlow remoteScriptFlow(final TafDataSourceDefinitionBuilder<DataRecord> dataSource) {
        return flow(EXECUTE_REMOTE_SCRIPT_FLOW)
                .addTestStep(annotatedMethod(cliCommandHelperTestSteps, CLICommandHelperTestSteps.StepIds.CREATE_SCRIPT_ON_REMOTE_HOST))
                .addTestStep(annotatedMethod(cliCommandHelperTestSteps, CLICommandHelperTestSteps.StepIds.EXECUTE_SCRIPT_WITH_COMMAND))
                .addTestStep(annotatedMethod(cliCommandHelperTestSteps, CLICommandHelperTestSteps.StepIds.DELETE_FOLDER_AND_SCRIPT_FROM_REMOTE_HOST))
                .withDataSources(dataSource)
                .build();
    }
}
