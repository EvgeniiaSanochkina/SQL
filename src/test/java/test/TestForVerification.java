package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import data.SQLHelper;
import org.openqa.selenium.By;
import page.DashboardPage;
import page.LoginPage;
import page.VerificationPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDataBase;


public class TestForVerification {
    @AfterAll
    static void tearDown() {
        cleanDataBase();
    }

    @Test
    void shouldSuccessfullyLoginToDashboardWithVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.validVerify(SQLHelper.getVerificationCode());
        var dashBoard = new DashboardPage();
    }

    @Test
    void shouldShowAnErrorIfLoginOrPasswordIsIncorrect() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        $("[data-test-id=\"login\"] input").setValue(authInfo.getLogin());
        $("[data-test-id=\"password\"] input").setValue(authInfo.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        $("[data-test-id=\"error-notification\"]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldShowAnErrorIfVerificationCodeIsIncorrect() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        $("[data-test-id=\"login\"] input").setValue(authInfo.getLogin());
        $("[data-test-id=\"password\"] input").setValue(authInfo.getPassword());
        $("[data-test-id=\"action-login\"]").click();
        var code = DataHelper.generateRandomVerificationCode();
        $("[data-test-id=\"code\"] input").setValue(code.getCode());
        $("[data-test-id=\"action-verify\"]").click();
        $("[data-test-id=\"error-notification\"]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}