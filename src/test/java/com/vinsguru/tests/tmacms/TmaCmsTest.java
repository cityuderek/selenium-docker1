package com.vinsguru.tests.tmacms;

import com.vinsguru.pages.tmacms.DashboardPage;
//import com.vinsguru.pages.tmacms.DashboardPage;
import com.vinsguru.pages.tmacms.LoginPage;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.tmacms.model.TmaCmsTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TmaCmsTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(TmaCmsTest.class);

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TmaCmsTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
//        log.debug("setPageObjects BEGIN testDataPath={}", testDataPath);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, TmaCmsTestData.class);
//        log.debug("setPageObjects END");
    }

    @Test
    public void loginTest(){
    	String url = Config.get(Constants.TMA_CMS_URL);
        log.debug("loginTest BEGIN url={}", url);
        loginPage.goTo(url);
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
//        log.debug("loginTest END");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
//        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
//        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
//        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
//        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
//
//        // order history search
//        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
//        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void gotoStaffList(){
        dashboardPage.gotoStaffList();
        String title = dashboardPage.getTitleText();
        log.debug("gotoStaffList title={}", title);
        Assert.assertTrue(dashboardPage.isTitleEquals("Staff"));
//        Assert.assertTrue(dashboardPage.isAt());
        
    }

    @Test(dependsOnMethods = "gotoStaffList")
    public void gotoStaffCreate(){
        dashboardPage.gotoCreate();
        String title = dashboardPage.getTitleText();
        log.debug("gotoStaffCreate title={}", title);
        Assert.assertTrue(dashboardPage.isTitleEquals("Create Staff"));
    }

    @Test(dependsOnMethods = "gotoStaffCreate")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
