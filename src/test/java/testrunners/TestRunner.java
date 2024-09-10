package testrunners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/APITest","src/test/java/features/UITest"},
        glue = {"stepdefinitions","seleniumgluecode.APIStepDefinition"},
        plugin = {"json:target/cucumber.json"},
        snippets = CAMELCASE,
        tags = "@Reggresion"
)


public class TestRunner {

}
