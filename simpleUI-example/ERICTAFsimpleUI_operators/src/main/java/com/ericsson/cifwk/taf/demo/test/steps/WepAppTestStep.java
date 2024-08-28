package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.operators.WebAppUiOperator;

/**
 * Created by eendjor on 01/06/2016.
 */
public class WepAppTestStep{

    private static final Logger LOGGER = LoggerFactory.getLogger(WepAppTestStep.class);

    public static final String EXECUTE_SEARCH = "executeSearch";

    @Inject
    private Provider<WebAppUiOperator> provider;

    /**
     * @throws InterruptedException
     * @DESCRIPTION Enter search term in search box, receive correct search results and follow link
     * @PRE Verify Connection to Internet
     * @PRIORITY MEDIUM
     */
    @TestStep(id = WepAppTestStep.EXECUTE_SEARCH)
    public void executeSearch(
            @Input("search") String searchString,
            @Output("bingResult") String bingResult,
            @Output("followFirstResult") String followFirstResult) throws InterruptedException {

        WebAppUiOperator webAppOperator = provider.get();
        webAppOperator.openBrowser();
        LOGGER.info("Enter text in search box, hit search button");
        String result = webAppOperator.enterSearchTerm(searchString);
        assertThat(result.contains(bingResult)).isTrue();
        LOGGER.info("Search for term, follow link returned");
        assertThat(webAppOperator.followFirstResult()).isEqualTo(followFirstResult);
        assertThat(webAppOperator.tearDown()).isTrue();
    }

}
