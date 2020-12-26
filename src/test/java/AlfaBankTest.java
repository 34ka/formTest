
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

class AlfaBankTest {

    private ElementsCollection archivedDepositsCards = $$("[data-widget-name='CatalogCard']");
    private SelenideElement
            archivedDepositsButton = $$("[data-test-id=button]").find(text("Архивные счета и депозиты")),
            depositsTab = $("[data-test-id=tabs-list-tabTitle-1]");

    @BeforeEach
    void openPage() {
        open("https://alfabank.ru/make-money/");
    }

    @Test
    @DisplayName("Во вкладке 'Архивные счета и депохиты' представлено 5 архивных депозитов")
    void checkArchivedDepositsCount() {
        archivedDepositsButton.click();
        depositsTab.click();
        archivedDepositsCards.shouldHave(size(5));
    }

    @Test
    @DisplayName("Using sibling")
    void openDepositInsuranceInfoSiblingAndClosest() {
        $(byText("Описание")).closest("button").sibling(0).click();
        checkAccordionItems();
    }


    @Test
    @DisplayName("Using parent and preceding")
    void openDepositInsuranceInfoParentAndPreceding() {
        $(byText("Что такое вклад?")).parent().preceding(0).click();
        checkAccordionItems();
    }

    private void checkAccordionItems() {
        $$("[data-test-id^='accordion-item-']").shouldHave(texts(
                "Альфа-Банк является участником системы обязательного страхования вкладов",
                "Федеральный закон от 23.12.2003 N 177-ФЗ " +
                        "«О страховании вкладов физических лиц в банках Российской Федерации»",
                "Страхованию подлежат",
                "Как происходит возмещение средств?"
        ));
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
