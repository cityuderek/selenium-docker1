package com.vinsguru.pages.tmacms;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaffListPage extends BaseListPage {

    private static final Logger log = LoggerFactory.getLogger(StaffListPage.class);

//    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']")
//    private WebElement sideMenu;
//
    @FindBy(xpath = "//div[contains(@class, 'RaList-actions')]//input[@name='username']")
    private WebElement txtUsername;

    @FindBy(xpath = "//a[contains(@class, 'MuiButtonBase-root') and text()='Create']")
    private WebElement btnCreate;

    @FindBy(xpath = "//tbody[contains(@class, 'RaDatagrid-tbody')]//a[@aria-label='Edit' and text()='Edit']")
    private WebElement btnEdit;

    public StaffListPage(WebDriver driver) {
        super(driver, "staffs");
    }
    
    public boolean hasEditBtn() {
        wait.until(ExpectedConditions.visibilityOf(btnEdit));
        return this.btnEdit.isDisplayed();
    }
    
    public void clickEditBtn() {
    	btnEdit.click();
    }

    public void searchLoginName(String loginName){
        wait.until(ExpectedConditions.visibilityOf(txtUsername));
        txtUsername.sendKeys(loginName);
    }
}
