package shopsber;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import helpers.Attach;
import org.junit.jupiter.api.*;
import pages.RegistrationFormPage;

import java.util.Locale;

import static utils.RandomUtils.incorrectRandomEmail;
import static utils.RandomUtils.shortRandomPassword;
@Tag("REGRESS")
@DisplayName("Проверка формы регистрации интернет магазина 'SberShop'")
public class RegistrationFormTest extends TestBase {
    RegistrationFormPage registrationPage = new RegistrationFormPage();
    Faker faker = new Faker(new Locale("en"));
    String validName = faker.name().fullName();
    String validEmail = faker.internet().emailAddress();
    String validPassword = faker.internet().password();
    String incorrectEmail = incorrectRandomEmail();
    String incorrectPassword = shortRandomPassword();
    @BeforeEach
    void beforeEach() {
        registrationPage.openPage();
    }
    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
    @Test
    @DisplayName("Ввод валидных данных в форму регистрации")
    void positiveTest() {
        registrationPage.inputName(validName)
                .inputEmail(validEmail)
                .inputPasswordAndPressEnter(validPassword)
                .checkValidator("На указанный в форме e-mail было выслано письмо с информацией о подтверждении регистрации.");
    }
    @Test
    @DisplayName("Оставить все поля пустыми")
    void emptyRegistrationFormTest() {
        registrationPage.clickButtonRegistration()
                .checkValidator("Поле \"Email\" обязательно для заполнения")
                .checkValidator("Поле \"Пароль\" обязательно для заполнения")
                .checkValidator("Поле \"Имя\" обязательно для заполнения");
    }
    @Test
    @DisplayName("Ввод некорректного email")
    void incorrectEmailTest() {
        registrationPage.inputName(validName)
                .inputEmail(incorrectEmail)
                .inputPasswordAndPressEnter(validPassword)
                .checkValidator("Неверный email.");
    }
    @Test
    @DisplayName("Ввод длинной менее 6 символов")
    void shortPasswordTest() {
        registrationPage.inputName(validName)
                .inputEmail(validEmail)
                .inputPasswordAndPressEnter(incorrectPassword)
                .checkValidator("Пароль должен  быть не менее 6 символов длиной.");
    }
    @Test
    @DisplayName("Проверка работы кнопки 'Назад'")
    void backButtonTest() {
        registrationPage.clickButtonBack()
                .checkLoginForm();
    }
}
