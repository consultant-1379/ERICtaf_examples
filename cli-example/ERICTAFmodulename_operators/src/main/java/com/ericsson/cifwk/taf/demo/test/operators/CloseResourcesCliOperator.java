package com.ericsson.cifwk.taf.demo.test.operators;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.cli.CLI;
import com.ericsson.cifwk.taf.tools.cli.Shell;
import com.google.inject.Singleton;

@Singleton
public class CloseResourcesCliOperator implements CloseResourcesOperator {

    private CLI cli;
    private Shell shell;
    public static List<Shell> shellList;
    static Logger logger = LoggerFactory.getLogger(CloseResourcesCliOperator.class);


    @Override
    public String execute(String hostname, String commandRef) {
        initializeShell(hostname);
        String command = getCommand(commandRef);
        shell.writeln(command);
        return shell.read();

    }

    public static void initializeList(){
        if (shellList == null) {
            shellList = new ArrayList<>();
        }
    }

    public String getCommand(String commandRef) {
        return (String) DataHandler.getAttribute(commandRef);
    }

    private Shell initializeShell(String hostname) {
        Host host = DataHandler.getHostByName(hostname);
        openShell(host);
        return shell;
    }

    private void openShell(Host host){
        cli = new CLI(host);
        logger.debug("Creating new shell instance with host " + host.getIp());
        shell = cli.openShell();
        logger.debug("Adding shell to staticList");
        shellList.add(shell);
    }

    public static void closeShells() {
        if(!shellList.isEmpty()){
            for (Shell s : shellList) {
                logger.debug("Disconnecting from " + s.getEnv().get("HOSTNAME"));
                s.disconnect();
            }
        }
    }

}

