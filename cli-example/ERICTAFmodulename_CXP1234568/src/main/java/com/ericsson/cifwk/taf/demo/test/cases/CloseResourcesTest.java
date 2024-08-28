package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.CLIFlows;
import com.ericsson.cifwk.taf.demo.test.operators.CloseResourcesCliOperator;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

public class CloseResourcesTest extends TafTestBase {

    public static final String CLI_SHELL_COMMANDS = "CIP-3884_Func_1";
    public static final String SIMPLE_COMMANDS = "simple_commands";
    public static final String SHELL_COMMANDS_SCENARIO = "shellCommandScenario";

    @Inject
    private CLIFlows cliFlows;

    @BeforeSuite
    public void setUp() {
        CloseResourcesCliOperator.initializeList();
    }

    /**
     * @DESCRIPTION Verify the ability of CLI Shell commands to be executed
     * @PRE Connection to SUT
     * @PRIORITY HIGH
     */
    @TestId(id = CLI_SHELL_COMMANDS)
    @Test
    public void verifyCLICommandsCanBeExecuted() {

        TestScenario scenario = scenario(SHELL_COMMANDS_SCENARIO)
                .addFlow(cliFlows.shellCommandFlow(dataSource(SIMPLE_COMMANDS)))
                .build();

        TestScenarioRunner runner = runner()
                .withListener(new LoggingScenarioListener())
                .build();

        runner.start(scenario);
    }

    @AfterSuite
    public void closeResources() {
        CloseResourcesCliOperator.closeShells();
    }
}
