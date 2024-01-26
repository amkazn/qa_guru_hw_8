package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {

    private SelenideElement resultsTableLocator = $(".table-responsive");

    public void checkEntry (String key, String value) {
        resultsTableLocator
                .$(byText(key))
                .parent()
                .shouldHave(text(value));
    }
}
