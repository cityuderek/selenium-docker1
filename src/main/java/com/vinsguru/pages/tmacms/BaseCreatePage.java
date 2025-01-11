package com.vinsguru.pages.tmacms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseCreatePage extends DashboardPage {
    private static final Logger log = LoggerFactory.getLogger(BaseCreatePage.class);

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement btnSave;

	public BaseCreatePage(WebDriver driver, String resourceId, String resourceAction) {
		super(driver, resourceId, resourceAction);
	}
}
