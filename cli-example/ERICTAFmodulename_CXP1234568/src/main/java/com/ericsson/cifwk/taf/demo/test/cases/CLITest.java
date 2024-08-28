package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.assertions.TafAsserts.assertTrue;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.CLIFlows;
import com.ericsson.cifwk.taf.demo.test.operators.CLIOperator;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

public class CLITest extends TafTestBase {

    public static final String CLI_EXAMPLE = "CLI_Example";
    public static final String COMMAND_DATA = "commandData";
    public static final String EXECUTE_SCRIPT_DATA = "executeScriptData";
    public static final String SEQUENTIAL_DATA = "sequentialData";
    public static final String CLI_SCENARIO = "CLI_Scenario";

    @Inject
    private CLIFlows cliFlows;

    @BeforeSuite
    public void initialise() {
        assertTrue(CLIOperator.initialise());
    }

    @TestId(id = CLI_EXAMPLE)
    @Test
    public void cliExample() {

        TestScenario scenario = scenario(CLI_SCENARIO)
                .addFlow(cliFlows.cliFlow(dataSource(COMMAND_DATA), dataSource(EXECUTE_SCRIPT_DATA), dataSource(SEQUENTIAL_DATA)))
                .build();

        TestScenarioRunner runner = runner()
                .withListener(new LoggingScenarioListener())
                .build();

        runner.start(scenario);

    }
}
