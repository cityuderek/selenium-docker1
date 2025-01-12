package com.vinsguru.util;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.api.internal.StringUtils;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SeleniumHelper {
	public static boolean waitVisible(WebElement ele, WebDriverWait wait) {
	     wait.until(ExpectedConditions.visibilityOf(ele));
	     return ele.isDisplayed();
	}
}
