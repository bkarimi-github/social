import java.util.Scanner;
import java.util.*;

public class SmallestAndLargest {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'


        List<String> subStrings = new ArrayList<>();

        for(int i=0; i<s.length();i++)
        {
            if(i + k > s.length())
            {
                break;
            }
            subStrings.add(s.substring(i, i+k));
        }

        Collections.sort(subStrings, (s1, s2) -> {
            int length = s1.length() > s2.length() ? s2.length() : s1.length();
            for(int i=0; i<length;i++)
            {
                if(s1.charAt(i) == s2.charAt(i))
                {
                    continue;
                }
                if(s1.charAt(i) < s2.charAt(i))
                {
                    return -1;
                }
                else if(s1.charAt(i) > s2.charAt(i)) {
                    return 1;
                }
            }
            return 0;
        });
        smallest = subStrings.get(0);

        Collections.sort(subStrings, (s1, s2) -> {
            int length = s1.length() > s2.length() ? s2.length() : s1.length();
            for(int i=0; i<length;i++)
            {
                if(s1.charAt(i) == s2.charAt(i))
                {
                    continue;
                }
                if(s1.charAt(i) < s2.charAt(i))
                {
                    return 1;
                }
                else if(s1.charAt(i) > s2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        });
        largest = subStrings.get(0);

        return smallest + "\n" + largest;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }
}