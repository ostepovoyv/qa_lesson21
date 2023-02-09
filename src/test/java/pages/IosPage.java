package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;

public class IosPage {

    private final SelenideElement
            textButton = $(id("Text Button")),
            textInput = $(id("Text Input")),
            textOutput = $(id("Text Output"));

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
