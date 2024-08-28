package com.ericsson.cifwk.taf.demo.test.cases;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.NodeFlows;
import com.ericsson.cifwk.taf.demo.test.data.NetworkNode;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.api.ExceptionHandler;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

import org.testng.annotations.Test;

import javax.inject.Inject;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.*;

/**
 * These are dummy test cases that use ENM functionality as an example
 * The test do not preform any functionality and are only used for purposes of the example
 * You can find full implementations of these test in taf-tor-test
 * The data driven approaches outlined in these examples are not ENM specific
 */
public class AddNodeTest extends TafTestBase {

    public static final String ADD_NODE_SINGLE_DATA_SOURCE = "addNodeSingleDataSource";
    public static final String ADD_NODE_SCENARIO = "addNodeScenario";
    public static final String ALL_DATA = "allData";

    public static final String ADD_NODE_MULTIPLE_DATA_SOURCE = "addNodeMultipleDataSource";
    public static final String MULTIPLE_DATA_SOURCE_SCENARIO = "multipleDataSourceScenario";
    public static final String NODES = "nodes";
    public static final String USERS = "users";
    public static final String ADDED_NODE = "addedNode";

    public static final String VERIFY_NODE_EXISTS = "verifyNodeExists";
    public static final String VERIFY_NODE_SCENARIO = "verifyNodeScenario";

    public static final String ADD_NODES = "addNodes";
    public static final String ADD_NODES_SCENARIO = "addNodesScenario";
    public static final String NODES_WITH_VUSERS = "node_with_vusers";

    @Inject
    private NodeFlows nodeFlows;

    private TestScenarioRunner runner = runner().withExceptionHandler(ExceptionHandler.PROPAGATE).withListener(new LoggingScenarioListener()).build();

    /**
     * This scenario can also be run using addNodeSingleDataSource suite file
     */
    @TestId(id = ADD_NODE_SINGLE_DATA_SOURCE)
    @Test
    public void addNodeSingleDataSource() {

        TestScenario scenario = scenario(ADD_NODE_SCENARIO)
                .addFlow(nodeFlows.addNodeFlow(dataSource(ALL_DATA)))
                .build();

        runner.start(scenario);
    }

    /**
     * This scenario can also be run using addNodeMultipleDataSource suite file
     */
    @TestId(id = ADD_NODE_MULTIPLE_DATA_SOURCE)
    @Test
    public void addNodeMultipleDataSource() {

        TestScenario scenario = scenario(MULTIPLE_DATA_SOURCE_SCENARIO)
                .addFlow(nodeFlows.verifyAddNodeFlow("addedNode",
                        dataSource(NODES),
                        dataSource(USERS)))
                .addFlow(nodeFlows.verifySyncAndDeleteFlow(dataSource(ADDED_NODE)))
                .build();

        runner.start(scenario);
    }

    /**
     * This scenario can also be run using VerifyNodeExistsWithFiltering suite file
     */
    @TestId(id = VERIFY_NODE_EXISTS)
    @Test
    public void verifyNodeExists() {

        TestScenario scenario = scenario(VERIFY_NODE_SCENARIO)
                .addFlow(nodeFlows.verifyNodeFlow(
                        dataSource(NODES).withFilter("ManagedElementId.startsWith(\"LTE01\")"),
                        dataSource(USERS)))
                .build();

        runner.start(scenario);
    }

    /**
     * This scenario can be run using AddNodeMultipleVusers suite file and should be run using maven
     */
    @TestId(id = ADD_NODES)
    @Test
    public void addNodesWithMultipleVusers() {

        TestScenario scenario = scenario(ADD_NODES_SCENARIO)
                .addFlow(nodeFlows.addNodeFlow(
                        dataSource(NODES_WITH_VUSERS).withFilter("$VUSER == vuser")))
                .build();

        runner.start(scenario);
    }

    @TestId(id = "Add Nodes with binding of data source name")
    @Test
    public void addNodesWithBindingDataSource(){
        TestScenario scenario = scenario("Add nodes with binding of data source name")
                .addFlow(nodeFlows.addNetworkNodeFlow(dataSource(ALL_DATA, NetworkNode.class)))
                .build();

        runner.start(scenario);
    }
}
