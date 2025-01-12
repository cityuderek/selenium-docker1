package com.vinsguru.pages.tmacms;

import com.vinsguru.pages.AbstractPage;

import ch.qos.logback.core.util.StringUtil;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    protected String resourceId = "";

    protected String resourceAction = "list";
    
    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']")
    protected WebElement sideMenu;

    @FindBy(xpath = "//button[@aria-label='Profile']")
    protected WebElement btnProfile;

    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']//div[text()='System']")
    protected WebElement btnSystem;

    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']//a[text()='Staff']")
    protected WebElement btnStaff;

    @FindBy(xpath = "//span[contains(@class, 'RaConfigurable-root')]")
    protected WebElement title;

    @FindBy(xpath = "//a[contains(@class, 'MuiButtonBase-root') and text()='Create']")
    protected WebElement btnCreate;

    @FindBy(xpath = "//div[@role='presentation']//button[text()='Confirm']")
    protected WebElement btnModalConfirm;

//
//    @FindBy(id = "profit-margin")
//    private WebElement profitMarginElement;
//
//    @FindBy(id = "available-inventory")
//    private WebElement availableInventoryElement;
//
//    @FindBy(css = "#dataTable_filter input")
//    private WebElement searchInput;
//
//    @FindBy(id = "dataTable_info")
//    private WebElement searchResultsCountElement;
//
//    @FindBy(css = "img.img-profile")
//    private WebElement userProfilePictureElement;

    // prefer id / name / css
//    @FindBy(xpath = "//span[text()='Logout']")
    @FindBy(xpath = "//div[contains(@class, 'MuiListItemText-root')]//span[text()='Logout']")
    protected WebElement logoutLink;

//    @FindBy(css = "#logoutModal a")
//    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage(WebDriver driver, String resourceId, String resourceAction) {
        super(driver);
        this.resourceId = resourceId;
        this.resourceAction = resourceAction;
    }

    @Override
    public boolean isAt() {
    	log.debug("isAt resourceId=" + resourceId + ", resourceAction=" + resourceAction + ", url=" + driver.getCurrentUrl());
        this.wait.until(ExpectedConditions.visibilityOf(sideMenu));
//    	log.debug("isAt 222");
        if(!sideMenu.isDisplayed()) {
        	log.debug("!sideMenu");
        	return false;
        }
        if (resourceAction.equals("list")) {
        	return isAtListPage();
        }
        if (resourceAction.equals("edit")) {
        	return isAtEditPage();
        }
        if (resourceAction.equals("create")) {
        	return isAtCreatePage();
        }
//    	log.debug("resourceId=" + resourceId);
        return false;
    }

    public boolean isAtListPage() {
    	String resourceId = this.getResourceId();
    	return StringUtil.isNullOrEmpty(resourceId) || wait.until(ExpectedConditions.urlMatches(".*/#/fdmin/" + resourceId + "^"));
    }

    public boolean isAtEditPage() {
    	String resourceId = this.getResourceId();
//    	log.debug("isAtEditPage regex=" + ".*/#/fdmin/" + resourceId + "/\\d+");
    	return StringUtil.notNullNorEmpty(resourceId) && wait.until(ExpectedConditions.urlMatches(".*/#/fdmin/" + resourceId + "/\\d+"));
    }

    public boolean isAtCreatePage() {
    	String resourceId = this.getResourceId();
    	return StringUtil.notNullNorEmpty(resourceId) && wait.until(ExpectedConditions.urlMatches(".*/#/fdmin/" + resourceId + "/create"));
    }

    public boolean isTitleEquals(String expTitle) {
    	return wait.until(ExpectedConditions.textToBePresentInElement(title, expTitle));
    }

    public String getResourceId() {
        return resourceId;
    }
    
//    public String getMonthlyEarning(){
//        return this.monthlyEarningElement.getText();
//    }
//
//    public String getAnnualEarning(){
//        return this.annualEarningElement.getText();
//    }

    public String getTitleText(){
        return this.title.getText();
    }
//
//    public String getAvailableInventory(){
//        return this.availableInventoryElement.getText();
//    }
//
//    public void searchOrderHistoryBy(String keyword){
//        this.searchInput.sendKeys(keyword);
//    }

    /*
        Showing 1 to 10 of 32 entries (filtered from 99 total entries)
        arr[0] = "Showing"
        arr[1] = "1"
        arr[2] = "to"
        arr[3] = "10"
        arr[4] = "of"
        arr[5] = "32"
        ...
        ...
     */
//    public int getSearchResultsCount(){
//        String resultsText = this.searchResultsCountElement.getText();
//        String[] arr = resultsText.split(" ");
//        // if we do not have 5th item, it would throw exception.
//        // that's fine. we would want our test to fail anyway in that case!
//        int count = Integer.parseInt(arr[5]);
//        log.info("Results count: {}", count);
//        return count;
//    }
//
    public void gotoStaffList(){
    	btnSystem.click();
        wait.until(ExpectedConditions.visibilityOf(btnStaff));
        btnStaff.click();
    }

    public void gotoCreate(){
        wait.until(ExpectedConditions.visibilityOf(btnCreate));
        btnCreate.click();
    }
    
    public void logout(){
        this.btnProfile.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
    }

}
