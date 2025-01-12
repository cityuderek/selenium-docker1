package com.vinsguru.tests.tmacms;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import com.vinsguru.util.Config;
import com.vinsguru.util.EmailHelper;

public class CustomReporter implements IReporter{
   @Override
   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory) {

      //Iterating over each suite included in the test
      for (ISuite suite : suites) {

         //Following code gets the suite name
         String suiteName = suite.getName();

         //Getting the results for the said suite
         Map<String, ISuiteResult> suiteResults = suite.getResults();
         for (ISuiteResult sr : suiteResults.values()) {
            ITestContext tc = sr.getTestContext();
            StringBuilder su = new StringBuilder();
            su.append("Passed tests for suite '" + suiteName +
                    "' is:" + tc.getPassedTests().getAllResults().size() + "\r\n");
            su.append("Failed tests for suite '" + suiteName +
                    "' is:" + tc.getFailedTests().getAllResults().size() + "\r\n");
            su.append("Skipped tests for suite '" + suiteName +
                    "' is:" + tc.getSkippedTests().getAllResults().size() + "\r\n");
            System.out.println(su);
            
            boolean hasFail = tc.getFailedTests().getAllResults().size() > 0;
            if(hasFail) {
        		EmailHelper.sendEmail(Config.get("report.to"), "Selenium " + suiteName, su.toString(), "test-output\\emailable-report.html");
            }
         }
      }
   }
}