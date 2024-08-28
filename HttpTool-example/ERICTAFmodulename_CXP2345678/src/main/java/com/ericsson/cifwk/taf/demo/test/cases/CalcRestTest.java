package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataDrivenScenario;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import javax.inject.Inject;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestSuite;
import com.ericsson.cifwk.taf.demo.test.flows.CalcRestFlows;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

/**
 * Created by eendjor on 23/05/2016.
 */
public class CalcRestTest extends TafTestBase {

    public static final String CALC_TEST_SUITE = "CalcRestTestScenario";
    public static final String CALC_FILE = "csvfile";
    public static final String VERIFY_CALC_SCENARIO = "VerifyCalcRestScenario";

    @Inject
    private CalcRestFlows calcRestFlows;

    /**
     * @DESCRIPTION Verify the functionality of the Calc REST interface
     */
    @Test
    @TestSuite(CALC_TEST_SUITE)
    public void verifyCalcCommand() {

        TestScenario scenario = dataDrivenScenario(VERIFY_CALC_SCENARIO)
                .addFlow(calcRestFlows.calcFlow())
                .withScenarioDataSources(dataSource(CALC_FILE)).build();
        runner().withListener(new LoggingScenarioListener()).build().start(scenario);
    }
}
