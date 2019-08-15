package com.EY.qa.framework;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        this.browser = System.getProperty("browser") != null ? System.getProperty("browser"):"chrome";
        this.os =  System.getProperty("os") != null ? System.getProperty("os"):"windows";
       // this.baseUrl = baseUrl;
        this.node = System.getProperty("node") != null ? System.getProperty("node"):"http://localhost:5555";
        this.env= System.getProperty("env") != null ? System.getProperty("env"):"dev";


        Platform platform = Platform.fromString(os.toUpperCase());
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), firefoxOptions);
        } else {
            InternetExplorerOptions ieOption = new InternetExplorerOptions();
            ieOption.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), ieOption);
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