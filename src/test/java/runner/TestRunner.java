package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/UItest","src/test/java/features/APITest"},
        glue = {"seleniumgluecode"}
)


public class TestRunner {

}
