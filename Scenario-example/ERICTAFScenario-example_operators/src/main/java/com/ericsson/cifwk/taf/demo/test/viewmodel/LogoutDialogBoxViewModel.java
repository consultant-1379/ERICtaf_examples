package com.ericsson.cifwk.taf.demo.test.viewmodel;

import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;

public class LogoutDialogBoxViewModel extends GenericViewModel {

    @UiComponentMapping(".ebDialogBox")
    private UiComponent dialog;

    @UiComponentMapping(".ebBtn.ebBtn_color_blue")
    private UiComponent submitBlueButton;

    public UiComponent getDialog() {
        return dialog;
    }

    public void getSubmitBlueButton() {
        submitBlueButton.click();
    }
}
