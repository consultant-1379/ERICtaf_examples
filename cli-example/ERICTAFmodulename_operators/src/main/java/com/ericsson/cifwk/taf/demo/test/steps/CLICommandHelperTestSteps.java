/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2015
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.operators.CLICommandHelperOperator;

public class CLICommandHelperTestSteps {

    @Inject
    private Provider<CLICommandHelperOperator> cliCommandHelperOperatorProvider;

    /**
     * This test step creates a script on the host specified in the operator
     * @param name
     * @param scriptFolder
     * @param scriptContent
     */
    @TestStep(id = StepIds.CREATE_SCRIPT_ON_REMOTE_HOST)
    public void createScriptOnRemoteHost(@Input(Parameters.SCRIPT_NAME) String name, @Input(Parameters.SCRIPT_FOLDER) String scriptFolder, @Input(Parameters.SCRIPT_CONTENT) String scriptContent) {
        CLICommandHelperOperator cliCommandHelperOperator = cliCommandHelperOperatorProvider.get();
        cliCommandHelperOperator.initializeShell();
        String fullPath = scriptFolder + "/" + name;
        cliCommandHelperOperator.execute("mkdir " + scriptFolder);
        cliCommandHelperOperator.execute("echo '" + scriptContent + "' > " + fullPath);
        cliCommandHelperOperator.execute("chmod +x " + fullPath);
        int commandExitValue = cliCommandHelperOperator.closeShell();
        assertThat(commandExitValue).isEqualTo(0);
    }

    /**
     * This test step executes a script on the host specified in the operator and verifies the output of the script
     * @param name
     * @param scriptFolder
     * @param expectedResult
     */
    @TestStep(id = StepIds.EXECUTE_SCRIPT_WITH_COMMAND)
    public void executeScript(@Input(Parameters.SCRIPT_NAME) String name, @Input(Parameters.SCRIPT_FOLDER) String scriptFolder, @Input(Parameters.SCRIPT_RESULT) String expectedResult) {
        CLICommandHelperOperator cliCommandHelperOperator = cliCommandHelperOperatorProvider.get();
        cliCommandHelperOperator.initializeShell();
        String changeDirCommand = "cd " + scriptFolder;
        String execCommand = "sh " + name;
        String output = cliCommandHelperOperator.simpleExec(changeDirCommand, execCommand);
        assertThat(output).contains(expectedResult);
    }

    /**
     * This test steps deletes a script from the host specified in the operator
     * @param scriptFolder
     */
    @TestStep(id = StepIds.DELETE_FOLDER_AND_SCRIPT_FROM_REMOTE_HOST)
    public void deleteScriptOnRemoteHost(@Input(Parameters.SCRIPT_FOLDER) String scriptFolder) {
        CLICommandHelperOperator cliCommandHelperOperator = cliCommandHelperOperatorProvider.get();
        cliCommandHelperOperator.initializeShell();
        cliCommandHelperOperator.execute("cd");
        cliCommandHelperOperator.execute("rm -rf " + scriptFolder);
        int commandExitValue = cliCommandHelperOperator.closeShell();
        assertThat(commandExitValue).isEqualTo(0);
    }

    public static class StepIds{
        public static final String CREATE_SCRIPT_ON_REMOTE_HOST = "createScriptOnRemoteHost";
        public static final String EXECUTE_SCRIPT_WITH_COMMAND = "execute-script-with-command";
        public static final String DELETE_FOLDER_AND_SCRIPT_FROM_REMOTE_HOST = "delete-folder-and-script-from-remote-host";

        private StepIds(){}
    }

    public static class Parameters{
        public static final String SCRIPT_NAME = "scriptname";
        public static final String SCRIPT_FOLDER = "scriptFolder";
        public static final String SCRIPT_CONTENT = "scriptContent";
        public static final String SCRIPT_RESULT = "scriptResult";

        private Parameters(){}
    }
}
