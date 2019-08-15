package com.EY.qa.runners;

import com.EY.qa.framework.SetupTestDriver;
import com.EY.qa.framework.WebApp;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.EY.qa.framework.SetupTestDriver;

import java.net.MalformedURLException;

/**
 * Created by Aruna on 08/07/19.
 */
@CucumberOptions(features = "src/main/resources/features",
        plugin = {"pretty" ,
                "json:Folder_Name/cucumber.json"},
        glue = "com.EY.qa.steps",
        tags ={"@login"},
        monochrome = true)


public class AllFeaturesRunner extends AbstractTestNGCucumberTests {
    //public static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws MalformedURLException {

        SetupTestDriver setupTestDriver = new SetupTestDriver();
        WebApp.driver = setupTestDriver.getDriver();

    }


}
