package com.vinsguru.pages.tmacms;

import org.openqa.selenium.WebDriver;

public class BaseListPage extends DashboardPage {

	public BaseListPage(WebDriver driver, String resourceId) {
		super(driver, resourceId, "list");
	}
}
