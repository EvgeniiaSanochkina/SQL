package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));
    private DataHelper() {
    }

   @Value
   public static class AuthInfo {
     private String login;
      private String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateRandomLogin() {return faker.name().username();}

    public static String generateRandomPassword() { return faker.internet().password();}

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }
    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }
}
