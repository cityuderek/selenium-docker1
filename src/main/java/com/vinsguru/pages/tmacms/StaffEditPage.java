package com.vinsguru.pages.tmacms;

import com.vinsguru.pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaffEditPage extends BaseEditPage {
    private static final Logger log = LoggerFactory.getLogger(StaffEditPage.class);

//    @FindBy(css = "input[name='username']")
//    protected WebElement usernameInput;
//
//    @FindBy(css = "input[name='password']")
//    protected WebElement txtPwd1;
//
//    @FindBy(css = "input[name='confirmPassword']")
//    protected WebElement txtPwd2;
//
//    @FindBy(css = "input[name='displayName']")
//    protected WebElement txtDisplayName;
//
//    @FindBy(css = "input[name='email']")
//    protected WebElement txtEmail;

    public StaffEditPage(WebDriver driver) {
        super(driver, "staffs");
//    	log.info(" StaffEditPage driver=" + driver);
//    	System.out.println("LoginPage");
    }

//    @Override
//    public boolean isAt() {
//        this.wait.until(ExpectedConditions.visibilityOf(this.usernameInput));
//        return this.usernameInput.isDisplayed();
//    }
//
//    public void fill(String login, String pwd, String displayName, String email){
//    	usernameInput.sendKeys(login);
//    	txtPwd1.sendKeys(pwd);
//    	txtPwd2.sendKeys(pwd);
//    	txtDisplayName.sendKeys(displayName);
//    	txtEmail.sendKeys(email);
//    	btnSave.click();
//    }

//    public void goTo(String url){
//    	log.debug("driver=" + driver);
//    	log.debug("url=" + url);
//        this.driver.get(url);
//    }
//
//    public void login(String username, String password){
//        this.usernameInput.sendKeys(username);
//        this.passwordInput.sendKeys(password);
//        this.loginButton.click();
//    }

}
