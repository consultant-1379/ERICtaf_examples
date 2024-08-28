package com.ericsson.cifwk.taf.demo.test.viewmodel;

import java.util.List;
import com.ericsson.cifwk.taf.demo.test.compositeModels.UiDropDownMenu;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Link;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;

public class TmsFormViewModel extends GenericViewModel {

    private static final int COMPONENT_TIMEOUT = 5000;

    @UiComponentMapping(".elWidgets-ComponentList")
    private UiDropDownMenu uiDropDownMenu;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-projectSelect")
    private Button projectDropDown;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-featureSelect")
    private TextBox feature;

    @UiComponentMapping(".ebMultiSelect-textarea.ebTextArea")
    private TextBox requirementId;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-testCaseId")
    private TextBox testCaseId;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-title")
    private TextBox title;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-description")
    private TextBox description;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-preCondition")
    private TextBox precondition;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-componentSelect")
    private TextBox component;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-typeSelect")
    private Button typeDropDown;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-prioritySelect")
    private Button priorityDropDown;

    @UiComponentMapping("#TMS_CreateTestCase_TestCaseForm-executionTypeSelect")
    private Button executionTypeDropDown;

    @UiComponentMapping(".eaTM-ActionLink-link")
    private List<Link> create;

    @UiComponentMapping("#TMS_TestCaseDetails_ViewTestCaseBar-backText")
    private Link backButton;

    public void setRequirementId(String object) {
        requirementId.setText(object);
    }

    public void setTestCaseId(String passWord) {
        testCaseId.setText(passWord+System.currentTimeMillis());
    }

    public void setTitle(String setTitle) {
        title.setText(setTitle);
    }

    public void setDescription(String setDescription) {
        description.setText(setDescription);
    }

    public void setPrecondition(String setPrecondition) {
        precondition.setText(setPrecondition);
    }

    public void setFeature(String setFeature) {
        feature.click();
        selectFromDropDown(setFeature);
    }

    public void setType(String type) {
        typeDropDown.click();
        selectFromDropDown(type);
    }

    public void setPriority(String priority) {
        priorityDropDown.click();
        selectFromDropDown(priority);
    }

    public void setComponent(String setComponent) {
        component.click();
        waitUntilComponentIsDisplayed(uiDropDownMenu, COMPONENT_TIMEOUT);
        uiDropDownMenu.clickMultipleOptions(setComponent);
    }

    public void selectExecutionType(String executionType) {
        executionTypeDropDown.click();
        selectFromDropDown(executionType);
    }

    public void createTestCase(String optionName) {
        for(Link link : create){
            if(link.getText().equalsIgnoreCase(optionName)){
                link.click();
                break;
            }
        }
    }

    public boolean isBackButtonVisible(){
        waitUntilComponentIsDisplayed(".eaTM-ViewTestCaseHelp-header", COMPONENT_TIMEOUT);
        return backButton.exists();
    }

    private void selectFromDropDown(String executionType) {
        waitUntilComponentIsDisplayed(uiDropDownMenu, COMPONENT_TIMEOUT);
        uiDropDownMenu.clickOptionByName(executionType);
    }
}
