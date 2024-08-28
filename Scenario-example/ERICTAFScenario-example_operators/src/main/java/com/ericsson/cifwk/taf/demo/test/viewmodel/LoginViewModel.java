package com.ericsson.cifwk.taf.demo.test.viewmodel;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;

public class LoginViewModel extends GenericViewModel {

    @UiComponentMapping(".eaLogin-loginUsername")
    private TextBox username;

    @UiComponentMapping(".eaLogin-loginPassword")
    private TextBox password;

    @UiComponentMapping(".eaLogin-formButton")
    private Button login;

    public void setUserName(String userName) {
    	username.setText(userName);
    }

    public void setUserPassword(String passWord) {
    	password.setText(passWord);
    }

    public void clickLogin() {
    	login.click();
    }

}
