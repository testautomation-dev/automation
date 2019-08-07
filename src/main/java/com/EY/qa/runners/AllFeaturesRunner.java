package com.EY.qa.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Aruna on 08/07/19.
 */
@CucumberOptions(features = "src/main/resources/features",
        glue = "com.EY.qa.steps",
        monochrome = true)
public class AllFeaturesRunner extends AbstractTestNGCucumberTests {
}
