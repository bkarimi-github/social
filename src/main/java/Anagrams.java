import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Anagrams {

    static boolean isAnagram(String a, String b) {

        if(a.length() != b.length())
        {
            return false;
        }

        a = a.toLowerCase();
        b = b.toLowerCase();
        char[] processedChars = new char[]{};
        for(int i = 0; i< a.length(); i++)
        {
            if(!isCharAnagram(a.charAt(i), a, b, processedChars))
            {
                return false;
            }
        }
        return true;

    }

    static boolean isCharAnagram(Character c, String a, String b, char[] processedChars)
    {
        if(processedChars.length > 0)
        {
            for(int i = 0; i < processedChars.length; i++)
            {
                if(c == processedChars[i])
                {
                    return true;
                }
            }
        }

        int i = 0;
        int acount = 0, bcount = 0;
        while(i < a.length())
        {
            int index = a.indexOf(c, i);
            if(index != -1)
            {
                ++acount;
                i = index + 1;
            }
            else if(index == -1)
            {
                break;
            }
        }

        i = 0;
        while(i < b.length())
        {
            int index = b.indexOf(c, i);
            if(index != -1)
            {
                ++bcount;
                i = index + 1;
            }
            else if(index == -1)
            {
                break;
            }
        }

        return acount == bcount;

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );

        String str = "I am burhan's father who's responsible, for everything I do?";
        String[] splitted = str.split(" '?");
        char[] charsequence = new char[]{'!',',','?','.','_','@'};
        Pattern pattern = Pattern.compile("\\D+");

        System.out.println(pattern.matcher("burhan").matches());
        System.out.println(pattern.matcher("burhan!()09").matches());
        for(int i = 0; i < splitted.length; i++)
        {
            System.out.println(splitted[i]);
        }
    }
}