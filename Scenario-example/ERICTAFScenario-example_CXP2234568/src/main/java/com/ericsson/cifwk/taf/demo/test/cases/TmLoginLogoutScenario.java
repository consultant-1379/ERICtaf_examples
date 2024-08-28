package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.TmFlows;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

public class TmLoginLogoutScenario extends TafTestBase {

    @Inject
    TmFlows flows;

    /**
     * @DESCRIPTION Login to TMS, create a test case and logout
     */
    @TestId(id = "TAFTEST-17-ScenariosTest", title = "Login to TMS then Logout scenario test")
    @Test
    public void verifyLoginAndLogoutFromTMS() {
        TestScenario scenario = scenario("Verify Login, Create Test Case and Logout").addFlow(flows.tmLoginLogoutFlow()).build();
        TestScenarioRunner runner = runner().withListener(new LoggingScenarioListener()).build();
        runner.start(scenario);
    }
}
