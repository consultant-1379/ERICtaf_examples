package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.demo.test.steps.NetsimTestStep;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.DataSourceDefinitionBuilder;

/**
 * This class contains flows to verify netsim commands
 */
public class NetsimFlows {

    public static final String NETSIM_FLOW = "netsimFlow";

    @Inject
    private NetsimTestStep netsimTestStep;

    /**
     * This flow tests a range of netsim commands
     * <p>
     * This flow contains the following test steps:
     * {@link NetsimTestStep.StepIds#VERIFY_LISTING_ALL_SIMULATION}
     * {@link NetsimTestStep.StepIds#VERIFY_LISTING_NES_OF_SIMULATION}
     * {@link NetsimTestStep.StepIds#VERIFY_STOP_OF_NE}
     * {@link NetsimTestStep.StepIds#VERIFY_START_OF_NE}
     * {@link NetsimTestStep.StepIds#VERIFY_COMMAND_ON_NE}
     * {@link NetsimTestStep.StepIds#VERIFY_MULTIPLE_COMMAND_ON_NE}
     * {@link NetsimTestStep.StepIds#VERIFY_COMMAND_ON_SIMULATION}
     * {@link NetsimTestStep.StepIds#VERIFY_MULTIPLE_COMMAND_ON_SIMULATION}
     * @param dataSources the data sources to be used with this flow
     * @return the built test flow
     */
    public TestStepFlow netsimFlow(final DataSourceDefinitionBuilder... dataSources) {
        return flow(NETSIM_FLOW).addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_LISTING_ALL_SIMULATION))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_LISTING_NES_OF_SIMULATION))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_START_OF_NE))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_STOP_OF_NE))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_COMMAND_ON_NE))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_MULTIPLE_COMMAND_ON_NE))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_COMMAND_ON_SIMULATION))
                .addTestStep(annotatedMethod(netsimTestStep, NetsimTestStep.StepIds.VERIFY_MULTIPLE_COMMAND_ON_SIMULATION))
                .withDataSources(dataSources)
                .build();
    }
}

