package com.EY.qa.framework;


import com.EY.qa.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Created by Aruna on 6/20/2016.
 */
public class WebApp {
    /* WebDriver variables */
    private static WebDriver driver = null;
    private static WebApp instance = null;
    private static final int timeout = 5;
    private static final String pageLoadedText = "Login with Username and Password";

    /* Logging variables */
    private static final Logger log = LogManager.getLogger(WebApp.class);

    /* Properties */
    private static Properties props = ReadProperties.getProperties();
    private static final String url = props.getProperty("url");
    private static final String username = props.getProperty("username");
    private static final String password = props.getProperty("password");
    private static boolean loggedIn = false;

    /* Private constructor - only one instance possible for this class */
    private WebApp() {
        instance = this;
    }

    public static WebApp getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new WebApp();
            return instance;
        }
    }

    public static String getBaseUrl() {
        return url;
    }

    public static WebDriver initializeDriver() {
        if (driver != null) {
            driver.quit();
        }

//        String browser = props.getProperty("browser");
//        if (browser == "firefox") {
//            driver = new FirefoxDriver();
//        } else if (browser == "chrome") {
//            driver = new ChromeDriver();
//        } else if (browser == "ie") {
//            driver = new InternetExplorerDriver();
//        } else {
//           // System.setProperty("webdriver.chrome.driver", "/Users/ashwingajjala/Downloads/chromedriver");
//            //driver = new ChromeDriver();
//            //ChromeDriverManager.getInstance().setup();
//        }

        WebDriverManager.chromedriver().setup();
        //Create a new ChromeDriver
        driver = new ChromeDriver();

//        DesiredCapabilities IEcaps = DesiredCapabilities.chrome();
//
//
//
//        //DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//
//        IEcaps.setCapability(CapabilityType.BROWSER_NAME, "chrome");//IEcaps .setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//
//        WebDriver driver = new ChromeDriver(IEcaps );

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // driver.manage().window().maximize();
        driver.get(props.getProperty("url"));
        loggedIn = false;
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = initializeDriver();
        }
        return driver;
    }

    public static LoginPage gotoLoginPage() {
        driver = getDriver();
        driver.manage().deleteAllCookies();
        loggedIn = false;
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        verifyPageLoaded();
        return loginPage;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
            loggedIn = false;
        }
    }

    protected void finalize() throws Throwable {
        quitDriver();
        super.finalize();
    }

    public static void resetDriver() {
        driver = getDriver();
        driver.manage().deleteAllCookies();
        loggedIn = false;
        driver.get(url);
    }

    public static void verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return;
    }

    public static void stopDriver() {
        if (driver != null) {
            driver.quit();
        }

    }
}
