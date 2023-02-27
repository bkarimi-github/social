import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static void main(String[] args) {
        final String regex = "^(?<mask>\\d{4}-\\d{4}-\\d{4})(?<last>-\\d{4})";
        final String string = "4444-4444-4444-4444";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(1));
            String masked = matcher.group("mask").replaceAll("\\d", "X");
            System.out.println("Masked: " + masked+matcher.group("last"));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }
}
