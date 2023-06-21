package hellocucumber;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("hellocucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/test-reports/cucumber-reports/html-reports/cucumber.html")
@IncludeTags("test")
public class RunCucumberTest {
}
