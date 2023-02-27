import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class SecretChamber {

        public static Map<Character, List<Character>> wordReplacementsMap = new HashMap<>();

        public static Map<String, String> wordsMap = new LinkedHashMap<>();

        public static boolean canReplace(Character source, Character target)
        {
            if(!wordReplacementsMap.containsKey(source))
            {
                return false;
            }
            List<Character> replacements = wordReplacementsMap.get(source);
            if(replacements.contains(target))
            {
                return true;
            }
            else
            {
                for(Character item: replacements)
                {
                    return canReplace(item, target);
                }
            }
            return replacements.contains(target);
        }

        public static void addToReplacementsMap(Character source, Character target)
        {
            if(!wordReplacementsMap.containsKey(source))
            {
                ArrayList<Character> characters = new ArrayList<>();
                wordReplacementsMap.put(source, characters);
            }

            List<Character> characters = wordReplacementsMap.get(source);

            characters.add(target);
        }

        public static boolean match(String source, String target)
        {
            if(source.length() != target.length())
                return false;
            if(source.equalsIgnoreCase(target))
                return true;

            for(int i = 0; i< source.length();i++)
            {
                if(source.charAt(i) == target.charAt(i))
                {
                    continue;
                }
                else if(canReplace(source.charAt(i), target.charAt(i)))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            return true;
        }

        /**
         * Iterate through each line of input.
         */
        public static void main(String[] args) throws IOException {
            InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(reader);
            String[] firstMultipleInput = in.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);
            String[] wordReplacement;
            for(int i=1; i<=n;i++)
            {
                wordReplacement = in.readLine().replaceAll("\\s+$", "").split(" ");
                addToReplacementsMap(wordReplacement[0].charAt(0), wordReplacement[1].charAt(0));

            }

            String[] words;
            for(int i=1; i<=m;i++)
            {
                words = in.readLine().replaceAll("\\s+$", "").split(" ");

                    wordsMap.put(words[0], words[1]);
            }

            for(Map.Entry<String,String> entry : wordsMap.entrySet())
            {
                boolean matchFound = match(entry.getKey(), entry.getValue());
                if(matchFound)
                {
                    System.out.println("yes");
                }
                else
                {
                    System.out.println("no");
                }
            }
        }




}
