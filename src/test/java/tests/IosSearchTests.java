package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.IosPage;


public class IosSearchTests extends TestBase {

    IosPage iosPage = new IosPage();

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

}
