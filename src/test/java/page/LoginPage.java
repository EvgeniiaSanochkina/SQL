package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        return new VerificationPage();
    }
}





