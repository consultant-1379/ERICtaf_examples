package com.ericsson.cifwk.taf.demo.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;

public class LoginViewModel extends GenericViewModel {

    @UiComponentMapping("#id_username")
    private TextBox usernameInputBox;

    @UiComponentMapping("#id_password")
    private TextBox passwordInputBox;

    @UiComponentMapping("input[name^=\"ciportal_login\"]")
    private UiComponent loginSubmit;

    public void setUserName(final String userName) {
        usernameInputBox.setText(userName);
    }

    public void setUserPassword(final String userPassword) {
        passwordInputBox.setText(userPassword);
    }

    public void clickSubmit() {
        loginSubmit.click();
    }
}

