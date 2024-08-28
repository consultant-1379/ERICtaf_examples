package com.ericsson.cifwk.taf.demo.test.operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;
import com.ericsson.cifwk.taf.tools.cli.CLI;
import com.ericsson.cifwk.taf.tools.cli.Shell;
import com.ericsson.cifwk.taf.tools.cli.Terminal;
import com.ericsson.cifwk.taf.tools.cli.handlers.impl.RemoteObjectHandler;
import com.google.inject.Singleton;

@Singleton
public class CalcCLIOperator implements CalcOperator {

    private String calcCommand;
    private CLI cli;

    Logger logger = LoggerFactory.getLogger(CalcCLIOperator.class);

    private static Pattern PATTERN_VERSION_DESCRIPTION = Pattern.compile(
            "(Version\\s*\\S*)");

    private static Pattern PATTERN_OPERATOR_DESCRIPTION = Pattern.compile(
            "(Performing\\s*\\S*)");


    public CalcCLIOperator() {
        calcCommand = DataHandler.getAttribute("CALCCMD").toString();
        Host host = DataHandler.getHostByName("testServer");
        cli = new CLI(host);
    }

    public static boolean initialise() {

        Host host = DataHandler.getHostByName("testServer");
        String source = DataHandler.getAttribute("CALCSCRIPT").toString();
        String target = DataHandler.getAttribute("CALCCMD").toString();
        RemoteObjectHandler remoteObjectHandler = new RemoteObjectHandler(host);
        if (!remoteObjectHandler.remoteFileExists(target)) {
            return remoteObjectHandler.copyLocalFileToRemote(source, target);
        } else
            return true;
    }


    protected String extractContent(String output, Pattern pattern) {
        String elementContent = output;
        Matcher matcher = pattern.matcher(output);
        if (matcher.find()) {
            elementContent = matcher.group(1);
        }
        return elementContent;
    }

    /**
     * Executes the Calc command and returns the result
     *
     * @param xvalue
     * @param yvalue
     * @param operator
     * @return {@link CalcResponse}
     */
    public CalcResponse verifyCalcCommand(String xvalue, String yvalue, String operator) {

        CalcResponse response = new CalcResponse();
        Shell shell = cli.executeCommand(Terminal.VT100, calcCommand);
        try {
            String versionDescriptionOutput = shell.expect("Version");
            String versionDescription = extractContent(versionDescriptionOutput, PATTERN_VERSION_DESCRIPTION);
            logger.info("Version description: " + versionDescription);
            response.setVersionDescription(versionDescription);

            shell.writeln(xvalue);
            shell.writeln(yvalue);
            shell.writeln(operator);
            String resultOutput = shell.read();
            response.setResult(resultOutput);

            String operatorDescription = extractContent(resultOutput, PATTERN_OPERATOR_DESCRIPTION);
            response.setOperatorDescription(operatorDescription);

            logger.info("Operator description: " + operatorDescription);
            logger.info("Exit value is: " + shell.getExitValue());
            response.setExitCode(shell.getExitValue());

        } finally {
            if (null != shell) {
                shell.disconnect();
            }
        }
        return response;

    }

}

