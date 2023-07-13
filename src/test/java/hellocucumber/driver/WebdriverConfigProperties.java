package hellocucumber.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebdriverConfigProperties {
    private String url;
    private String seleniumHubUrl;
    private String browser;
    private boolean remote;
    private boolean headless;
    private String fileDownloadPath;
    private int waitTime;
}
