package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.demo.test.data.NetworkNode;
import com.ericsson.cifwk.taf.demo.test.steps.AddNodeTestStep;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;
import com.ericsson.cifwk.taf.scenario.api.TafDataSourceDefinitionBuilder;

/**
 * Created by ethomev on 21/09/16
 *
 * These are dummy test flows that use ENM functionality as an example
 * The flows do not preform any functionality and are only used for purposes of the example.
 */
public class NodeFlows {

    public static final String ADD_NODE_FLOW = "addNodeFlow";
    public static final String VERIFY_ADDING_NODE_FLOW = "verifyAddingNodeBehavesAsExpected";
    public static final String VERIFY_SYNC_AND_DELETE_FLOW = "verifySyncAndDelete";
    public static final String VERIFY_NODE_FLOW = "verifyNodeFlow";

    @Inject
    private AddNodeTestStep addNodeTestStep;

    /**
     * This flow allows a tester to add a node to the ENM system.
     * <p>
     * This flow contains the following test step:
     * {@link AddNodeTestStep.StepIds#ADD_NODE}
     * @param dataSources one or more data sources to use in the flow
     * @return The built flow
     */
    public TestStepFlow addNodeFlow(final TafDataSourceDefinitionBuilder<DataRecord>... dataSources) {
        return flow(ADD_NODE_FLOW)
                .addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.ADD_NODE))
                .withDataSources(dataSources)
                .build();
    }

    /**
     * This flow allows a tester to synchronize and delete a node on the ENM system.
     * <p>
     * This flow contains the following test steps:
     * {@link AddNodeTestStep.StepIds#VERIFY_NODE_IS_SYNCED}
     * {@link AddNodeTestStep.StepIds#VERIFY_NODE_IS_DELETED}
     * @param dataSource the data source to use in the flow
     * @return the built flow
     */
    public TestStepFlow verifySyncAndDeleteFlow(final TafDataSourceDefinitionBuilder<DataRecord> dataSource) {
        return flow(VERIFY_SYNC_AND_DELETE_FLOW)
                .addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.VERIFY_NODE_IS_SYNCED))
                .addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.VERIFY_NODE_IS_DELETED))
                .withDataSources(dataSource)
                .build();
    }

    /**
     * This flow allows a tester to verify that a node has been added to the ENM system.
     * <p>
     * This flow contains the following test step:
     * {@link AddNodeTestStep.StepIds#VERIFY_ADD_NODE}
     * @param outputDataSource the name of the datasource to collect the results of the test step to
     * @param dataSources one or more data sources to use in the flow
     * @return the built flow
     */
    public TestStepFlow verifyAddNodeFlow(final String outputDataSource, final TafDataSourceDefinitionBuilder<DataRecord>... dataSources) {
        return flow(VERIFY_ADDING_NODE_FLOW).addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.VERIFY_ADD_NODE)
                .collectResultToDatasource(outputDataSource))
                .withDataSources(dataSources)
                .build();
    }

    /**
     * This flow allows a tester to verify that a node exists on the ENM system.
     * <p>
     * This flow contains the following test step:
     * {@link AddNodeTestStep.StepIds#VERIFY_NODE_EXISTS}
     * @param dataSources one or more data sources to use in the flow
     * @return the built flow
     */
    public TestStepFlow verifyNodeFlow(final TafDataSourceDefinitionBuilder<DataRecord>... dataSources) {
        return flow(VERIFY_NODE_FLOW).addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.VERIFY_NODE_EXISTS)).withDataSources(dataSources)
                .build();
    }

    /**
     * This flow allows a tester to verify that a node exists on the ENM system.
     * The Test Steps has the data source name hard coded so the data source passed has to be bound to it.
     * @param dataSource
     * @return
     */
    public TestStepFlow addNetworkNodeFlow(final TafDataSourceDefinitionBuilder<NetworkNode> dataSource) {
        return flow(ADD_NODE_FLOW)
                .addTestStep(annotatedMethod(addNodeTestStep, AddNodeTestStep.StepIds.ADD_NETWORK_NODE))
                .withDataSources(dataSource.bindTo(AddNodeTestStep.Parameters.NODES_TO_ADD))
                .build();
    }
}
