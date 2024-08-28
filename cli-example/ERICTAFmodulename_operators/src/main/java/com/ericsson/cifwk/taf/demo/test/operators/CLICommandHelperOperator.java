package com.ericsson.cifwk.taf.demo.test.operators;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.cli.CLICommandHelper;

public class CLICommandHelperOperator {

    private CLICommandHelper cliCommandHelper;
    private int exitValue;
    private String stdOut;
    private Host host;
    private static final String TEST_SERVER = "testServer";

    public void initializeShell() {
        host = DataHandler.getHostByName(TEST_SERVER);
        cliCommandHelper = new CLICommandHelper(host);
        cliCommandHelper.openShell();
    }

    public String simpleExec(String... commands){
        stdOut = cliCommandHelper.simpleExec(commands);
        exitValue = cliCommandHelper.getShellExitValue();
        return stdOut;
    }

    public String execute(String command){
        return cliCommandHelper.execute(command);
    }

    public int closeShell(){
        return cliCommandHelper.closeAndValidateShell();
    }

}
