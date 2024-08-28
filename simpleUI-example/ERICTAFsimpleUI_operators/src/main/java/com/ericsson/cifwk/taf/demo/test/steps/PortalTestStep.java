package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.testng.annotations.BeforeSuite;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.operators.PortalUIOperator;
import com.ericsson.cifwk.taf.execution.TestExecutionEvent;
import com.ericsson.cifwk.taf.ui.UI;

/**
 * Created by eendjor on 31/05/2016.
 */
public class PortalTestStep {

    public static final String LOGIN = "login";
    public static final String FOLLOW_LINK = "followLink";

    @Inject
    private Provider<PortalUIOperator> provider;

    PortalUIOperator portalUIOperator;

    @BeforeSuite
    public void setUp(){
        UI.closeWindow(TestExecutionEvent.ON_SUITE_FINISH);
    }

    /**
     * Login to the CI Portal and verify that the url is as expected
     * @param username for login
     * @param password for login
     */
    @TestStep(id = PortalTestStep.LOGIN)
    public void login(
            @Input("username") String username,
            @Input("password") String password,
            @Output("loginUrl") String url) {
        portalUIOperator = provider.get();
        assertThat(url).isEqualTo(portalUIOperator.login(username,password));
    }

    /**
     * Follow the link and verify url is as expected
     */
    @TestStep(id = PortalTestStep.FOLLOW_LINK)
    public void followLink(
            @Output("followLinkUrl") String url){
        portalUIOperator = provider.get();
        assertThat(url).isEqualTo(portalUIOperator.followLink());
    }
}
