import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

class GoogleTest {

    @BeforeEach
    void openPage() {
        open("https://Google.ru");
    }

    @Test
    void searchTest() {
        $(".input__control").setValue("Новосибирск").pressEnter();
        $$(".serp-item")
                .get(0).shouldHave(Condition.text("Новосибирск"));

    }


    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
