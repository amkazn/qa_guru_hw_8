package utils;

import com.github.javafaker.Faker;
import tests.TestData;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {

    private static Faker faker = new Faker();

    public static String getRandomFirstName(){
        return faker.name().firstName();
    }

    public static String getRandomLastName(){
        return faker.name().lastName();
    }

    public static String getRandomEmail(){
        return faker.internet().emailAddress();
    }

    public static String[] getRandomSubjects(){
        return getRandomUniqueStrings(TestData.subjects);
    }

    public static String[] getRandomDate(){
        String[] months = "January February March April May June July August September October November December"
                .split("\s");

        Date date = faker.date().birthday();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String day = "00"+String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = months[cal.get(Calendar.MONTH)];
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String [] dataArr = {day.substring(day.length()-2, day.length()),
                month,
                year};

        return dataArr;
    }

    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getRandomGender() {
        return faker.options().option(TestData.genders);
    }

    public static String [] getRandomHobbies() {
        return getRandomUniqueStrings(TestData.hobbies);
    }

    public static String[] getRandomUniqueStrings(String [] items){

        Set<String> set = new HashSet<>();
        int randSize = getRandomInt(1, items.length);

        while (set.size() != randSize)
            set.add(faker.options().option(items));

        return set.toArray(new String[0]);
    }

    public static String [] getRandomStateAndCity() {
        List<String> states = new ArrayList<>();

        Pattern.compile("\"?\\w+\\s?\\w*\"?:\\s\\[")
                .matcher(TestData.countryAndCityCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        states.add(mr.substring(0, mr.length() - 1))
                );

        String state = faker.options().option(states.toArray(new String[0]));

        Matcher m = Pattern.compile(state+"\\[(\\{\\n[\\s\\w:\",]+\\},?\\s?)+")
                .matcher((TestData.countryAndCityCode));
        m.find();
        String stateCode = m.group();

        List<String> cities = new ArrayList<>();

        Pattern.compile("label:\s\"\\w+\"")
                .matcher(stateCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        cities.add(mr.substring(mr.indexOf("\"") + 1, mr.length() - 1))
                );

        state = state.replaceAll("(\"|(:\\s))","");
        String[] resultArr = {state, faker.options().option(cities.toArray(new String[0]))};

        return resultArr;
    };
}
