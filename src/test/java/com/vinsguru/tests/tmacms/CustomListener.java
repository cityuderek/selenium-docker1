package com.vinsguru.tests.tmacms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter{
    private static final Logger log = LoggerFactory.getLogger(CustomListener.class);

   @Override
   public void onTestFailure(ITestResult tr) {
      log.info(tr.getName()+ "--Test method failed\n");
   }

   @Override
   public void onTestSkipped(ITestResult tr) {
	   log.info(tr.getName()+ "--Test method skipped\n");
   }

   @Override
   public void onTestSuccess(ITestResult tr) {
	   log.info(tr.getName()+ "--Test method success\n");
   }
}
