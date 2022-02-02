package testproj;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/TesteAuto"},
        //glue = "test.StepDefinitions"
        plugin = { "pretty", "html:local_test_report/html-reports.html" }
        //publish = true
        //strict = true
)
public class RunCukesTest {
}
