package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import java.io.FileNotFoundException;
import javax.inject.Inject;
import javax.inject.Provider;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.demo.test.operators.CLIOperator;
import com.ericsson.cifwk.taf.tools.cli.TimeoutException;

/**
 * Created by eendjor on 15/06/2016.
 */
public class CLITestSteps {

    @Inject
    private Provider<CLIOperator> provider;

    /**
     * @throws TimeoutException
     * @DESCRIPTION Verify the ability of CLI Shell commands to be executed
     * @PRE Connection to SUT
     * @PRIORITY HIGH
     */
    @TestStep(id = StepIds.VERIFY_COMMANDS_EXECUTED)
    public void verifyCLICommandsCanBeExecuted(
            @Input(Parameters.COMMAND) String command,
            @Input(Parameters.TIMEOUT) int timeout,
            @Input(Parameters.ARGS_COMMAND_DATA) String args,
            @Output(Parameters.EXPECTED_OUT_COMMAND_DATA) String expectedOut,
            @Output(Parameters.EXPECTED_EXIT_COMMAND_DATA) int expectedExitCode) throws InterruptedException, TimeoutException {

        CLIOperator cliOperator = provider.get();
        cliOperator.initializeShell(DataHandler.getHostByName("testServer"));

        cliOperator.writeln(command, args);
        assertThat(cliOperator.getStdOut()).contains(expectedOut);
    }

    /**
     * @throws TimeoutException
     * @throws FileNotFoundException
     * @DESCRIPTION Verify details from running script are as expected
     * @PRE Connection to SUT
     * @PRIORITY HIGH
     */
    @TestStep(id = StepIds.VERIFY_PROMPT_DETAILS)
    public void verifyExpectedPromptDetails(
            @Input(Parameters.EXECUTE_FILE) String executeFileCmd,
            @Input(Parameters.FILE) String file,
            @Input(Parameters.PROMPT1RESPONSE) String firstPromptResponse,
            @Input(Parameters.PROMPT2RESPONSE) String secondPromptResponse,
            @Input(Parameters.CHANGE_DIR_SCRIPT_DATA) String changeDirCmd,
            @Input(Parameters.FILE_LOCATION) String fileLocation,
            @Input(Parameters.TIMEOUT_SCRIPT_DATA) int timeout,
            @Input(Parameters.EXIT_SHELL) String exitShellCmd,
            @Output(Parameters.PROMPT1) String firstPrompt,
            @Output(Parameters.PROMPT2) String secondPrompt,
            @Output(Parameters.EXPECTED_EXIT_SCRIPT_DATA) int expectedExitCode) throws TimeoutException, FileNotFoundException {

        CLIOperator cliOperator = provider.get();
        Host testServer = DataHandler.getHostByName("testServer");
        cliOperator.initializeShell(testServer);
        cliOperator.sendFileRemotely(testServer, file, fileLocation);

        cliOperator.executeScript(changeDirCmd, fileLocation, file, executeFileCmd, firstPrompt, firstPromptResponse, secondPrompt, secondPromptResponse);
        cliOperator.verifyClose(exitShellCmd, timeout, expectedExitCode);
        cliOperator.deleteRemoteFile(testServer, file, fileLocation);
        cliOperator.disconnect();
    }

    /**
     * @throws InterruptedException
     * @throws TimeoutException
     * @DESCRIPTION Verify commands can be executed sequentially
     * @PRE Connection to SUT
     * @PRIORITY HIGH
     */
    @TestStep(id = StepIds.VERIFY_COMMANDS_EXECUTED_SEQUENTIALLY)
    public void verifyCLICommandsCanBeExecutedSequentially(
            @Input(Parameters.LIST_DIR) String listDirCmd,
            @Input(Parameters.ARGS_SEQUENTIAL_DATA) String args,
            @Input(Parameters.STRING) String changeDirCmd,
            @Input(Parameters.TAIL_FILE) String tailCmd,
            @Input(Parameters.ARGS2) String args2,
            @Input(Parameters.TIMEOUT_SEQUENTIAL_DATA) int timeout,
            @Input(Parameters.HOME_DIR) String rootDirectory,
            @Input(Parameters.EXIT_SHELL_SEQUENTIAL_DATA) String exitShellCmd,
            @Output(Parameters.EXPECTED_OUT) String expectedOut,
            @Output(Parameters.EXPECTED_OUT2) String expectedOut2,
            @Output(Parameters.EXPECTED_EXIT) int expectedExitCode) throws InterruptedException, TimeoutException {

        CLIOperator cliOperator = provider.get();
        cliOperator.initializeShell(DataHandler.getHostByName("testServer"));
        cliOperator.verifyChangedirCommand(changeDirCmd, rootDirectory, listDirCmd, expectedOut);
        cliOperator.verifyChangedirCommandWithTail(changeDirCmd, args, tailCmd, args2, expectedOut2);
        cliOperator.verifyClose(exitShellCmd, timeout, expectedExitCode);
        cliOperator.disconnect();
    }

    public static class StepIds{
        public static final String VERIFY_COMMANDS_EXECUTED = "verifyCLICommandsCanBeExecuted";
        public static final String VERIFY_PROMPT_DETAILS = "verifyExpectedPromptDetails";
        public static final String VERIFY_COMMANDS_EXECUTED_SEQUENTIALLY = "verifyCLICommandsCanBeExecutedSequentially";

        private StepIds(){}
    }

    public static class Parameters{
        public static final String COMMAND = "command";
        public static final String TIMEOUT = "timeout";
        public static final String ARGS_COMMAND_DATA = "argsCommandData";
        public static final String EXPECTED_OUT_COMMAND_DATA = "expectedOutCommandData";
        public static final String EXPECTED_EXIT_COMMAND_DATA = "expectedExitCommandData";
        public static final String EXECUTE_FILE = "executeFile";
        public static final String FILE = "file";
        public static final String PROMPT1RESPONSE = "prompt1response";
        public static final String PROMPT2RESPONSE = "prompt2response";
        public static final String CHANGE_DIR_SCRIPT_DATA = "changeDirScriptData";
        public static final String FILE_LOCATION = "fileLocation";
        public static final String TIMEOUT_SCRIPT_DATA = "timeoutScriptData";
        public static final String EXIT_SHELL = "exitShell";
        public static final String PROMPT1 = "prompt1";
        public static final String PROMPT2 = "prompt2";
        public static final String EXPECTED_EXIT_SCRIPT_DATA = "expectedExitScriptData";
        public static final String LIST_DIR = "listDir";
        public static final String ARGS_SEQUENTIAL_DATA = "argsSequentialData";
        public static final String STRING = "changeDir";
        public static final String TAIL_FILE = "tailFile";
        public static final String ARGS2 = "args2";
        public static final String TIMEOUT_SEQUENTIAL_DATA = "timeoutSequentialData";
        public static final String HOME_DIR = "homeDir";
        public static final String EXIT_SHELL_SEQUENTIAL_DATA = "exitShellSequentialData";
        public static final String EXPECTED_OUT = "expectedOut";
        public static final String EXPECTED_OUT2 = "expectedOut2";
        public static final String EXPECTED_EXIT = "expectedExit";

        private Parameters(){}
    }
}
