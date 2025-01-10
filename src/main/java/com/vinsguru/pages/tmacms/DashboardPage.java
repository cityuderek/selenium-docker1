package com.vinsguru.pages.tmacms;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']")
    private WebElement sideMenu;

    @FindBy(xpath = "//button[@aria-label='Profile']")
    private WebElement btnProfile;

    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']//div[text()='System']")
    private WebElement btnSystem;

    @FindBy(xpath = "//main[@class='RaLayout-contentWithSidebar']//a[text()='Staff']")
    private WebElement btnStaff;

    @FindBy(xpath = "//span[contains(@class, 'RaConfigurable-root')]")
    private WebElement title;

    @FindBy(xpath = "//a[contains(@class, 'MuiButtonBase-root') and text()='Create']")
    private WebElement btnCreate;

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
    private WebElement logoutLink;

//    @FindBy(css = "#logoutModal a")
//    private WebElement modalLogoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(sideMenu));
        return sideMenu.isDisplayed();
    }

    public boolean isAtStaffList() {
    return wait.until(ExpectedConditions.textToBePresentInElement(title, "Staff"));
    }

    public boolean isTitleEquals(String expTitle) {
    return wait.until(ExpectedConditions.textToBePresentInElement(title, expTitle));
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
