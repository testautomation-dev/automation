package com.EY.qa.framework;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SetupTestDriver {
    private WebDriver driver = null;
    private String browser = null;
   // private String baseUrl = null;
    private String os = null;
    private String node = null;
    private String env = null;
    Properties props = null;

    public  SetupTestDriver() throws MalformedURLException {
        this.browser = System.getProperty("browser") != null ? System.getProperty("browser"):"chrome" +
                "";
        this.os =  System.getProperty("os") != null ? System.getProperty("os"):"windows";
       // this.baseUrl = baseUrl;
        this.node = System.getProperty("node") != null ? System.getProperty("node"):"http://localhost:5555";
        this.env= System.getProperty("env") != null ? System.getProperty("env"):"dev";


        Platform platform = Platform.fromString(os.toUpperCase());
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", platform);
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            //System.setProperty("webdriver.gecko.driver", "C:\\Users\\BZ324DG\\Desktop\\Selenium\\geckodriver-v0.9.0-win64\\geckodriver.exe");//This is the location where you have installed Firefox on your machine
             //driver = new FirefoxDriver(options);
//            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//            capabilities.setCapability("marionette", true);
//            capabilities.setCapability("gecko", true);
            //this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), capabilities);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), options);
        } else {
           File file = new File("C:\\Users\\BZ324DG\\Desktop\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
//
           //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            //InternetExplorerOptions ieOption = new InternetExplorerOptions();

//            ieOption.setCapability("platform", platform);
//            ieOption.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            //driver = new RemoteWebDriver(new URL(node + "/wd/hub"),
              //      capability);
            //DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            //caps.setCapability("ignoreZoomSetting", true);

            //this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), ieOption);
            //driver = new InternetExplorerDriver();
            //driver.manage().window().maximize();
            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            this.driver = new RemoteWebDriver(new URL(node +"/wd/hub"), capability);
            this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }

        this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.props = ReadProperties.getProperties();


    }

    public String getOs() {
        return this.os;
    }

    public String getBrowser() {
        return this.browser;
    }

//    public String getBaseUrl() {
//        return this.baseUrl;
//    }

    public String getNode() {
        return this.node;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
    public Properties getProps() {
        return this.props;
    }
}