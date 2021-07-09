package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Feature",dryRun=true,monochrome=true,glue="steps",plugin= {"pretty"},tags= {"@delmethod"})
public class RunnerClass {

}
