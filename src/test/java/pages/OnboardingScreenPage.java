package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.id;

public class OnboardingScreenPage {

    private final SelenideElement
            primaryTextView = $(id("org.wikipedia.alpha:id/primaryTextView")),
            addLangContainer = $(id("org.wikipedia.alpha:id/addLangContainer")),
            forwardButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            rejectButton = $(id("org.wikipedia.alpha:id/rejectButton")),
            acceptButton = $(id("org.wikipedia.alpha:id/acceptButton")),
            onboardingDoneButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

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

    @Step("Проверка наличия кнопки Accept")
    public OnboardingScreenPage checkAcceptButton(String value) {
        acceptButton.shouldHave(Condition.text(value));
        return this;
    }

    @Step("Проверка наличия кнопки Reject")
    public OnboardingScreenPage checkRejectButton(String value) {
        rejectButton.shouldHave(Condition.text(value));
        return this;
    }

    @Step("завершение онбоардинга")
    public OnboardingScreenPage onboardingDone(String value) {
        onboardingDoneButton.shouldHave(Condition.text(value)).click();
        return this;
    }


}
