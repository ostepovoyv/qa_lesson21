package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static org.openqa.selenium.By.id;

public class WikipediaPage {

    private final SelenideElement
            search = $(accessibilityId("Search Wikipedia")),
            searchSrc = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")),
            description = $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")),
            mainToolbar = $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar")),
            headerImage = $(AppiumBy.id("org.wikipedia.alpha:id/view_page_header_image")),
            pageContent = $(AppiumBy.id("org.wikipedia.alpha:id/page_contents_container"));

    private final ElementsCollection
            searchResult = $$(id("org.wikipedia.alpha:id/page_list_item_title"));


    @Step("Закрываем страницу настройки")
    public WikipediaPage goBack() {
        back();
        return this;
    }

    @Step("Ищем статью: {value}")
    public WikipediaPage searchArticle(String value) {
        search.click();
        searchSrc.sendKeys(value);
        return this;
    }

    @Step("Проверяем поисковую выдачу")
    public WikipediaPage checkSearchResult() {
        searchResult.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверяем найденные заголовки")
    public WikipediaPage checkPageDescription(String value) {
        description.shouldHave(text(value));
        return this;
    }

    @Step("Проверки на найденной странице")
    public WikipediaPage checkTextOnArticlePage(String value) {
        description.click();
        headerImage.shouldHave(appear);
//        pageContent.find(className("android.view.View"))
//                .shouldHave(text(value));
        return this;
    }

    @Step("Проверяем word mark")
    public WikipediaPage checkWordMark() {
        mainToolbar
                .find(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark"))
                .shouldHave(appear);
        return this;
    }
}

