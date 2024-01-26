import org.apache.commons.exec.util.StringUtils;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    static String subjectsCode = "var Uu = [{\n" +
            "        value: 1,\n" +
            "        label: \"Hindi\"\n" +
            "    }, {\n" +
            "        value: 2,\n" +
            "        label: \"English\"\n" +
            "    }, {\n" +
            "        value: 3,\n" +
            "        label: \"Maths\"\n" +
            "    }, {\n" +
            "        value: 4,\n" +
            "        label: \"Physics\"\n" +
            "    }, {\n" +
            "        value: 5,\n" +
            "        label: \"Chemistry\"\n" +
            "    }, {\n" +
            "        value: 6,\n" +
            "        label: \"Biology\"\n" +
            "    }, {\n" +
            "        value: 7,\n" +
            "        label: \"Computer Science\"\n" +
            "    }, {\n" +
            "        value: 8,\n" +
            "        label: \"Commerce\"\n" +
            "    }, {\n" +
            "        value: 9,\n" +
            "        label: \"Accounting\"\n" +
            "    }, {\n" +
            "        value: 10,\n" +
            "        label: \"Economics\"\n" +
            "    }, {\n" +
            "        value: 11,\n" +
            "        label: \"Arts\"\n" +
            "    }, {\n" +
            "        value: 12,\n" +
            "        label: \"Social Studies\"\n" +
            "    }, {\n" +
            "        value: 13,\n" +
            "        label: \"History\"\n" +
            "    }, {\n" +
            "        value: 14,\n" +
            "        label: \"Civics\"\n" +
            "    }]";

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();



        Pattern.compile("label:\s\"\\w+\"")
                .matcher(subjectsCode)
                .results()
                .map(mr -> mr.group())
                .forEach(mr ->
                        lines.add(mr.substring(mr.indexOf("\"") + 1, mr.length() - 1))
                );

        SecureRandom rnd = new SecureRandom();
        Set<String> subjects = new HashSet<>();
        int randSize = rnd.nextInt(1, lines.size());
        while (subjects.size() != randSize){
            subjects.add(lines.get(rnd.nextInt(lines.size()-1)));
        }

        Arrays.toString(subjects.toArray(new String[0]));


    }


}
