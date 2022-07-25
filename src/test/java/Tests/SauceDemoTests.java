package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.SauceDemoPage;
import utils.Driver;
import utils.PropertyFileReader;

import java.io.IOException;
import java.util.Properties;

public class SauceDemoTests {
    public WebDriver driver;
    public String username_valid;
    public String password;
    public String username_invalid;
    public String password_invalid;
    public String username_locked;
    public String firstName;
    public String lastName;
    public String zipCode;
    @BeforeMethod
    public void testDataSetup() {
        Properties properties = PropertyFileReader.readPropertiesFile("testdata.properties");
        String browser = properties.getProperty("browser");
        driver = Driver.get(browser);
        username_valid = properties.getProperty("username_valid");
        password = properties.getProperty("password");
        username_invalid = properties.getProperty("username_invalid");
        password_invalid = properties.getProperty("password_invalid");
        username_locked = properties.getProperty("username_locked");
        firstName = properties.getProperty("firstname");
        lastName = properties.getProperty("lastname");
        zipCode = properties.getProperty("zipcode");
    }
    /*
    Test Case : Login with a valid user
     */
    @Test(priority = 1)
    public void verifyValidUserLogin(){
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_valid,password);
        sauceDemoPage.verifyHomePage();
    }

    /*
    Test Case : Login with a invalid user
     */
    @Test(priority = 2)
    public void verifyInvalidUserLogin(){
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_invalid,password_invalid);
        sauceDemoPage.verifyInvalidLogin();
    }

    /*
    Test Case : Logout from the home page
     */
    @Test(priority = 3)
    public void verifyLogout() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_valid,password);
        sauceDemoPage.verifyHomePage();
        sauceDemoPage.verifyLogout();
    }
    /*
    Test Case : Sort products by Price (low to high)
     */
    @Test(priority = 4)
    public void verifyPriceLowToHigh() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_valid,password);
        sauceDemoPage.verifyHomePage();
        sauceDemoPage.verifyPriceLowToHigh();
    }
    /*
    Test Case : Add multiple items to the shopping cart
     */
    @Test(priority = 5)
    public void verifyAddMultipleItemsToCart() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_valid,password);
        sauceDemoPage.verifyHomePage();
        sauceDemoPage.verifyPriceLowToHigh();
        sauceDemoPage.addToCart();
        sauceDemoPage.verifyItemsAddedToCart();
    }

    /*
    Test Case : Add the specific product ‘Sauce Labs Onesie’ to the shopping cart
    Test Case : Complete a purchase
     */
    @Test(priority = 6)
    public void verifyCompletePurchase() {
        SauceDemoPage sauceDemoPage = new SauceDemoPage(driver);
        sauceDemoPage.loginToApp(username_valid,password);
        sauceDemoPage.verifyHomePage();
        sauceDemoPage.verifyPriceLowToHigh();
        sauceDemoPage.completePurchase(firstName,lastName,zipCode);
    }
    @AfterMethod
    public void close(){
        driver.close();
    }
}