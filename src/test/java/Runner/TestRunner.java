package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/Features", // or features/add_user
        glue = {"StepDefinition", "hooks"},
        plugin = {
            "pretty",
            "html:target/cucumber-reports/cucumber.html",
            "json:target/cucumber-reports/cucumber.json",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)  // Optional: for parallel scenario execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
