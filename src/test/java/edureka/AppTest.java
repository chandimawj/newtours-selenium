package edureka;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import edureka.constants.IConstants;
import edureka.pages.FindFlightPage;
import edureka.pages.LoginPage;
import edureka.pages.RegisterPage;
import edureka.pages.SelectFlightPage;

public class AppTest {

    public WebDriver driver;

    @BeforeMethod
    @Parameters({ "browserName" })
    public void launchBrowser(String browserName) {

        // System.setProperty("webdriver.chrome.driver", IConstants.CHROMEDRIVERPATH);
        System.setProperty("webdriver.gecko.driver", IConstants.GECKODRIVERPATH);
        // ChromeOptions options = new ChromeOptions();
        FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);
        driver = new FirefoxDriver(options);
        // driver = new ChromeDriver(options);

        Reporter.log("Browser Opened", true); // setting to true will print in console too

        driver.manage().timeouts().implicitlyWait(IConstants.IMPLICITWAITSTND, TimeUnit.SECONDS);
        driver.get("http://newtours.demoaut.com");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test(description = "Register User Mercury Tours")
    public void registerUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(loginPage.clickRegister());
        String currentUrl = registerPage.registerUser();

        Assert.assertEquals(currentUrl.contains("create_account_success"), true);
    }

    @Test(description = "Login to Mercury Tours")
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);

        String currentTitle = loginPage.loginToDemoaut("mercury", "mercury");

        // checking if there was any error thrown
        String pageSource = driver.getPageSource();

        if (pageSource.contains("Whitelabel Error")) {
            Reporter.log("Login Unsuccessful. Fatal Error", true);
            driver.quit();

        } else {
            Assert.assertEquals(currentTitle, "Find a Flight: Mercury Tours:");
            Reporter.log("Login Successful", true);
        }

    }

    @Test(description = "Book a Flight")
    public void selectFlightTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToDemoaut("mercury", "mercury");
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.findFlight();
    
        SelectFlightPage selectFlightPage = new SelectFlightPage(driver);

        selectFlightPage.selectFlights();
        String title = selectFlightPage.verifyBookFlightTitle();
        Assert.assertEquals(title, "Book a Flight: Mercury Tours");

    }

    @AfterMethod
    public void tearDown() {
        driver.close();

    }
}
