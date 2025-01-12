package com.vinsguru.tests.tmacms;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.pages.tmacms.DashboardPage;
//import com.vinsguru.pages.tmacms.DashboardPage;
import com.vinsguru.pages.tmacms.LoginPage;
import com.vinsguru.pages.tmacms.StaffCreatePage;
import com.vinsguru.pages.tmacms.StaffEditPage;
import com.vinsguru.pages.tmacms.StaffListPage;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.tmacms.model.TmaCmsTestData;
import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import com.vinsguru.util.JsonUtil;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    private StaffListPage staffListPage;
    private StaffEditPage staffEditPage;
    private StaffCreatePage staffCreatePage;
    private TmaCmsTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
//        log.debug("setPageObjects BEGIN testDataPath={}", testDataPath);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.staffListPage = new StaffListPage(driver);
        this.staffEditPage = new StaffEditPage(driver);
        this.staffCreatePage = new StaffCreatePage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, TmaCmsTestData.class);
//        log.debug("setPageObjects END");
    }

    @Test
    public void loginTest(){
        log.info("loginTest BEGIN");
    	String url = getUrl("Login");// Config.get(Constants.TMA_CMS_URL);
        log.debug("loginTest BEGIN url={}", url);
        loginPage.goTo(url);
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
//        log.debug("loginTest END");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        log.info("dashboardTest BEGIN");
        Assert.assertTrue(dashboardPage.isAt());
        log.info("dashboardTest END");

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
        log.info("gotoStaffList BEGIN");
        dashboardPage.gotoStaffList();
        String title = dashboardPage.getTitleText();
        log.debug("gotoStaffList title={}", title);
        Assert.assertTrue(dashboardPage.isTitleEquals("Staff"));
//        Assert.assertTrue(dashboardPage.isAt());
        
    }

    @Test(dependsOnMethods = "gotoStaffList")
    public void gotoStaffCreate(){
        log.info("gotoStaffCreate BEGIN");
        dashboardPage.gotoCreate();
//        String title = dashboardPage.getTitleText();
//        log.debug("gotoStaffCreate title={}", title);
        Assert.assertTrue(dashboardPage.isTitleEquals("Create Staff"));
    }

    @Test(dependsOnMethods = "gotoStaffCreate")
    public void staffCreate(){
        log.info("staffCreate BEGIN");
//    	staffCreate.isAt();
//        String title = dashboardPage.getTitleText();
//        log.debug("gotoStaffCreate title={}", title);
        Assert.assertTrue(staffCreatePage.isAt());
        staffCreatePage.fill(testData.newStaffLogin(), testData.newStaffPwd(),testData.newStaffDisplayName(), testData.newStaffEmail());
//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlToBe(TmaCmsTest.getUrl("StaffList")));
        Assert.assertTrue(staffListPage.isAt());
    }

	@Test(dependsOnMethods = "staffCreate")
	public void delStaffIfExist(){
	    log.info("delStaffIfExist BEGIN");
	    Assert.assertTrue(staffListPage.isAt());
	    staffListPage.searchLoginName(testData.newStaffLogin());
	//    Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
	    Assert.assertTrue(staffListPage.hasEditBtn());
	    if(staffListPage.hasEditBtn()) {
	        log.info("delStaffIfExist hasEditBtn");
	    	staffListPage.clickEditBtn();
	        Assert.assertTrue(staffEditPage.isAt());
	//        staffEditPage.test();
	        staffEditPage.clickDelBtn();
	//        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(10));
	    }
	}
  
	  @Test(dependsOnMethods = "delStaffIfExist")
	  public void logoutTest(){
	  log.info("logoutTest BEGIN");
	  dashboardPage.logout();
	  Assert.assertTrue(loginPage.isAt());
	  }

//		sendEmail(Config.get("report.to"), "TMA selenium", "ffff", "test-output\\emailable-report.html");
		
	public static String getUrl(String page){
		String baseUrl = Config.get("tmaCms.baseUrl");
		if(page == null) return baseUrl;
		if (page.equals("StaffCreate")) {
			return baseUrl + "/#/fdmin/staffs/create";
		}
		if (page.equals("StaffList")) {
			return baseUrl + "/#/fdmin/staffs";
		}
		if (page.equals("Login")) {
			return baseUrl + "/#/fdmin/login";
		}
		return baseUrl;
	}
    
//	public static String getBaseUrl(){
//		return Config.get("tmaCms.baseUrl");
//	}
}
