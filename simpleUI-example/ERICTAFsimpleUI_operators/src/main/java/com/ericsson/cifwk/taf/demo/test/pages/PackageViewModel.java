package com.ericsson.cifwk.taf.demo.test.pages;

import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Link;

public class PackageViewModel extends GenericViewModel {

    @UiComponentMapping("#package_ERICdatapersist_CXP9030106")
    private Link packageLink;

    public void followLink() {
        packageLink.isDisplayed();
        packageLink.click();
    }
}
