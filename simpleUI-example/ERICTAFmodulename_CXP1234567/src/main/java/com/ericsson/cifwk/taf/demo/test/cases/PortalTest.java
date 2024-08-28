package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.steps.PortalTestStep;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.api.ScenarioExceptionHandler;

/**
 * Created by eendjor on 31/05/2016.
 */
public class PortalTest extends TafTestBase {

    public static final String EXAMPLE_PORTAL_LOGIN = "Example_Portal_Login";
    public static final String LOGIN = "login";
    public static final String FOLLOW_LINK = "followLink";
    public static final String PORTAL_SCENARIO = "PortalScenario";
    public static final String PORTAL_FLOW = "PortalFlow";

    @Inject
    PortalTestStep portalTestStep;

    @TestId(id = EXAMPLE_PORTAL_LOGIN)
    @Test
    public void verifyPortalLogin() {

        TestScenario scenario = scenario(PORTAL_SCENARIO)
                .addFlow(flow(PORTAL_FLOW)
                        .addTestStep(annotatedMethod(portalTestStep, PortalTestStep.LOGIN))
                        .addTestStep(annotatedMethod(portalTestStep, PortalTestStep.FOLLOW_LINK))
                        .withDataSources(dataSource(LOGIN), dataSource(FOLLOW_LINK))
                        .build())
                .build();

        TestScenarioRunner runner = runner()
                .withDefaultExceptionHandler(ScenarioExceptionHandler.PROPAGATE)
                .build();

        runner.start(scenario);
    }
}
