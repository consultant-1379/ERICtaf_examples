package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.demo.test.steps.CalcRestTestStep;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;

/**
 * Flows to test the Calculator REST Service
 */
public class CalcRestFlows {
    public static final String EXECUTE_CALC_FLOW = "ExecuteCalc";

    @Inject
    private CalcRestTestStep calcRestTestSteps;

    /**
     * This flow tests the operations of the calculator rest service.
     * <p>
     * This flow contains the following test step:
     * {@link CalcRestTestStep.StepIds#VERIFY_CALC}
     * @return the built test flow
     */
    public TestStepFlow calcFlow() {
        return flow(EXECUTE_CALC_FLOW)
                .addTestStep(annotatedMethod(calcRestTestSteps, CalcRestTestStep.StepIds.VERIFY_CALC))
                .build();
    }
}
