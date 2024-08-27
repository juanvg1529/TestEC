package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/APITest","src/test/java/features/UITest"},
        glue = {"seleniumgluecode","seleniumgluecode.APIStepDefinition"},
        snippets = CAMELCASE
)


public class TestRunner {

}
