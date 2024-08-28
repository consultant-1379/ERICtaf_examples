package com.ericsson.cifwk.taf.demo.test.viewmodel;

import java.util.List;
import com.ericsson.cifwk.taf.ui.core.UiComponent;
import com.ericsson.cifwk.taf.ui.core.UiComponentMapping;
import com.ericsson.cifwk.taf.ui.sdk.GenericViewModel;
import com.ericsson.cifwk.taf.ui.sdk.Link;

public class CreateTestCaseViewModel extends GenericViewModel {

    @UiComponentMapping(".eaTM-ActionLink-link")
    private List<Link> createTestCase;

    @UiComponentMapping("#TMS_CreateTestCase_DialogBox-productSelect")
    private UiComponent uiDropDownMenu;

    @UiComponentMapping(".eaTM-Menu-button")
    private Link menuList;

    @UiComponentMapping(".ebDialogBox")
    private UiComponent dialogBox;

    @UiComponentMapping(".ebDialog .ebDialogBox-actionBlock > .ebBtn.ebBtn_color_green")
    private UiComponent okButton;

    @UiComponentMapping(".ebComponentList")
    private UiComponent menuListItem;

    @UiComponentMapping(".ebComponentList .ebComponentList-item")
    private List<UiComponent> componentItems;

    public UiComponent getMenuList() {
        return menuListItem;
    }

    public void clickMenuButton(){
        menuList.click();
    }

    public UiComponent getDialogBox(){
        return dialogBox;
    }

    public void clickCreateTestCase(String optionName) {
        for (UiComponent componentItem : componentItems) {
            if (componentItem.getText().equals(optionName)) {
                componentItem.click();
                break;
            }
        }
    }

    public void clickProductType(String productType){
        uiDropDownMenu.click();
        for (UiComponent componentItem : componentItems) {
            if (componentItem.getText().equals(productType)) {
                componentItem.click();
                break;
            }
        }
        okButton.click();
    }

    public void createTestCase(String optionName) {
        for(Link link : createTestCase){
            if(link.getText().equalsIgnoreCase(optionName)){
                link.click();
                break;
            }
        }
    }
}
