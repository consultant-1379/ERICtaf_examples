package com.ericsson.cifwk.taf.demo.test.operators;

import static com.google.common.truth.Truth.assertThat;
import java.io.FileNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.tools.cli.CLI;
import com.ericsson.cifwk.taf.tools.cli.Shell;
import com.ericsson.cifwk.taf.tools.cli.TimeoutException;
import com.ericsson.cifwk.taf.tools.cli.handlers.impl.RemoteObjectHandler;
import com.ericsson.cifwk.taf.utils.FileFinder;
import com.google.inject.Singleton;

@Singleton
public class CLIOperator implements GenericOperator {

    private CLI cli;
    private Shell shell;

    Logger logger = LoggerFactory.getLogger(CLIOperator.class);

    @Override
    public String getCommand(String command) {
        return DataHandler.getAttribute(cliCommandPropertyPrefix + command)
                .toString();
    }

    public static boolean initialise() {

        Host host = DataHandler.getHostByName("testServer");
        String source = DataHandler.getAttribute("CLISOURCE").toString();
        String target = DataHandler.getAttribute("CLIFOLDER").toString();
        String file = DataHandler.getAttribute("USER_DETAILS_FILE").toString();
        String folderFile = target + file;
        RemoteObjectHandler remoteObjectHandler = new RemoteObjectHandler(host);
        if (!remoteObjectHandler.remoteFileExists(target) && !remoteObjectHandler.remoteFileExists(folderFile)) {
            return remoteObjectHandler.copyLocalFileToRemote(source, target);
        } else
            return true;
    }

    @Override
    public void initializeShell(Host host) {
        cli = new CLI(host);
        if (shell == null) {
            shell = cli.openShell();
            logger.debug("Creating new shell instance");
        }
    }

    @Override
    public void writeln(String command, String args) {
        String cmd = getCommand(command);
        logger.trace("Writing " + cmd + " " + args + " to standard input");
        logger.info("Executing commmand " + cmd + " with args " + args);
        shell.writeln(cmd + " " + args);
    }

    @Override
    public void writeln(String command) {
        String cmd = getCommand(command);
        logger.trace("Writing " + cmd + " to standard input");
        logger.info("Executing commmand " + cmd);
        shell.writeln(cmd);
    }

    @Override
    public int getExitValue() {
        int exitValue = shell.getExitValue();
        logger.debug("Getting exit value from shell, exit value is :"
                + exitValue);
        return exitValue;
    }

    @Override
    public String expect(String expectedText) throws TimeoutException {
        logger.debug("Expected return is " + expectedText);
        String found = shell.expect(expectedText);
        return found;
    }

    @Override
    public void expectClose(int timeout) throws TimeoutException {
        shell.expectClose(timeout);
    }

    @Override
    public boolean isClosed() throws TimeoutException {
        return shell.isClosed();
    }

    @Override
    public String checkForNullError(String error) {
        if (error == null) {
            error = "";
            return error;
        }
        return error;
    }

    @Override
    public String getStdOut() {
        String result = shell.read();
        logger.debug("Standard out: " + result);
        return result;
    }

    @Override
    public void disconnect() {
        logger.info("Disconnecting from shell");
        shell.disconnect();
        shell = null;
    }

    @Override
    public void sendFileRemotely(Host host, String fileName,
            String fileServerLocation) throws FileNotFoundException {

        RemoteObjectHandler remoteObjectHandler = new RemoteObjectHandler(host);
        List<String> fileLocation = FileFinder.findFile(fileName);
        String remoteFileLocation = fileServerLocation; // unix address
        remoteObjectHandler.copyLocalFileToRemote(fileLocation.get(0), remoteFileLocation);
        logger.debug("Copying " + fileName + " to " + remoteFileLocation
                + " on remote host");

    }

    @Override
    public void deleteRemoteFile(Host host, String fileName,
            String fileServerLocation) throws FileNotFoundException {

        RemoteObjectHandler remoteObjectHandler = new RemoteObjectHandler(host);
        String remoteFileLocation = fileServerLocation;
        remoteObjectHandler.deleteRemoteFile(remoteFileLocation + fileName);
        logger.debug("deleting " + fileName + " at location "
                + remoteFileLocation + " on remote host");
    }

    @Override
    public void scriptInput(String message) {
        logger.info("Writing " + message + " to standard in");
        shell.writeln(message);
    }

    @Override
    public Shell executeCommand(String... commands) {
        logger.info("Executing command(s) " + commands);
        return cli.executeCommand(commands);

    }

    public void verifyChangedirCommand(String changeDirCmd, String directory, String listDirCmd, String expectedOut) {
        writeln(changeDirCmd, directory);
        writeln(listDirCmd);
        assertThat(getStdOut().contains(expectedOut)).isTrue();
    }

    public void verifyChangedirCommandWithTail(String changeDirCmd, String directory, String tailCmd, String file, String expectedOut) {
        writeln(changeDirCmd, directory);
        writeln(tailCmd, file);
        assertThat(getStdOut().contains(expectedOut)).isTrue();
    }

    public void verifyClose(String exitShellCmd, int timeout, int expectedExitCode) {
        writeln(exitShellCmd);
        expectClose(timeout);
        assertThat(expectedExitCode).isEqualTo(getExitValue());
        assertThat(isClosed()).isTrue();
    }

    public void executeScript(String changeDirCmd, String fileLocation, String file, String executeFileCmd, String firstPrompt, String firstPromptResponse, String secondPrompt, String secondPromptResponse) {
        writeln(changeDirCmd, fileLocation);
        writeln("convertLineEndings", file);
        writeln(executeFileCmd, file);
        expect(firstPrompt);
        scriptInput(firstPromptResponse);
        expect(secondPrompt);
        scriptInput(secondPromptResponse);
    }
}
