package com.littlesword.cucumberscreenshots.cucumber;

import android.os.Bundle;
import android.support.test.runner.MonitoringInstrumentation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cucumber.api.android.CucumberInstrumentationCore;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberTagStatement;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;
import gherkin.formatter.model.TagStatement;

/**
 * This instrumentation will inject a screenshot step at runtime whenever there is a THEN statement
 * under scenario that is tagged by @screenshot
 */
public class MockStepInstrumentation extends MonitoringInstrumentation {
    private final CucumberInstrumentationCore mInstrumentationCore = new CucumberInstrumentationCore(this);
    private final String SCREENSHOT = "@screenshot";
    private final String THEN = "Then ";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mInstrumentationCore.create(bundle);
        try {
            Object cucumberExecutor = getFieldValue("cucumberExecutor", mInstrumentationCore);
            Object cucumberFeatures = getFieldValue("cucumberFeatures", cucumberExecutor);

            for(CucumberFeature cf : (List<CucumberFeature>)cucumberFeatures){
                Object cucumberTagStatements = getFieldValue("cucumberTagStatements", cf);
                for(CucumberTagStatement t : (List<CucumberTagStatement>)cucumberTagStatements){
                    //TODO bypass commented scenario
                    TagStatement so = t.getGherkinModel();
                    for(Tag tag : so.getTags()){
                        if(tag.getName().compareToIgnoreCase(SCREENSHOT) == 0){
                            ArrayList<Integer> positionToInsert = new ArrayList<>();
                            List<Step> steps = t.getSteps();
                            for(int i =0; i< steps.size(); i++){
                                if(steps.get(i).getKeyword().compareToIgnoreCase(THEN) == 0){
                                    positionToInsert.add(i);
                                }
                            }
                            for(int i = 0 ;i < positionToInsert.size() ; i++){
                                t.getSteps().add(positionToInsert.get(i) + i + 1
                                        , createScreenShotStep(steps.get(positionToInsert.get(i) + i).getName()
                                                , cf.getGherkinFeature().getName(), t.getVisualName()));
                            }
                        }
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        start();
    }

    private Step createScreenShotStep(String tag, String featureName, String scenarioName) {
        //create mock screenshot step defs
        //refer to HelpStepDef#takescreenshot()
        tag = tag.replaceAll("\"", "").replaceAll(" ", "_").replaceAll("-","_");//using previous Then statement as image tag. remove space and ' " '
        return new Step(null, "And ", "take screenshot \""+tag+"\" in feature file \""+featureName+"\" with scenario \""+scenarioName+"\"", -1, null, null);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        mInstrumentationCore.start();
    }

    private static Object getFieldValue(String fieldName, Object target) throws NoSuchFieldException, IllegalAccessException {
        Field field = findField(fieldName, target.getClass());
        field.setAccessible(true);
        return field.get(target);
    }

    private static Field findField(String name, Class clazz) throws NoSuchFieldException {
        for(Class currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                if (name.equals(field.getName())) {
                    return field;
                }
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found for class " + clazz);
    }

}
