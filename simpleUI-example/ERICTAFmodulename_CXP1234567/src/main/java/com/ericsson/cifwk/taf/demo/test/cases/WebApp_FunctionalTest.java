package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataDrivenScenario;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;

import javax.inject.Inject;

import org.testng.annotations.Test;

import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestSuite;
import com.ericsson.cifwk.taf.demo.test.steps.WepAppTestStep;
import com.ericsson.cifwk.taf.scenario.TestScenario;

/**
 * Created by eendjor on 01/06/2016.
 */
public class WebApp_FunctionalTest extends TafTestBase {

    public static final String WEB_APP_TEST_SUITE = "WebAppTestScenario";
    public static final String BING_SEARCH = "bing_search";
    public static final String VERIFY_BING_SEARCH = "verifyBingSearch";
    public static final String EXECUTE_BING_SEARCH = "ExecuteSearchFlow";

    @Inject
    WepAppTestStep wepAppTestStep;

    @Test
    @TestSuite(WEB_APP_TEST_SUITE)
    public void verifyBingSearch(){

        TestScenario scenario = dataDrivenScenario(VERIFY_BING_SEARCH)
                .addFlow(
                        flow(EXECUTE_BING_SEARCH)
                                .addTestStep(annotatedMethod(wepAppTestStep, WepAppTestStep.EXECUTE_SEARCH)))
                .withScenarioDataSources(dataSource(BING_SEARCH))
                .build();
        runner().build().start(scenario);
    }
}
