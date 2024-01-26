package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement yearSelector = $(".react-datepicker__year-select"),
            monthSelector = $(".react-datepicker__month-select");

    private SelenideElement daySelector(String day){
        return $(String.format("[class*='day--0%s']:not([class*='outside-month'])", day));
    }

    public void setDate(String day, String month, String year){
        yearSelector.selectOption(year);
        monthSelector.selectOption(month);
        daySelector(day).click();
    }
}
