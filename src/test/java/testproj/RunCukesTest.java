package testproj;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features = {"src/test/java/testproj/"},
        //glue = "test.StepDefinitions"
        //plugin = { "pretty", "html:target/html-reports" },
        //publish = true
        //strict = true
)
public class RunCukesTest {
}
