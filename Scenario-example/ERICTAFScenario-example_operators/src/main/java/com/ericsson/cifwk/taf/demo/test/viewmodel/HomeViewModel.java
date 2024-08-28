package com.ericsson.cifwk.taf.demo.test.viewmodel;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;

public class HomeViewModel extends GenericViewModel {

    @UiComponentMapping(".eaTM-ContentBoxItem-text")
    private List<UiComponent> selectTestCase;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeViewModel.class);

    public void selectTestCase(String select) {
        for (UiComponent component: selectTestCase){
            if(component.getText().equalsIgnoreCase("Select a Test Case")){
                LOGGER.info("Clicked '{}'.", component.getText());
                component.click();
                return;
            }
        }
        throw new RuntimeException("Select test case button not found");
    }
}
