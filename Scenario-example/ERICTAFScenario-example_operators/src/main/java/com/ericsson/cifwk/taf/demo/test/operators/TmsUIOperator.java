/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.cifwk.taf.demo.test.operators;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.demo.test.dataRecords.TestCaseDetails;
import com.ericsson.cifwk.taf.demo.test.viewmodel.CreateTestCaseViewModel;
import com.ericsson.cifwk.taf.demo.test.viewmodel.HomeViewModel;
import com.ericsson.cifwk.taf.demo.test.viewmodel.LoginViewModel;
import com.ericsson.cifwk.taf.demo.test.viewmodel.LogoutDialogBoxViewModel;
import com.ericsson.cifwk.taf.demo.test.viewmodel.LogoutViewModel;
import com.ericsson.cifwk.taf.demo.test.viewmodel.TmsFormViewModel;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.enm.Tool;

public class TmsUIOperator {

    private Browser browser;
    private BrowserTab browserTab;
    private LoginViewModel loginViewModel;
    private LogoutViewModel logoutViewModel;
    private HomeViewModel homeViewModel;
    private LogoutDialogBoxViewModel dialogScreenView;
    private CreateTestCaseViewModel createTestCaseViewModel;
    private TmsFormViewModel form;
    public static final int WAIT_SHORT_TIMEOUT = 5000;

    public void setTool(Tool tool) {
        this.browser = tool.getAs(Browser.class);
        this.browserTab = browser.getCurrentWindow();
    }

    public Tool login(String userName, String password) {
        browser = UI.newBrowser();
        browserTab = browser.open(getUrl());
        loginViewModel = browserTab.getView(LoginViewModel.class);
        loginViewModel.setUserName(userName);
        loginViewModel.setUserPassword(password);
        loginViewModel.clickLogin();
        browserTab.waitUntilComponentIsDisplayed(".eaTM-ContentBoxItem-text", WAIT_SHORT_TIMEOUT);
        return Tool.set(browser);
    }

    public String selectTestCaseLink(String selectTestCase) {
        homeViewModel = browserTab.getView(HomeViewModel.class);
        homeViewModel.selectTestCase(selectTestCase);
        return browserTab.getCurrentUrl();
    }

    public String createTestCaseLink(String projectType) {
        createTestCaseViewModel = browserTab.getView(CreateTestCaseViewModel.class);
        createTestCaseViewModel.clickMenuButton();
        browserTab.waitUntilComponentIsDisplayed(createTestCaseViewModel.getMenuList(), WAIT_SHORT_TIMEOUT);
        createTestCaseViewModel.clickCreateTestCase("Create Test Case");
        if(createTestCaseViewModel.getDialogBox().isDisplayed()){
            createTestCaseViewModel.clickProductType(projectType);
        }
        createTestCaseViewModel.createTestCase("Create Test Case");
        return browserTab.getCurrentUrl();
    }

    private String getUrl() {
        Host host = DataHandler.getHostByName("testManagement");
        return host.getIp();
    }

    public Boolean createTestCase(TestCaseDetails testCase) {
        form = browserTab.getView(TmsFormViewModel.class);
        form.setFeature(testCase.getFeature());
        form.setComponent(testCase.getComponent());
        form.setRequirementId(testCase.getReqId());
        form.setTestCaseId(testCase.getTestCaseId());
        form.setTitle(testCase.getTitle());
        form.setDescription(testCase.getDescription());
        form.setPrecondition(testCase.getPrecondition());
        form.setType(testCase.getType());
        form.setPriority(testCase.getPriority());
        form.selectExecutionType(testCase.getExecutionType());
        form.createTestCase(testCase.getOptionName());
        return form.isBackButtonVisible();
    }

    public Boolean logout(){
        logoutView();
        dialogScreenView = browserTab.getView(LogoutDialogBoxViewModel.class);
        browserTab.waitUntilComponentIsDisplayed(dialogScreenView.getDialog(), WAIT_SHORT_TIMEOUT);
        dialogScreenView.getSubmitBlueButton();
        browser.close();
        return browser.isClosed();
    }

    private void logoutView() {
        logoutViewModel = browserTab.getView(LogoutViewModel.class);
        logoutViewModel.clickUserButton();
        browserTab.waitUntilComponentIsDisplayed(logoutViewModel.getLogoutButton(), WAIT_SHORT_TIMEOUT);
        logoutViewModel.clickLogoutButton();
    }
}
