package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.WikipediaPage;

public class AndroidSearchTests extends TestBase {

    WikipediaPage wikipediaPage = new WikipediaPage();

    @Test
    @Tag("android")
    @DisplayName("Поиск страницы BrowserStack")
    void searchTest() {
        wikipediaPage
                .goBack()
                .searchArticle("BrowserStack")
                .checkSearchResult();
    }

    @Test
    @Tag("android")
    @DisplayName("Поиск страницы Microsoft, проверка описания")
    void checkDescriptionTest() {
        wikipediaPage
                .goBack()
                .searchArticle("Microsoft")
                .checkPageDescription("American multinational technology corporation");
    }


}
