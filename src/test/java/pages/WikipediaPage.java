package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.id;

public class WikipediaPage {

    private final SelenideElement
            search = $(accessibilityId("Search Wikipedia")),
            searchSrc = $(id("org.wikipedia.alpha:id/search_src_text")),
            description = $(id("org.wikipedia.alpha:id/page_list_item_description"));
    private final ElementsCollection
            searchResult = $$(id("org.wikipedia.alpha:id/page_list_item_title"));


    @Step("Закрываем страницу настройки")
    public WikipediaPage goBack() {
        back();
        return this;
    }

    @Step("Ищем статью: {value}")
    public WikipediaPage SearchArticle(String value) {
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
    public WikipediaPage checkPageDescription(String value){
        description.shouldHave(text(value));
        return this;
    }
}

