package com.ericsson.cifwk.taf.demo.test.flows;

import static com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps.CREATE_TEST_CASE;
import static com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps.CREATE_TEST_CASE_LINK;
import static com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps.LOGIN;
import static com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps.LOGOUT;
import static com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps.SELECT_TEST_CASE;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.annotatedMethod;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.flow;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.demo.test.steps.TmLoginLogoutTestSteps;
import com.ericsson.cifwk.taf.scenario.TestStepFlow;

public class TmFlows {

	@Inject
	TmLoginLogoutTestSteps testSteps;

	public TestStepFlow tmLoginLogoutFlow(){

		return flow("Login to TMS then logout")
				.addTestStep(annotatedMethod(testSteps, LOGIN))
				.addTestStep(annotatedMethod(testSteps, SELECT_TEST_CASE))
				.addTestStep(annotatedMethod(testSteps, CREATE_TEST_CASE_LINK))
				.addTestStep(annotatedMethod(testSteps, CREATE_TEST_CASE))
				.addTestStep(annotatedMethod(testSteps, LOGOUT))
				.withDataSources(dataSource("loginData"), dataSource("createTestCase"))
				.build();
	}
}
