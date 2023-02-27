
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTokens {

    static String specialCharsPattern = "[!,?\\._'@]{1,}";
    private static Pattern PATTERN = Pattern.compile(specialCharsPattern);

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        scan.close();

        // Write your code here.
        List<String> orderedValues = new LinkedList<>();
        String[] splitted = s.split(" ");
        String normalPattern = "^[A-Za-z0-9]+$";

        for(int i=0; i< splitted.length; i++)
        {
                process(splitted[i], orderedValues);
        }

        System.out.println(orderedValues.size());
        for(String value : orderedValues)
        {
            System.out.println(value);
        }

    }
    private static void process(String input, List<String> orderedList)
    {
        if(input !=null && input.length() > 0) {
            Matcher matcher = PATTERN.matcher(input);
            //System.out.println("Special: " + input);
            if (matcher.find()) {
                String matched = matcher.group();
                int matchedIndex = input.indexOf(matched);
                String toadd = input.substring(0, matchedIndex);
                orderedList.add(toadd);
                String remaining = input.substring(matchedIndex + 1);
                process(remaining, orderedList);
            } else {
                orderedList.add(input);
            }
        }
    }
}

