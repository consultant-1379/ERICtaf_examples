package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.CalcCliFlows;
import com.ericsson.cifwk.taf.demo.test.operators.CalcCLIOperator;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

/**
 * Created by eendjor on 23/05/2016.
 */
public class CalcCLITest extends TafTestBase {

    public static final String CALC_COMMAND_001 = "CIP-xxxx_Func-1";
    public static final String CALC_DATA = "CalcCLITest";
    public static final String VERIFY_CALC_COMMAND_SCENARIO = "CalcScenario";

    @Inject
    private CalcCliFlows calcCliFlows;

    @BeforeSuite
    public void initialise() {
        CalcCLIOperator.initialise();
    }

    /**
     * @DESCRIPTION Verify the functionality of the Calc Command
     * @PRE Get the calc version to be tested
     */
    @TestId(id = CALC_COMMAND_001)
    @Test
    public void verifyCalcCommand() {

        TestScenario scenario = scenario(VERIFY_CALC_COMMAND_SCENARIO)
                .addFlow(calcCliFlows.executeCalcFlow(dataSource(CALC_DATA)))
                .build();

        TestScenarioRunner runner = runner()
                .withListener(new LoggingScenarioListener())
                .build();

        runner.start(scenario);

    }

}
