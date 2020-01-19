package edureka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    private WebDriver registerPageDriver;

    @FindBy(name = "firstName")
    @CacheLookup
    private WebElement firstName;

    @FindBy(name = "lastName")
    @CacheLookup
    private WebElement lastName;

    @FindBy(name = "phone")
    @CacheLookup
    private WebElement phone;

    @FindBy(id = "userName")
    @CacheLookup
    private WebElement userName;

    @FindBy(name = "address1")
    @CacheLookup
    private WebElement address1;

    @FindBy(name = "city")
    @CacheLookup
    private WebElement city;

    @FindBy(name = "state")
    @CacheLookup
    private WebElement state;

    @FindBy(name = "postalCode")
    @CacheLookup
    private WebElement postalCode;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(name = "confirmPassword")
    @CacheLookup
    private WebElement confirmPassword;

    @FindBy(name = "register")
    @CacheLookup
    private WebElement registerBtn;

    public RegisterPage(WebDriver driver) {
        registerPageDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String registerUser() {

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        phone.sendKeys("1234567890");
        userName.sendKeys("john@doe.com");
        address1.sendKeys("22, Park Street");
        city.sendKeys("New York");
        state.sendKeys("NY");
        postalCode.sendKeys("000");
        email.sendKeys("johndoe");
        password.sendKeys("1234");
        confirmPassword.sendKeys("1234");
        registerBtn.click();

        return registerPageDriver.getCurrentUrl();
    }

}