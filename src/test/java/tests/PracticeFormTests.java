package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static utils.RandomUtils.*;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String userEmail = getRandomEmail();
    String mobile = getRandomPhone();
    String gender = getRandomGender();
    String address = getRandomAddress();
    String pictureName = TestData.pictureName;
    String url = TestData.url;
    String [] subjects = getRandomSubjects();
    String [] dateArr = getRandomDate();
    String [] hobbies = getRandomHobbies();
    String [] stateAndCity = getRandomStateAndCity();

    @Test
    void successfulRegistrationFullTest() {

        //fill in the form
        registrationPage
                .openPage(url)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEMail(userEmail)
                .setGender(gender)
                .setUserNumber(mobile)
                .setBirthDate(dateArr[0], dateArr[1], dateArr[2])
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .uploadPicture(pictureName)
                .setCurrentAddress(address)
                .setStateAndCity(stateAndCity[0], stateAndCity[1])
                .clickSubmit();

        //check results
        registrationPage
                .checkDialogAppears()
                .checkResults("Student Name", "%s %s".formatted(firstName, lastName))
                .checkResults("Student Email", userEmail)
                .checkResults("Gender", gender)
                .checkResults("Mobile", mobile)
                .checkResults("Date of Birth", "%s %s,%s".formatted(dateArr[0], dateArr[1], dateArr[2]))
                .checkResults("Subjects", String.join(", ", subjects))
                .checkResults("Hobbies", String.join(", ", hobbies))
                .checkResults("Picture", pictureName)
                .checkResults("Address", address)
                .checkResults("State and City", "%s %s".formatted(stateAndCity[0], stateAndCity[1]));
    }

    @Test
    void successfulRegistrationMinTest() {

        registrationPage
                .openPage(url)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(mobile)
                .clickSubmit();

        registrationPage
                .checkDialogAppears()
                .checkResults("Student Name", "%s %s".formatted(firstName, lastName))
                .checkResults("Gender", gender)
                .checkResults("Mobile", mobile);
    }

    @Test
    void negativeRegistrationTest() {

        registrationPage
                .openPage(url)
                .clickSubmit();

        registrationPage
                .checkDialogNotAppears();
    }
}
