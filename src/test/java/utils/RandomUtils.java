package utils;

import com.github.javafaker.Faker;
import tests.TestData;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {

    public static String getRandomString(int len){
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < len; i++)
            result.append(AB.charAt(rnd.nextInt(AB.length())));

        return result.toString();
    }

    public static String getRandomFirstName(){
        return new Faker().name().firstName();
    }

    public static String getRandomLastName(){
        return new Faker().name().lastName();
    }

    public static String getRandomEmail(){
        return new Faker().internet().emailAddress();
    }

    public static String[] getRandomSubjects(){

        List<String> lines = new ArrayList<>();

        Pattern.compile("label:\s\"\\w+\"")
                .matcher(TestData.subjectsCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        lines.add(mr.substring(mr.indexOf("\"") + 1, mr.length() - 1))
                );

        Set<String> subjects = new HashSet<>();
        int randSize = getRandomInt(1, lines.size());

        while (subjects.size() != randSize)
            subjects.add(lines.get(getRandomInt(0, lines.size()-1)));

        return getRandomUniqueStrings(lines);
    }

    public static String[] getRandomDate(){
        String[] months = "January February March April May June July August September October November December"
                .split("\s");


        Date date = new Faker().date().birthday();

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
        return new Faker().address().fullAddress();
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++)
            result.append(getRandomInt(0,9));
        return result.toString();
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        int index = getRandomInt(0, genders.length - 1);
        return genders[index];
    }

    public static String [] getRandomHobbies() {
        String[] hobbies = "Sports Reading Music"
                .split("\s");

        return getRandomUniqueStrings(Arrays.asList(hobbies));
    }

    public static String getSomething() {
        Faker faker = new Faker(new Locale("ru"));
        String firstName = faker.name().firstName();

        return firstName + 10;
    }

    public static String[] getRandomUniqueStrings(List list){
        Set<String> set = new HashSet<>();
        int randSize = getRandomInt(1, list.size());

        while (set.size() != randSize)
            set.add(list.get(getRandomInt(0, list.size()-1)).toString());

        return set.toArray(new String[0]);

    }

    public static String [] getRandomStateAndCity() {
        List<String> countries = new ArrayList<>();

        Pattern.compile("\"?\\w+\\s?\\w*\"?:\\s\\[")
                .matcher(TestData.countryAndCityCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        countries.add(mr.substring(0, mr.length() - 1))
                );

        String country = countries.get(getRandomInt(0, countries.size()-1));

        Matcher m = Pattern.compile(country+"\\[(\\{\\n[\\s\\w:\",]+\\},?\\s?)+")
                .matcher((TestData.countryAndCityCode));
        m.find();
        String countryCode = m.group();

        List<String> cities = new ArrayList<>();

        Pattern.compile("label:\s\"\\w+\"")
                .matcher(countryCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        cities.add(mr.substring(mr.indexOf("\"") + 1, mr.length() - 1))
                );

        country = country.replaceAll("(\"|(:\\s))","");
        String[] resultArr = {country, cities.get(getRandomInt(0, cities.size()-1))};

        return resultArr;
    };
}
