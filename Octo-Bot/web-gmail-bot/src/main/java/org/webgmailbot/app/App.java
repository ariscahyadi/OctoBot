package org.webgmailbot.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.gmail.com");
        //Send email address
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(args[0]);
        driver.findElement(By.xpath("//div[@id='identifierNext']")).click();

        //send password
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(args[1]);
        driver.findElement(By.xpath("//div[@id='passwordNext']")).click();

        //read email

        //Click on compose button
        //driver.findElement(By.xpath("//div[text()='Compose']")).click();

        //Enter the sender mail id
        //driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("Sender e-mail id");
        //driver.findElement(By.xpath("//div[@name='to']")).sendKeys("dcsacr@nus.edu.sg");
        //Enter subject to the mail
        //driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Selenium script");
        //driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys("Selenium script to send mail");

        //Attach the full path of file
        //driver.findElement(By.xpath("//input[@name='Filedata']")).sendKeys("D:\\maven.txt");
        //driver.findElement(By.xpath("//div[text()='Send']")).click();

        Thread.sleep(20000);

        // Close Browser
        driver.close();
    }
}