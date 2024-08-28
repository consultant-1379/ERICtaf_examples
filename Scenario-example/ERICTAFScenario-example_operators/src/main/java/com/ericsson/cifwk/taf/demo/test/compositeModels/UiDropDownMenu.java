package com.ericsson.cifwk.taf.demo.test.compositeModels;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.ui.core.AbstractUiComponent;
import com.ericsson.cifwk.taf.ui.core.SelectorType;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.Link;

public class UiDropDownMenu extends AbstractUiComponent{

    private static final Logger LOGGER = LoggerFactory.getLogger(UiDropDownMenu.class);

    @UiComponentMapping(selector = ".ebComponentList-item", selectorType = SelectorType.CSS)
    private List<Link> dropDownOptions;

    public void clickOptionByName(String optionName){
        for(Link link : dropDownOptions){
            if(link.getText().equalsIgnoreCase(optionName)){
                link.click();
                LOGGER.info("Clicked '{}'.", optionName);
                break;
            }
        }
    }

    public void clickMultipleOptions(String... optionNames){
        for(UiComponent component: dropDownOptions){
            for(String optionName : optionNames){
                if(component.getText().equalsIgnoreCase(optionName)){
                    component.getDescendantsBySelector(".ebCheckbox").get(0).click();
                    LOGGER.info("Clicked '{}'.", optionName);
                    break;
                }
            }
        }
    }

}
