package com.littlesword.cucumberscreenshots.cucumber.entry;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "features", // Test scenarios
        glue = {"com.littlesword.cucumberscreenshots.steps"},
        tags = {
                "@sign_in"
        } // Use to run set of tests
)
class CucumberTestEntry {
    //nothing should be in this class!!!!!
}
