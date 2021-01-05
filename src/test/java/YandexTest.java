import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

class YandexTest {

    @BeforeEach
    void openPage() {
        open("https://google.ru");
    }

    @Test
    void googleSearchString () {
        //input text for search
        $(byName("q")).val("Debit card").pressEnter();
        //assert text
        $("html").shouldHave(text("Debit card"));
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
