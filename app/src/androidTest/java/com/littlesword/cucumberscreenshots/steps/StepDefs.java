package com.littlesword.cucumberscreenshots.steps;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.littlesword.cucumberscreenshots.LoginActivity;
import com.littlesword.cucumberscreenshots.R;
import com.littlesword.cucumberscreenshots.SpoonCucumberScreenshotAction;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kongw1 on 11/07/16.
 */
public class StepDefs extends ActivityInstrumentationTestCase2<LoginActivity> {

    public StepDefs(){
        super(LoginActivity.class);
    }

    @Given("^I am on login screen$")
    public void iAmOnLoginScreen() throws Throwable {
        assertNotNull(getActivity());
    }

    @And("^I enter email using \"([^\"]*)\"$")
    public void iEnterEmailUsing(String email) throws Throwable {
        onView(withId(R.id.email)).perform(typeText(email));
    }

    @And("^I enter password using \"([^\"]*)\"$")
    public void iEnterPasswordUsing(String password) throws Throwable {
        onView(withId(R.id.password)).perform(typeText(password));
    }

    @When("^I press the sign in button$")
    public void iPressTheSingInButton() throws Throwable {
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }

    @Then("^I will see the error text \"([^\"]*)\"$")
    public void iWillSeeTheErrorText(String error) throws Throwable {
        onView(withId(R.id.email)).check(matches(hasErrorText(error)));

    }

    @And("^take screenshot \"([^\"]*)\" in feature file \"([^\"]*)\" with scenario \"([^\"]*)\"$")
    public void takeScreenshotInFeatureFileWithScenario(String tag, String featureName, String scenarioName) throws Throwable {
        //populate string to match Spoon class and method naming requirement
        featureName = "Feature " + featureName;
        scenarioName = scenarioName.replace(":","");//remove :
        tag = tag.replaceAll("[\"!@#$%-+^&*(),.;'?~`\n]", "_").replaceAll(" ", "_");//remove special chars in tag value
        SpoonCucumberScreenshotAction.perform(tag, featureName, scenarioName);
    }

    @Then("^I will see the text \"([^\"]*)\"$")
    public void iWillSeeTheText(String text) throws Throwable {
        onView(withText(text)).check(matches(isDisplayed()));

    }
}
