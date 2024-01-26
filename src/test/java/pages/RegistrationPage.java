package pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import java.util.Arrays;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private ResultsTableComponent resultsTable = new ResultsTableComponent();
    private CalendarComponent calendarComponent = new CalendarComponent();
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            eMailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userSubjectsInput = $("#subjectsInput"),
            userHobbiesInput = $("#hobbiesWrapper"),
            calendarInput = $("#dateOfBirthInput"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress-wrapper").$("#currentAddress"),
            stateCityWrapper = $("#stateCity-wrapper"),
            submitButton = $("#submit"),
            dialogWindow = $(".modal-dialog");


    public RegistrationPage openPage(String relativeOrAbsoluteUrl) {
        open(relativeOrAbsoluteUrl);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String firstName){
        firstNameInput.setValue(firstName);

        return this;
    }

    public RegistrationPage setLastName(String lastName){
        lastNameInput.setValue(lastName);

        return this;
    }

    public RegistrationPage setEMail(String eMail){
        eMailInput.setValue(eMail);

        return this;
    }

    public RegistrationPage setGender(String gender){
        genderWrapper.$(byText(gender)).click();

        return this;
    }
    public RegistrationPage setUserNumber(String userNumber){
        userNumberInput.setValue(userNumber);

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year){
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String... args){
        Arrays.asList(args)
                .forEach(arg -> userSubjectsInput.setValue(arg).pressEnter());

        return this;
    }

    public RegistrationPage setHobbies(String... args){
        Arrays.asList(args)
                .forEach(arg -> userHobbiesInput.$(byText(arg))
                        .click());

        return this;
    }

    public RegistrationPage uploadPicture(String pictureName){
        uploadPictureInput.uploadFromClasspath(pictureName);

        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress){
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city){
        stateCityWrapper
                .$("#state")
                .click();
        stateCityWrapper
                .$("#state")
                .$(byText(state))
                .click();

        stateCityWrapper
                .$("#city")
                .click();
        stateCityWrapper
                .$("#city")
                .$(byText(city))
                .click();

        return this;
    }

    public RegistrationPage clickSubmit() {
        submitButton.click();

        return this;
    }

    public RegistrationPage checkResults(String key, String value){
        resultsTable.checkEntry(key,value);

        return this;
    }

    public RegistrationPage checkDialogAppears(){
        dialogWindow.should(appear);
        return this;
    }

    public RegistrationPage checkDialogNotAppears(){
        dialogWindow.shouldNot(appear);
        return this;
    }
}
