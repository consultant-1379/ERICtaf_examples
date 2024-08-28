package com.ericsson.cifwk.taf.demo.test.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LauncherOperator {

    private static final Logger logger = LoggerFactory.getLogger(LauncherOperator.class);

    /**
     * Dummy example of logging in to ENM Launcher
     * @param username
     * @param password
     * @return
     */
    public void login(String username, String password) {
        logger.info("Dummy example of logging in with username {} and password {}", username, password);
    }

    /**
     * Dummy example of logging out of ENM Launcher
     * @return
     */
    public void logout(){
        logger.info("Logging out");
    }
}
