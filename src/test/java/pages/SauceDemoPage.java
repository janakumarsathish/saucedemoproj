package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/*
@ClassName = SauceDemoPage
@Description = This class is used to store page objects and methods to build test cases
@Parameter = driver
*/
public class SauceDemoPage {
    WebDriver driver;
    public SauceDemoPage(WebDriver driver){
        this.driver = driver;
    }

    /* Page objects : Login Page*/
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By loginError = By.xpath("//h3[contains(text(),'Username and password')]");
    By loginLogo = By.xpath("//div[@class='login_logo']");

    /* Page objects : Home Page*/
    By sideMenuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");
    By productsHeader = By.xpath("//span[contains(text(),'Products')]");
    By productSelector = By.xpath("//select[@class='product_sort_container']");
    By lowestPrice = By.xpath("//div[contains(text(),'Sauce Labs Onesie')]");
    By addToCartElements = By.xpath("//div[contains(text(),'Sauce Labs Onesie')]/following::div/button");
    By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
    By sauceOnesie = By.xpath("//div[contains(text(),'Sauce Labs Onesie')]");
    By sauceBikeLight = By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]");

    /* Page objects : Checkout Page*/
    By checkOutButton = By.id("checkout");
    By enterFirstName = By.id("first-name");
    By lastFirstName = By.id("last-name");
    By zipCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By orderConfirmMessage = By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]");

    /*
    @MethodName = loginToApp
    @Description = This method is used to login to application
    @Parameter = string, string (username, password)
    */
    public void loginToApp(String user, String pass){
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();

    }

    /*
    @MethodName = verifyHomePage
    @Description = This method is used to verify user is on home page
    @Parameter = null
    */
    public void verifyHomePage(){
        String products = driver.findElement(productsHeader).getText();
        Assert.assertTrue(products.equalsIgnoreCase("Products"));
    }
    /*
    @MethodName = verifyInvalidLogin
    @Description = This method is used to error message for invalid user
    @Parameter = null
    */
    public void verifyInvalidLogin(){
        String errorMesasge = driver.findElement(loginError).getText();
        System.out.println(errorMesasge);
        Assert.assertTrue(errorMesasge.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"));
    }
    /*
    @MethodName = verifyLogout
    @Description = This method is used to logout
    @Parameter = null
    */
    public void verifyLogout() {
        driver.findElement(sideMenuButton).click();
        driver.findElement(logoutButton).isDisplayed();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        Assert.assertTrue(driver.findElement(loginLogo).isDisplayed());
    }
    /*
    @MethodName = verifyPriceLowToHigh
    @Description = This method is used to select filter drop down from price : low to high
    @Parameter = null
    */
    public void verifyPriceLowToHigh() {
        driver.findElement(productSelector).click();
        Select select = new Select(driver.findElement(productSelector));
        select.selectByValue("lohi");
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(lowestPrice)));
        Assert.assertTrue(driver.findElement(lowestPrice).isDisplayed());
    }
    /*
    @MethodName = addToCart
    @Description = This method is used to add 2 products to cart
    @Parameter = null
    */
    public void addToCart(){
        List<WebElement> addToCartList = driver.findElements(addToCartElements);
        addToCartList.get(0).click();
        addToCartList.get(1).click();
    }
    /*
    @MethodName = verifyItemsAddedToCart
    @Description = This method is used to verify items are added to cart
    @Parameter = null
    */
    public void verifyItemsAddedToCart(){
        driver.findElement(cartIcon).click();
        Assert.assertTrue(driver.findElement(sauceOnesie).isDisplayed());
        Assert.assertTrue(driver.findElement(sauceBikeLight).isDisplayed());
    }
    /*
    @MethodName = completePurchase
    @Description = This method is used verify end to end complete purchase
    @Parameter = string, string, string (first name, last name, postal code)
    */
    public void completePurchase(String firstName, String lastName, String postalCode){
        List<WebElement> addToCartList = driver.findElements(addToCartElements);
        addToCartList.get(0).click();
        driver.findElement(cartIcon).click();
        driver.findElement(checkOutButton).click();
        driver.findElement(enterFirstName).sendKeys(firstName);
        driver.findElement(lastFirstName).sendKeys(lastName);
        driver.findElement(zipCode).sendKeys(postalCode);
        driver.findElement(continueButton).click();
        driver.findElement(finishButton).click();
        Assert.assertTrue(driver.findElement(orderConfirmMessage).isDisplayed());
    }
}