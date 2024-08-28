package com.ericsson.cifwk.taf.demo.test.steps;

import static com.ericsson.cifwk.taf.assertions.TafHamcrestAsserts.assertEquals;
import javax.inject.Inject;
import com.ericsson.cifwk.taf.TestContext;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.dataRecords.TestCaseDetails;
import com.ericsson.cifwk.taf.demo.test.operators.TmsUIOperator;
import com.ericsson.enm.Tool;
import com.ericsson.enm.UserSession;
import com.google.inject.Provider;

public class TmLoginLogoutTestSteps {

    @Inject
    Provider<TmsUIOperator> webOperatorProvider;

    @Inject
    public TestContext context;

    TmsUIOperator tmsOperator;

    public static final String LOGIN = "login";
    public static final String SELECT_TEST_CASE = "selectTestCaseLink";
    public static final String CREATE_TEST_CASE_LINK = "createTestCaseLink";
    public static final String CREATE_TEST_CASE = "createTestCase";
    public static final String LOGOUT = "logout";

    @TestStep(id="login")
    public void login(
            @Input("username") String username,
            @Input("password") String password
    		) {
        Tool tool = webOperatorProvider.get().login(username,password);
        UserSession userSession = new UserSession();
        userSession.setTool(tool);
        context.setAttribute(UserSession.SESSION, userSession);
    }

    @TestStep(id="selectTestCaseLink")
    public void selectTestCaseLink(@Input("selectTestCase") String selectTestCase) {
        getTmsOperator().selectTestCaseLink(selectTestCase);
    }

    @TestStep(id="createTestCaseLink")
    public void createTestCaseLink(@Input("projectType") String projectType) {
        getTmsOperator().createTestCaseLink(projectType);
    }

    @TestStep(id="createTestCase")
    public void createTestCase(@Input("createTestCase")TestCaseDetails testCase){
        assertEquals(testCase.getHeaderExists(), getTmsOperator().createTestCase(testCase));
    }

    @TestStep(id="logout")
    public void logout(@Input("headerExists") Boolean headerExists){
        assertEquals(headerExists, getTmsOperator().logout());
    }

    private TmsUIOperator getTmsOperator(){
        tmsOperator = webOperatorProvider.get();
        UserSession userSession = context.getAttribute(UserSession.SESSION);
        tmsOperator.setTool(userSession.getTool());
        return tmsOperator;
    }
}
