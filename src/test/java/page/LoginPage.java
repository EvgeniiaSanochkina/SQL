package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    //private SelenideElement error = $("[data-test-id=\"error-notification\"]");
    private SelenideElement error = $(By.className("notification__content"));
    public void errorNotification() {
        error.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        return new VerificationPage();
    }
}





