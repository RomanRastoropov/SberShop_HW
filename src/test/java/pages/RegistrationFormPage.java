package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormPage {
    public RegistrationFormPage openPage() {
        open("https://sbershop.ru");
        $("#link-auth").click();
        $(".gray-btn").click();
        $(".auth_registr").click();
        return this;
    }
    public RegistrationFormPage inputName(String name) {
        $("[name=\"REGISTER[NAME]\"]").setValue(name);
        return this;
    }
    public RegistrationFormPage inputEmail(String email) {
        $("[name=\"REGISTER[EMAIL]\"]").setValue(email);
        return this;
    }
    public RegistrationFormPage inputPasswordAndPressEnter(String password) {
        $("[name=\"REGISTER[PASSWORD]\"]").setValue(password).pressEnter();;
        return this;
    }
    public RegistrationFormPage checkValidator(String validator) {
        $(".authForm").shouldHave(text(validator));
        return this;
    }
    public RegistrationFormPage clickButtonRegistration() {
        $("input[name=register_submit_button]").click();
        return this;
    }
    public RegistrationFormPage clickButtonBack() {
        $(".go_back-3").click();
        return this;
    }
    public RegistrationFormPage checkLoginForm() {
        $(".modal__forms_detail").shouldHave(text("Войти"));
        return this;
    }
}
