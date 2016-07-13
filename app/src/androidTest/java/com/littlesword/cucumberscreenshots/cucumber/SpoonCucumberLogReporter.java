package com.littlesword.cucumberscreenshots.cucumber;

import android.util.Log;

import java.util.List;

import cucumber.runtime.Runtime;
import gherkin.formatter.Formatter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

/**
 * Created by kongw1 on 13/07/16.
 */
public class SpoonCucumberLogReporter implements Formatter {
    /**
     * The {@link cucumber.runtime.Runtime} to get the errors and snippets from for writing them to the logcat at the end of the execution.
     */
    private final Runtime runtime;

    /**
     * The log tag to be used when logging to logcat.
     */
    private final String logTag = "TestRunner";

    /**
     * Holds the feature's uri.
     */
    private String uri;
    private String featureName;

    /**
     * Creates a new instance for the given parameters.
     *
     * @param runtime the {@link cucumber.runtime.Runtime} to get the errors and snippets from
     */
    public SpoonCucumberLogReporter(final Runtime runtime) {
        this.runtime = runtime;
    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        Log.d(logTag, String.format("started: %s(%s)", scenario.getKeyword() + " " + scenario.getName(), featureName));
    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        Log.d(logTag, String.format("finished: %s(%s)", scenario.getKeyword() + " " + scenario.getName(), featureName));
    }


    @Override
    public void close() {

    }

    @Override
    public void eof() {

    }

    @Override
    public void uri(final String uri) {

    }

    @Override
    public void feature(final Feature feature) {
        featureName = feature.getKeyword() + " " + feature.getName();
    }

    @Override
    public void background(final Background background) {

    }

    @Override
    public void scenario(final Scenario scenario) {

    }

    @Override
    public void scenarioOutline(final ScenarioOutline scenarioOutline) {

    }

    @Override
    public void examples(final Examples examples) {

    }

    @Override
    public void step(final Step step) {

    }

    @Override
    public void syntaxError(final String state, final String event, final List<String> legalEvents, final String uri, final Integer line) {

    }

    @Override
    public void done() {

    }
}
