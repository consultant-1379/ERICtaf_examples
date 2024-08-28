package com.ericsson.cifwk.taf.demo.test.operators;

import java.util.Arrays;
import java.util.List;

import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.ui.Browser;
import com.ericsson.cifwk.taf.ui.BrowserTab;
import com.ericsson.cifwk.taf.ui.UI;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.sdk.Button;
import com.ericsson.cifwk.taf.ui.sdk.Label;
import com.ericsson.cifwk.taf.ui.sdk.TextBox;
import com.ericsson.cifwk.taf.ui.sdk.ViewModel;

public class WebAppUiOperator implements WebAppOperator {

    private Browser browser;
    private BrowserTab browserTab;
    private ViewModel viewModel;
    private List<UiComponent> components;
    private Host bingHost;

    public void openBrowser(){
        String url = getWebAppUrl();
        browser = UI.newBrowser();
        browserTab = browser.open(url);
        viewModel = browserTab.getGenericView();
        components = getPageElements();
    }

    private String getWebAppUrl() {
        Host bingHost = DataHandler.getHostByName("bing");
        return String.format("http://%s", bingHost.getIp());
    }

    private List<UiComponent> getPageElements(){
        //Google do not name elements on their page intuitively - thus the IDs are gibberish
        return Arrays.asList(this.viewModel.getTextBox("#sb_form_q"), this.viewModel.getButton("#sb_form_go"), this.viewModel.getLabel("#b_results"));
    }

    /**
     * Enter text in search box and click search
     * @param searchTerm used to search
     */
    public String enterSearchTerm(String searchTerm) throws InterruptedException{
        TextBox searchBar = (TextBox) components.get(0);
        Button searchButton = (Button) components.get(1);
        searchBar.setText(searchTerm);
        searchButton.click();
        browserTab.waitUntilComponentIsDisplayed(components.get(2), 5000);
        Label searchResults = (Label) components.get(2);
        return searchResults.getText();
    }

    /**
     * Follow the first link returned
     */
    public String followFirstResult() throws InterruptedException{
        //results are of class 'r' - so wait until a result is displayed before clicking
        browserTab.waitUntilComponentIsDisplayed((UiComponent) viewModel.getViewComponent(".b_algo a", Button.class), 10000);
        List<Button> buttons = (List<Button>) viewModel.getViewComponents(".b_algo a", Button.class);
        buttons.get(0).click();
        return browserTab.getCurrentUrl();
    }

    public boolean tearDown(){
        browser.close();
        return browser.isClosed();
    }
}

