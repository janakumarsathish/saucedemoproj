package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static WebDriver driver;
    private Driver(){
    }
    /*
    @MethodName = get
    @Description = This method is used to get selenium web driver with given browser type
    @Parameter = string (browser type)
 */
    public static WebDriver get(String browser){
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            return driver;
        } else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            return driver;
        } else if(browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            return driver;
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
