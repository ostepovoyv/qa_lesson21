package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static config.ProjectConfig.PROPS;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class MobileDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        try {
            return new URL(PROPS.getLocalDriverUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(PROPS.getPlatformName())
                .setDeviceName(PROPS.getDevice())
                .setPlatformVersion(PROPS.getOsVersion())
                .setApp(getAppPath())
                .setAppPackage(PROPS.getAppPackage())
                .setAppActivity(PROPS.getAppActivity());
        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private String getAppPath() {
        String appUrl = PROPS.getAppUrl();
        String appPath = PROPS.getAppPath();
        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}
