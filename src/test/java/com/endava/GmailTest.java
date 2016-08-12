package com.endava;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by mapostol on 7/29/2016.
 */


public class GmailTest {
    static WebDriver webDriver;
    @BeforeClass
    public static void setUp() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://gmail.com");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Test
    public void testGmail() {
        WebElement emailField = webDriver.findElement(By.xpath("//input[@id=\"Email\"]"));
        emailField.sendKeys("endavaselenium");
        WebElement nextButton = webDriver.findElement(By.xpath("//input[@id='next']"));
        nextButton.click();
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@id='Passwd']"));
        passwordField.sendKeys("endavaqa");
        WebElement signInButton =webDriver.findElement(By.xpath("//input[@id='signIn']"));
        signInButton.click();
        WebElement composeButton = webDriver.findElement(By.xpath("//div[text()=\"COMPOSE\"]"));
        composeButton.click();
        WebElement toFielf =webDriver.findElement(By.xpath("//textarea[@name=\"to\"]"));
        toFielf.sendKeys("endavaselenium@gmail.com");
        toFielf.sendKeys(Keys.RETURN);
        WebElement subjectField = webDriver.findElement(By.xpath("//input[@name='subjectbox']"));
        subjectField.sendKeys("Test");
        WebElement messageBox = webDriver.findElement(By.xpath("//div[@aria-label='Message Body']"));
        messageBox.sendKeys("Testeasfsdfs");
        WebElement sendButton = webDriver.findElement(By.xpath("//div[@aria-label='Send \u202A(Ctrl-Enter)\u202C']"));
        sendButton.click();

        WebElement firstRowEmail = webDriver.findElement(By.xpath("//table/tbody/tr[1]/td[5]/div[@class='yW']"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions actions = new Actions(webDriver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
        if(isAlertPresent()){
            webDriver.switchTo().alert().accept();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        firstRowEmail.click();
        WebElement verifyNameAndPass = webDriver.findElement(By.xpath("//h3//span[@name='endava automation' and @email='endavaselenium@gmail.com']"));
        assertEquals("endava automation", verifyNameAndPass.getAttribute("name"));
        assertEquals("endavaselenium@gmail.com", verifyNameAndPass.getAttribute("email"));



    }



    @AfterClass
    public static void tearDown() {
        webDriver.close();
    }

    public boolean isAlertPresent() {
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}