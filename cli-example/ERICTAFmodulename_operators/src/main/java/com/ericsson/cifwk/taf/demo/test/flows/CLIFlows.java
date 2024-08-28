package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.demo.test.steps.CLITestSteps;
import com.ericsson.cifwk.taf.demo.test.steps.CloseResourcesTestSteps;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.TafDataSourceDefinitionBuilder;

/**
 * Flows to execute commands on a remote host
 */
public class CLIFlows {

    private static final String CLI_FLOW = "CLI_Flow";
    private static final String SHELL_COMMANDS_FLOW = "shellCommandFlow";

    @Inject
    private CLITestSteps cliTestSteps;

    @Inject
    private CloseResourcesTestSteps closeResourcesTestSteps;

    /**
     * This flow verifies command execution on a remote host
     * <p>
     * This flow contains the following test steps:
     * {@link CLITestSteps.StepIds#VERIFY_COMMANDS_EXECUTED}
     * {@link CLITestSteps.StepIds#VERIFY_PROMPT_DETAILS}
     * {@link CLITestSteps.StepIds#VERIFY_COMMANDS_EXECUTED_SEQUENTIALLY}
     * @param dataSources the data sources to use with this flow
     * @return the built test flow
     */
    public TestStepFlow cliFlow(final TafDataSourceDefinitionBuilder<DataRecord>... dataSources) {
        return flow(CLI_FLOW).addTestStep(annotatedMethod(cliTestSteps, CLITestSteps.StepIds.VERIFY_COMMANDS_EXECUTED))
                .addTestStep(annotatedMethod(cliTestSteps, CLITestSteps.StepIds.VERIFY_PROMPT_DETAILS))
                .addTestStep(annotatedMethod(cliTestSteps, CLITestSteps.StepIds.VERIFY_COMMANDS_EXECUTED_SEQUENTIALLY)).withDataSources(dataSources)
                .build();
    }

    /**
     * This flow verifies execution of commands on a remote host
     * <p>
     * This flow contains the following test step:
     * {@link CloseResourcesTestSteps.StepIds#VERIFY_SHELL_COMMANDS}
     * @param dataSource
     * @return
     */
    public TestStepFlow shellCommandFlow(final TafDataSourceDefinitionBuilder<DataRecord> dataSource) {
        return flow(SHELL_COMMANDS_FLOW)
                .addTestStep(annotatedMethod(closeResourcesTestSteps, CloseResourcesTestSteps.StepIds.VERIFY_SHELL_COMMANDS))
                .withDataSources(dataSource)
                .build();
    }
}
