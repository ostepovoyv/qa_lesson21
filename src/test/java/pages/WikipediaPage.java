package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.id;

public class WikipediaPage {

    private final SelenideElement
            searchContainer = $(AppiumBy.id("org.wikipedia.alpha:id/search_container")),
            searchSrc = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")),
            description = $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")),
            mainToolbar = $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar")),
            cardHeaderMenu = $(AppiumBy.id("org.wikipedia.alpha:id/view_list_card_header_menu")),
            title = $(AppiumBy.id("org.wikipedia.alpha:id/title")),
            cardHeaderTitle = $(AppiumBy.id("org.wikipedia.alpha:id/view_card_header_title")),
            headerImage = $(AppiumBy.id("org.wikipedia.alpha:id/view_page_header_image"));

    private final ElementsCollection
            searchResult = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    String hiddenArticleTitle;

    @Step("Закрываем страницу настройки")
    public WikipediaPage goBack() {
        back();
        return this;
    }

    @Step("Вводим поисковый запрос: {value}")
    public WikipediaPage searchArticle(String value) {
        searchContainer.click();
        searchSrc.sendKeys(value);
        return this;
    }

    @Step("Проверяем поисковую выдачу")
    public WikipediaPage checkSearchResultSize() {
        searchResult.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверяем поисковый результат")
    public WikipediaPage checkSearchResult(String value) {
        description.shouldHave(text(value));
        return this;
    }

    @Step("Проверки на найденной странице")
    public WikipediaPage checkTextOnArticlePage() {
        description.click();
        headerImage.shouldHave(appear);
        return this;
    }

    @Step("Проверяем word mark")
    public WikipediaPage checkWordMark() {
        mainToolbar
                .find(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark"))
                .shouldHave(appear);
        return this;
    }

    @Step("Открываем настройки статьи")
    public WikipediaPage openArticleSettings(){
        cardHeaderMenu.click();
        return this;
    }

    @Step("Скрываем статью")
    public WikipediaPage selectHideInMenu(String value){
        title.shouldHave(exactText(value)).click();
        return this;
    }

    @Step("Получаем название статью, которую будем закрывать")
    public WikipediaPage getCurrentTitleArticle(){
        hiddenArticleTitle = cardHeaderTitle.getText();
        return this;
    }

    @Step("Проверяем, что выбранная статья скрыта")
    public WikipediaPage checkHiddenArticle(){
        cardHeaderTitle.shouldNot(text(hiddenArticleTitle));
        return this;
    }
}

