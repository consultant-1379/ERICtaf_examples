package com.ericsson.cifwk.taf.demo.test.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkNodeOperator {

    static final String CPP_VERSION = "13.112.4";
    static final String CPP_NAMESPACE = "CPP_NODE_MODEL";
    static final String ERBS_VERSION = "4.1.189";
    static final String ERBS_NAMESPACE = "ERBS_NODE_MODEL";
    static final String ERBS_Connectivity_Info_Model_Version = "1.0.0";
    static final String Me_Context_Model_Version = "1.1.0";
    private boolean doesNodeExist = true;

    private static final Logger logger = LoggerFactory
            .getLogger(NetworkNodeOperator.class);

    public NetworkNodeOperator() {

    }

    /**
     * Creates node from parameters and default data. Then adds node.
     * @param nodeName
     * @param ipAddress
     * @return Node successfully added
     */
    public boolean add(final String nodeName, final String ipAddress) {
        Node node = new Node();
        node.setName(nodeName);
        node.setIpAddress(ipAddress);
        node.setCppVersion(CPP_VERSION);
        node.setCppNamespace(CPP_NAMESPACE);
        node.setErbsVersion(ERBS_VERSION);
        node.setErbsNamespace(ERBS_NAMESPACE);
        node.setErbsConnectivityInfoModelVersion(ERBS_Connectivity_Info_Model_Version);
        node.setMeContextModelVersion(Me_Context_Model_Version);
        return add(node);
    }

    /**
     * Add Node to ENM
     * @param node
     * @return Node successfully added
     */
    public boolean add(final Node node) {
        logger.info("Adding node {}", node.getName());
        return doesNodeExist(node.getName());
    }

    /**
     * Sync node in ENM
     * @param nodeName
     */
    public void sync(final String nodeName) {
        logger.info("Syncing node {}", nodeName);
    }

    /**
     * Delete node from ENM
     * @param nodeName
     * @return Node successfully deleted
     */
    public boolean delete(String nodeName) {
        logger.info("Deleting Node {}", nodeName);
        return true;
    }

    /**
     * Check if node exists
     * @param nodeName
     * @return doesNodeExist
     */
    public boolean doesNodeExist(String nodeName) {
        logger.info("Dummy example of checking if a Node {} exists", nodeName);
        return doesNodeExist;
    }


}
