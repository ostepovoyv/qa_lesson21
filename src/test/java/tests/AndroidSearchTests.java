package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.OnboardingScreenPage;
import pages.WikipediaPage;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.appium.SelenideAppium.back;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class AndroidSearchTests extends TestBase {

    WikipediaPage wikipediaPage = new WikipediaPage();
    OnboardingScreenPage onboardingScreenPage = new OnboardingScreenPage();

    @Disabled
    @Test
    @Tag("android")
    @DisplayName("Поиск страницы BrowserStack")
    void searchTest() {
        wikipediaPage
                .goBack()
                .searchArticle("BrowserStack")
                .checkSearchResult();
    }

    @Disabled
    @Test
    @Tag("android")
    @DisplayName("Поиск страницы Microsoft, проверка описания")
    void checkDescriptionTest() {
        wikipediaPage
                .goBack()
                .searchArticle("Microsoft")
                .checkPageDescription("American multinational technology corporation")
                .checkTextOnArticlePage("Microsoft");
    }

    @Disabled
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
                    .checkRejectButton("Reject")
                    .checkAcceptButton("Accept")
                    .onboardingDone("GET STARTED");
        });
        step("Проверка экрана после онбординга", () -> {
            wikipediaPage
                    .checkWordMark();
        });
    }

    @Test
    @DisplayName("Поиск по букве")
    void LetterSearch() {
        back();
        step("Поиск по букве А", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("A");
        });
        step("Проверка результата", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text("First letter of the Latin alphabet"));
        });
    }

    @Test
    @DisplayName("Скрытие карточки на главной странице")
    void hideThisCard() {
        back();
        step("Нажимаем на настрйоки статьи", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/view_list_card_header_menu")).click();
        });
        step("Выбираем скрыть в меню", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).shouldHave(exactText("Hide this card"));
            $(AppiumBy.id("org.wikipedia.alpha:id/title")).click();
        });

    }

    @Test
    @DisplayName("Check getting started (onboarding screen)")
    void checkOonboardingScreenTest() {
        step("Check 'The Free Encyclopedia …in over 300 languages' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'New ways to explore' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'Reading lists with sync' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
        });

        step("Click on 'Сontinue'", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());

        step("Check 'Send anonymous data' text is visible", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));
        });
    }



}
