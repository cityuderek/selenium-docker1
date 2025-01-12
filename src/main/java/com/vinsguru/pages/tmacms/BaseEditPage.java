package com.vinsguru.pages.tmacms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfa.selenium.util.SeleniumUtil;

public class BaseEditPage extends DashboardPage {
    private static final Logger log = LoggerFactory.getLogger(BaseCreatePage.class);

    @FindBy(xpath = "//form//button[@type='submit' and @aria-label='Save']")
    protected WebElement btnSave;

    @FindBy(xpath = "//form//button[@type='button' and @aria-label='Delete']")
    protected WebElement btnDel;

	public BaseEditPage(WebDriver driver, String resourceId) {
		super(driver, resourceId, "edit");
	}
    
    public void test() {
        log.debug("btnDel=" + btnDel.isDisplayed());
        log.debug("btnSave=" + btnSave.isDisplayed());
    }
    
    public void clickSaveBtn() {
    	btnSave.click();
    }
    
    public void clickDelBtn() {
    	btnDel.click();
    	SeleniumUtil.waitVisible(btnModalConfirm, wait);
    	btnModalConfirm.click();
    }
}
