package com.littlesword.cucumberscreenshots;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.jraska.falcon.FalconSpoon;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsAnything;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;


/**
 * Created by kongw1 on 6/07/16.
 */
public class SpoonCucumberScreenshotAction implements ViewAction {
    private final String tag;
    private final String scenarioName;
    private final String featureName;

    public SpoonCucumberScreenshotAction(String tag, String featureName, String scenarioName) {
        this.tag = tag;
        this.featureName = featureName;
        this.scenarioName = scenarioName;
    }

    @Override
    public Matcher<View> getConstraints() {
        return new IsAnything<>();
    }

    @Override
    public String getDescription() {
        return "Taking a screenshot using spoon.";
    }

    @Override
    public void perform(UiController uiController, View view) {
        FalconSpoon.screenshot(getActivity(view), tag, featureName, scenarioName);
    }

    private static Activity getActivity(View view) {
        Context context = view.getContext();
        while (!(context instanceof Activity)) {
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                throw new IllegalStateException("Got a context of class "
                        + context.getClass()
                        + " and I don't know how to get the Activity from it");
            }
        }
        return (Activity) context;
    }

    /** This must be called directly from your test method. */
    public static void perform(String tag, String featureName, String scenarioName) {
        onView(isRoot()).perform(new SpoonCucumberScreenshotAction(tag, featureName, scenarioName));
    }

}
