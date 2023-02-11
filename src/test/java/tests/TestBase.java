package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.MobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

class TestBase {

    @BeforeAll
    static void beforeAll() {

        switch (System.getProperty("env")) {
            case "android":
            case "ios":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "local":
                Configuration.browser = MobileDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        closeWebDriver();
        if (!System.getProperty("env").equals("local")) Attach.addVideo(sessionId);
    }
}
