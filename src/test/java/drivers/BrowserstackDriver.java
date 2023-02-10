package drivers;

import com.codeborne.selenide.WebDriverProvider;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static config.ProjectConfig.PROPS;

public class BrowserstackDriver implements WebDriverProvider {

    @SneakyThrows
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", PROPS.getLogin());
        mutableCapabilities.setCapability("browserstack.key", PROPS.getPassword());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", PROPS.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", PROPS.getDevice());
        mutableCapabilities.setCapability("os_version", PROPS.getOsVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", PROPS.getProjectName());
        mutableCapabilities.setCapability("build", PROPS.getBuildName());
        mutableCapabilities.setCapability("name", PROPS.getTestName());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(PROPS.getDriverUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
