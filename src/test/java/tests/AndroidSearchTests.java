package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.OnboardingScreenPage;
import pages.WikipediaPage;
import static io.qameta.allure.Allure.step;

public class AndroidSearchTests extends TestBase {

    WikipediaPage wikipediaPage = new WikipediaPage();
    OnboardingScreenPage onboardingScreenPage = new OnboardingScreenPage();

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
//                .checkTextOnArticlePage("Microsoft");
    }

    @Test
    @Tag("android")
    @DisplayName("Прохождение онбординга")
    void gettingStartedTest() {
        step("Первая страница", () -> {
            onboardingScreenPage
                    .checkPageText("The Free Encyclopedia …in over 300 languages")
                    .checkFirstPageEditLanguageButton("ADD OR EDIT LANGUAGES")
                    .goToNextPage();
        });
        step("Вторая страница", () -> {
            onboardingScreenPage
                    .checkPageText("New ways to explore")
                    .goToNextPage();
        });
        step("Третья страница", () -> {
            onboardingScreenPage
                    .checkPageText("Reading lists with sync")
                    .goToNextPage();
        });
        step("Четвертая страница", () -> {
            onboardingScreenPage
                    .checkPageText("Send anonymous data")
//                    .checkRejectButton("Reject")
                    .checkAcceptButton("Accept")
                    .onboardingDone("GET STARTED");
        });
        step("Проверка экрана после онбординга", () -> {
            wikipediaPage
                    .checkWordMark();
        });
    }

}
