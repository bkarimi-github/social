import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentRegEx {
    static String CONTENT = ".*<(\\w{1,}|\\D{1,})>(?<content>.*?)</(?:\\1)>";
    static String DUPLICATE_TAG_REGEX = "(?<first>(<|</){1}(\\w*|\\D*)>)(?:\\1)+";
    static Pattern PATTERN_CONTENT = Pattern.compile(CONTENT);
    static Pattern PATTERN_TAG = Pattern.compile("(?<tag><(\\w*|\\D*)>|</(\\w*|\\D*)>)");
    static Pattern PATTERN_DUPLICATE_TAGS = Pattern.compile(DUPLICATE_TAG_REGEX);

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String line = in.nextLine();

            //Write your code here
            Matcher m = PATTERN_DUPLICATE_TAGS.matcher(line);

            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                line = line.replaceAll(m.group(0), m.group("first"));
            }

            //System.out.println("Tag " + line);
            //Write your code here
            System.out.println(getContent(line));

            testCases--;
        }
    }
    private static String getContent(String tag)
    {
        Matcher matcherTag = PATTERN_TAG.matcher(tag);
        if(matcherTag.find())
        {
            Matcher matcherContent = PATTERN_CONTENT.matcher(tag);
            if(matcherContent.find())
            {
                String content = matcherContent.group("content");
                if(content != null || content.length() > 0)
                {
                    return getContent(content);
                }
                else{
                    return "None";
                }
            }
            else
            {
                return "None";
            }
        }
        else
        {
            return (tag == null || tag.length() == 0) ? "None" : tag;
        }

    }
}
