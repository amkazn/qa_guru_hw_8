package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase{

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {

        textBoxPage
                .openPage("/text-box")
                .setUserName("Sherlock")
                .setUserEmail("s@holmes.com")
                .setCurrentAddress("Baker Street 221a")
                .setPermanentAddress("Baker Street 221b")
                .clickSumbit();

        textBoxPage
                .checkResults("Name", "Sherlock")
                .checkResults("Email", "s@holmes.com")
                .checkResults("Current Address ", "Baker Street 221a")
                .checkResults("Permananet Address ", "Baker Street 221b");
    }
}
