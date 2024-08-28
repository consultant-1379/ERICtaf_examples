package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.testng.annotations.BeforeSuite;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.operators.CloseResourcesCliOperator;
import com.ericsson.cifwk.taf.demo.test.operators.CloseResourcesOperator;

/**
 * Created by eendjor on 15/06/2016.
 */
public class CloseResourcesTestSteps {

    @Inject
    private Provider<CloseResourcesCliOperator> provider;

    @BeforeSuite
    public void setUp() {
        CloseResourcesCliOperator.initializeList();
    }

    /**
     * @DESCRIPTION Verify the ability of CLI Shell commands to be executed
     * @PRE Connection to SUT
     * @PRIORITY HIGH
     */
    @TestStep(id = StepIds.VERIFY_SHELL_COMMANDS)
    public void verifyCLICommandsCanBeExecuted(@Input(Parameters.HOST) String host, @Input(Parameters.COMMAND_REF) String commandRef,
            @Output(Parameters.EXPECTED_OUT) String expectedOut) {
        CloseResourcesOperator operator = provider.get();
        String result = operator.execute(host, commandRef);
        assertThat(result).contains(expectedOut);
    }

    public static class StepIds {
        public static final String VERIFY_SHELL_COMMANDS = "verifyCLICommandsCanBeExecuted";

        private StepIds() {
        }
    }

    public static class Parameters {
        public static final String HOST = "host";
        public static final String COMMAND_REF = "commandRef";
        public static final String EXPECTED_OUT = "expectedOut";

        private Parameters() {
        }
    }
}
