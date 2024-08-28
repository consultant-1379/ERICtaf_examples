package com.ericsson.cifwk.taf.demo.test.viewmodel;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;

/**
 * Created by eendjor on 14/09/2015.
 */
public class LogoutViewModel extends GenericViewModel {

    @UiComponentMapping(".eaTmsUserButton-button")
    private Button userButton;

    @UiComponentMapping(".eaTmsUserButton-logout-anchor")
    private Button logoutButton;

    @UiComponentMapping(".ebBtn ebBtn_color_blue")
    private Button okButton;

    public void clickUserButton() {
        userButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
