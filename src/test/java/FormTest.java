import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

class FormTest {

    private String validName = "Ivan",
            validSurname = "Ivanov",
            validEmail = "ivanov@test.com",
            gender = "Male",
            validPhone = "8911123112",
            yearOfBirth = "1992",
            monthOfBirth = "March",
            dayOfBirth = "10",
            address = "Street, 1",
            hobbie = "Reading",
            state = "NCR",
            city = "Delhi";

    private List<String> subjects = new ArrayList<>(Arrays.asList("Maths", "Biology"));

    @Test
    @DisplayName("Заполнение всех полей формы")
    void fillFormAllFieldsTest() {
        open("https://demoqa.com/automation-practice-form");

        /*
         Fill in form
         */

        $("#firstName").setValue(validName);
        $("#lastName").setValue(validSurname);
        $("#userEmail").setValue(validEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(validPhone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $$(".react-datepicker__day").find(text(dayOfBirth)).click();

        $("#subjectsInput").setValue(subjects.get(0).substring(0, 2));
        $(".subjects-auto-complete__menu").$(byText(subjects.get(0))).click();
        $("#subjectsInput").setValue(subjects.get(1).substring(0, 2));
        $(".subjects-auto-complete__menu").$(byText(subjects.get(1))).click();
        $("#hobbiesWrapper").$(byText(hobbie)).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));

        $("#currentAddress").setValue(address);
        $("#state").scrollIntoView(true).click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();

        $("#submit").click();

        /*
         Check fields
         */
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(
                text(String.format("%s %s", validName, validSurname)),
                text(validEmail),
                text(gender),
                text(validPhone),
                text(String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth)),
                text(String.format("%s, %s", subjects.get(0), subjects.get(1))),
                text(hobbie),
                text("picture.jpg"),
                text(address),
                text(String.format("%s %s", state, city))
        );
    }
}
