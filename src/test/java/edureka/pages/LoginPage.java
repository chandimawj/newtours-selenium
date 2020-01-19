package edureka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver loginPageDriver;

	@FindBy(name = "userName")
	@CacheLookup
	private WebElement username;

	@FindBy(name = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(xpath = "//input[@name='login']")
	@CacheLookup
	private WebElement loginBtn;

	@FindBy(linkText = "REGISTER")
	@CacheLookup
	private WebElement registerLnk;

	public LoginPage(WebDriver driver) {
		loginPageDriver = driver;
		PageFactory.initElements(driver, this);

	}

	public String loginToDemoaut(String userName, String passWord) {
		username.sendKeys(userName);
		password.sendKeys(passWord);
		loginBtn.click();

		WebDriverWait wait = new WebDriverWait(loginPageDriver, 30);
		wait.until(ExpectedConditions.titleContains("Find a Flight"));

		return loginPageDriver.getTitle();
	}

	public WebDriver clickRegister() {
		registerLnk.click();
		return loginPageDriver;
	}

}