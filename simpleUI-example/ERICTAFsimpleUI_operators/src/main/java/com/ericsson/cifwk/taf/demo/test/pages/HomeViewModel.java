package com.ericsson.cifwk.taf.demo.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Link;

public class HomeViewModel extends GenericViewModel {

    @UiComponentMapping("#cpl_link")
    private Link completePackageListLink;

    public void followLink() {
        completePackageListLink.click();
    }
}
