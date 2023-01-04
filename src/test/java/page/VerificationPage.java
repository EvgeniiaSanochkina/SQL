package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement error = $(By.className("notification__content"));
    private SelenideElement codeField = $("[data-test-id=\"code\"] input");
    private SelenideElement verifyButton = $("[data-test-id=\"action-verify\"]");

    public void errorNotification() {
        error.shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз."))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

   public void verifyVerificationPageVisibility () {
       codeField.shouldBe(visible);
   }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }

    public void verify (String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}
