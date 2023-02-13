package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class OnboardingScreenPage {

    private final SelenideElement
            primaryTextView = $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")),
            addLangContainer = $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")),
            forwardButton = $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            onboardingDoneButton = $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

    @Step("Проверка текста на странице")
    public OnboardingScreenPage checkPageText(String value) {
        primaryTextView.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Проверка наличия кнопки добавления/редактирования языков")
    public OnboardingScreenPage checkFirstPageEditLanguageButton(String value) {
        addLangContainer.find(AppiumBy.className("android.widget.TextView"))
                .shouldHave(Condition.text(value));
        return this;
    }

    @Step("Нажимаем на 'Сontinue'. Переход к следующей странице")
    public OnboardingScreenPage goToNextPage() {
        forwardButton.click();
        return this;
    }

    @Step("завершение онбоардинга")
    public OnboardingScreenPage onboardingDone(String value) {
        onboardingDoneButton.shouldHave(Condition.text(value)).click();
        return this;
    }


}
