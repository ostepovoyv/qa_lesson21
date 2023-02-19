package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.IosPage;

@Epic("iOS")
@Feature("Search")
@Owner("ostepovoyv")
public class IosSearchTests extends TestBase {

    IosPage iosPage = new IosPage();

    @Test
    @Tag("ios")
    @DisplayName("Проверка поиска")
    void checkTextTest() {
        iosPage
                .clickTextButton()
                .checkOutputText("Waiting for text input.")
                .setValueInTextInput("Selenium")
                .checkOutputText("Selenium");
    }

}
