package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.demo.test.steps.CalcCLITestSteps;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.TafDataSourceDefinitionBuilder;

/**
 * Flows to test the cli calculator
 */
public class CalcCliFlows {

    private static final String EXECUTE_CALC_COMMAND_FLOW = "CalcFlow";

    @Inject
    private CalcCLITestSteps calcCLITestSteps;

    /**
     * This flow tests the operator of the calculator
     * <p>
     * This flow contains the following test step:
     * {@link CalcCLITestSteps.StepIds#VERIFY_CALC_COMMAND}
     * @param dataSource the data source to use with this flow
     * @return the built flow
     */
    public TestStepFlow executeCalcFlow(final TafDataSourceDefinitionBuilder<DataRecord> dataSource) {
        return flow(EXECUTE_CALC_COMMAND_FLOW)
                .addTestStep(annotatedMethod(calcCLITestSteps, CalcCLITestSteps.StepIds.VERIFY_CALC_COMMAND))
                .withDataSources(dataSource)
                .build();
    }
}
