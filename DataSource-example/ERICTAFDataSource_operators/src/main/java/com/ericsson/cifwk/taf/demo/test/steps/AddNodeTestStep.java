package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.datasource.DataRecord;
import com.ericsson.cifwk.taf.datasource.DataRecordBuilder;
import com.ericsson.cifwk.taf.demo.test.data.NetworkNode;
import com.ericsson.cifwk.taf.demo.test.operators.LauncherOperator;
import com.ericsson.cifwk.taf.demo.test.operators.NetworkNodeOperator;

/**
 * Created by eendjor on 08/06/2016.
 *
 * These are dummy test flows that use ENM functionality as an example
 * The flows do not preform any functionality and are only used for purposes of the example.
 */
public class AddNodeTestStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNodeTestStep.class);

    @Inject
    private Provider<NetworkNodeOperator> networkNodeOperatorProvider;

    @Inject
    private Provider<LauncherOperator> launcherOperatorProvider;

    /**
     * Add node using a single data source that contains both node and user data
     * This is not the best approach as user data is consistent for all nodes
     * but needs to be repeated several times
     * @param nodeName Name of node
     * @param ipAddress IPAddress associated with node
     * @param username for login
     * @param password for login
     */
    @TestStep(id = StepIds.ADD_NODE)
    public void addNodeSingleDataSource(
            @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName,
            @Input(Parameters.IP_ADDRESS) String ipAddress,
            @Input(Parameters.USERNAME) String username,
            @Input(Parameters.PASSWORD) String password) {
        LOGGER.info("User {} and node {} are being provided", username, nodeName);
        LauncherOperator launcherOperator = launcherOperatorProvider.get();
        launcherOperator.login(username, password);
        assertThat(networkNodeOperatorProvider.get().add(nodeName, ipAddress)).isTrue();
        LOGGER.info("Deleting node {}", nodeName);
        assertThat(networkNodeOperatorProvider.get().delete(nodeName)).isTrue();
        launcherOperator.logout();
    }

    /**
     * This test step demonstrates the use of multiple data sources in one test case
     * During run time these two data sources are combined into a single data
     * source similar to all_data data source
     * It also shows how to set up a custom data source using TestContext
     * A data source is created with nodes that have been added
     * This test step adds a node
     * @param nodeName Name of node
     * @param ipAddress IPAddress associated with node
     * @param username for login
     * @param password for login
     */
    @TestStep(id = StepIds.VERIFY_ADD_NODE)
    public DataRecord verifyAddingNodeBehavesAsExpected(
            @Input(Parameters.USERNAME) String username,
            @Input(Parameters.PASSWORD) String password,
            @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName,
            @Input(Parameters.IP_ADDRESS) String ipAddress) {
        LOGGER.info("User {} and node {} are being provided", username, nodeName);
        LauncherOperator launcherOperator = launcherOperatorProvider.get();
        launcherOperator.login(username, password);
        assertThat(networkNodeOperatorProvider.get().add(nodeName, ipAddress)).isTrue();
        return new DataRecordBuilder().setField(Parameters.MANAGED_ELEMENT_ID, nodeName).build();
    }

    /**
     * The next two tests steps share the data source addedNode which was created in the previous test step
     * It is possible to share a single data source between multiple test step methods
     * This test cases syncs node that were added in verifyAddingNodeBehavesAsExpected test step
     * @param nodeName
     */
    @TestStep(id = StepIds.VERIFY_NODE_IS_SYNCED)
    public void verifyNodeIsSynced(
            @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName) {
        LOGGER.info("Syncing Node {}", nodeName);
        networkNodeOperatorProvider.get().sync(nodeName);
    }

    /**
     * This test step deletes the nodes that were added in the verifyAddingNodeBehavesAsExpected test step
     * @param nodeName
     */
    @TestStep(id = StepIds.VERIFY_NODE_IS_DELETED)
    public void verifyNodeIsDeleted(
            @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName) {
        LOGGER.info("Deleting Node {}", nodeName);
        assertThat(networkNodeOperatorProvider.get().delete(nodeName)).isTrue();
    }

    /**
     * VerifyNodeExists demonstrates the use of filtering
     * The nodes data source has 6 rows of data but we only want to run the test for particular data sources
     * ie ManagedElementId that start with LTE01
     * There are two rows that have this criteria therefore the test will run twice
     * @param username
     * @param password
     * @param nodeName
     * @param ipAddress
     */
    @TestStep(id = StepIds.VERIFY_NODE_EXISTS)
    public void verifyNodeExists(@Input(Parameters.USERNAME) String username,
                                 @Input(Parameters.PASSWORD) String password,
                                 @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName,
                                 @Input(Parameters.IP_ADDRESS) String ipAddress) {
        LOGGER.info("User {} and node {} are being provided", username, nodeName);
        LauncherOperator launcherOperator = launcherOperatorProvider.get();
        launcherOperator.login(username, password);
        assertThat(networkNodeOperatorProvider.get().doesNodeExist(nodeName)).isTrue();
        launcherOperator.logout();
    }

    /**
     * This test step shows how through the user of filtering you can determine the data run by a particular vUser
     * The node_with_vusers data source has a particular vUser associated with a particular node
     * For example vUser "1" is associated with node LTE07ERBS00153 and LTE01ERBS00102
     * So vUser "1" will use this data when running the tests
     * @param username
     * @param password
     * @param nodeName
     * @param ipAddress
     */
    @TestStep(id = StepIds.ADD_NODES)
    public void addNodes(@Input(Parameters.USERNAME) String username,
                         @Input(Parameters.PASSWORD) String password,
                         @Input(Parameters.MANAGED_ELEMENT_ID) String nodeName,
                         @Input(Parameters.IP_ADDRESS) String ipAddress) {
        LauncherOperator launcherOperator = launcherOperatorProvider.get();
        launcherOperator.login(username, password);
        assertThat(networkNodeOperatorProvider.get().add(nodeName, ipAddress)).isTrue();
        assertThat(networkNodeOperatorProvider.get().delete(nodeName)).isTrue();
        launcherOperator.logout();
    }

    /**
     * This test step shows how through the user of filtering you can determine the data run by a particular vUser
     * The node_with_vusers data source has a particular vUser associated with a particular node
     * For example vUser "1" is associated with node LTE07ERBS00153 and LTE01ERBS00102
     * So vUser "1" will use this data when running the tests
     * @param node
     */
    @TestStep(id = StepIds.ADD_NETWORK_NODE)
    public void addNodes(@Input(Parameters.NODES_TO_ADD) NetworkNode node) {
        LauncherOperator launcherOperator = launcherOperatorProvider.get();
        launcherOperator.login(node.getUsername(), node.getPassword());
        assertThat(networkNodeOperatorProvider.get().add(node.getManagedElementId(), node.getIpAddress())).isTrue();
        assertThat(networkNodeOperatorProvider.get().delete(node.getManagedElementId())).isTrue();
        launcherOperator.logout();
    }

    public static class StepIds{
        public static final String VERIFY_NODE_IS_DELETED = "verifyNodeIsDeleted";
        public static final String ADD_NODE = "addNodeSingleDataSource";
        public static final String VERIFY_ADD_NODE = "verifyAddingNodeBehavesAsExpected";
        public static final String VERIFY_NODE_IS_SYNCED = "verifyNodeIsSynced";
        public static final String VERIFY_NODE_EXISTS = "verifyNodeExists";
        public static final String ADD_NODES = "addNodes";
        public static final String ADD_NETWORK_NODE = "addNetworkNode";

        private StepIds(){}
    }

    public static class Parameters{
        public static final String NODES_TO_ADD = "nodesToAdd";
        public static final String MANAGED_ELEMENT_ID = "ManagedElementId";
        public static final String IP_ADDRESS = "ipAddress";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";

        private Parameters(){}
    }
}
