package com.ericsson.cifwk.taf.demo.test.operators;

import javax.inject.Singleton;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.demo.test.pages.HomeViewModel;
import com.ericsson.cifwk.taf.demo.test.pages.LoginViewModel;
import com.ericsson.cifwk.taf.demo.test.pages.PackageViewModel;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.UI;

@Singleton
public class PortalUIOperator implements PortalOperator {

    private Browser browser;
    private BrowserTab browserTab;
    private LoginViewModel loginViewModel;
    private HomeViewModel homeViewModel;
    private PackageViewModel packageViewModel;

    @Override
    public String login(String userName, String password) {
        browser = UI.newBrowser();
        browserTab = browser.open(getUrl());
        loginViewModel = browserTab.getView(LoginViewModel.class);
        loginViewModel.setUserName(userName);
        loginViewModel.setUserPassword(password);
        loginViewModel.clickSubmit();
        return browserTab.getCurrentUrl();
    }

    @Override
    public String followLink() {
        homeViewModel = browserTab.getView(HomeViewModel.class);
        homeViewModel.followLink();
        packageViewModel = browserTab.getView(PackageViewModel.class);
        packageViewModel.followLink();
        return browserTab.getCurrentUrl();
    }

    private String getUrl() {
        Host host = DataHandler.getHostByName("fwk");
        return host.getIp();
    }
}
