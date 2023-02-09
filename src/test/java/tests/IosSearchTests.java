package tests;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.IosPage;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;

public class IosSearchTests extends TestBase {

    IosPage iosPage = new IosPage();

    @Disabled
    @Test
    @Tag("ios")
    @DisplayName("Проверка поиска")
    void checkOutputTextTest1() {
        iosPage
                .clickTextButton()
                .checkOutputText("Waiting for text input.")
                .setValueInTextInput("Selenium")
                .checkOutputText("Selenium");
    }

    @DisplayName("Проверка функции поиска (ios)")
    @Test
    @Tag("ios")
    void checkOutputTextTest() {
        step("Click Text Button", () -> {
            $(id("Text Button")).click();
        });

        step("Check initial state Output text", () -> {
            assertEquals("Waiting for text input.", $(id("Text Output")).getText());
        });

        step(format("Set value %s in the input field and press enter", "Selenium"), () -> {
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys("Selenium");
            $(id("Text Input")).pressEnter();
        });

        step("Check Output text", () -> {
            assertEquals("Selenium", $(id("Text Output")).getText());
        });
    }
}
