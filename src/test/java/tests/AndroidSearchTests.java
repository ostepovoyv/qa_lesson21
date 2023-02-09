package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.WikipediaPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.id;


public class AndroidSearchTests extends TestBase {

    WikipediaPage wikipediaPage = new WikipediaPage();

    @Test
    @Tag("android")
    @DisplayName("Поиск страницы BrowserStack")
    void searchTest() {
        wikipediaPage
                .goBack()
                .SearchArticle("BrowserStack")
                .checkSearchResult();
    }

    @Test
    @Tag("android")
    @DisplayName("Поиск страницы Microsoft, проверка описания")
    void checkDescriptionTest() {
        wikipediaPage
                .goBack()
                .SearchArticle("Microsoft")
                .checkPageDescription("American multinational technology corporation");
    }


}
