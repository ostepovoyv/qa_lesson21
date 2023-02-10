package pages;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class IosPage {

    private final SelenideElement
            textButton = $(AppiumBy.id("Text Button")),
            textInput = $(AppiumBy.id("Text Input")),
            textOutput = $(AppiumBy.id("Text Output"));

    @Step("Кликаем Text Button")
    public IosPage clickTextButton() {
        textButton.click();
        return this;
    }

    @Step("Проверяем состояние Output text")
    public IosPage checkOutputText(String value) {
        assertEquals(value, textOutput.getText());
        return this;
    }

    @Step("Вводим значение {value}")
    public IosPage setValueInTextInput(String value) {
        textInput.click();
        textInput.sendKeys(value);
        textInput.pressEnter();
        return this;
    }

}
