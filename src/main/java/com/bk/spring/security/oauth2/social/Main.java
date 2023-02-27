package com.bk.spring.security.oauth2.social;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        /*
        List<String> list = Arrays.asList("A0", "C0", "D0", "AB0");
        Consumer<String> printer = (s) -> System.out.println(s);
        Set<String> set = list.stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toSet());
        set.stream().forEach(str -> System.out.println(str));

        double[] doubleArray = new double[]{1.1,2.2,3.3,4.4,5.5,6.6};
        squareArray(doubleArray);

        listFunction();

        countNoOfWordsInString(printer);

        averageOfNumbers(printer);

        stats(printer);

        countLengthOfStrings(printer);

        */
        //rotateArray(new int[]{1,2,3,4,5,6,7,8,9,10}, 898);

        System.out.println(100+100+ " Burhan");
        System.out.println(100+100+ " Burhan" + 100 + 100);
        sortWordsByLength();


        List<Integer> list = new ArrayList<Integer>();

        Map<Integer, Integer> imap = new HashMap<Integer, Integer>(){{
            put(1,2);
            put(3,4);
            put(5,6);
            put(7,8);

        }};
        System.out.println(imap.getClass().getName());
        imap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        Map<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        inmap.put(10,20);
        inmap.put(30,40);
        inmap.put(50,60);
        inmap.put(70,80);
        System.out.println(inmap.getClass().getName());
        inmap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));


        int[] arr = {1, 2, 4, 5, 3, 7, 6,111111111};
        Arrays.sort(arr);

        for(int i=0; i<8; i++)
        {
            if(arr[i] != i+1)
            {
                System.out.println("Missing " + (i+1) + " in array");
                break;
            }
        }


    }

    public static void stats(Consumer<String> printer)
    {
        double[] numbers = new double[]{5.5, 6.5, 7.5, 8.5,9.5,10.5,11.5,12.5,13.5};
        DoubleSummaryStatistics stats = Arrays.stream(numbers).summaryStatistics();
        printer.accept("Count: " + stats.getCount());
        printer.accept("Sum: " + stats.getSum());
        printer.accept("Min: " + stats.getMin());
        printer.accept("Max: " + stats.getMax());
        printer.accept("Average: " + stats.getAverage());
        printer.accept(stats.toString());
    }

    public static void averageOfNumbers(Consumer<String> printer)
    {
        double[] numbers = new double[]{5.5, 6.5, 7.5, 8.5,9.5,10.5,11.5,12.5,13.5};
        printer.accept("Average of Numbers: " + Arrays.stream(numbers).average().getAsDouble());
    }

    public static void sortWordsByLength()
    {
        String[] array = new String[]{"Burhan", "Valikarimwala", "Banuben", "Jalalbhaiwala", "Alifiya", "Hussain"};

        Map<String, Integer> map = Arrays.stream(array).collect(Collectors.toMap(Function.identity(), e -> e.length()));
        map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

        //.entrySet().stream().sorted(Map.Entry.comparingByKey((o1, o2) -> o1.compareTo(o1))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).entrySet().forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }

    public static void countLengthOfStrings(Consumer<String> printer)
    {
        String[] array = new String[]{"Burhan", "Valikarimwala", "Banuben", "Jalalbhaiwala", "Alifiya", "Hussain"};

        printer.accept("List of words vs length of each:");
        Arrays.stream(array).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> e.length()))).entrySet().stream().forEach(e -> printer.accept("Key: " + e.getKey() + " Value: "+ e.getValue()));

        Arrays.stream(array).collect(Collectors.toMap(Function.identity(), (value) -> new StringBuffer(value).reverse())).entrySet().forEach(entry -> System.out.println("Value: " + entry.getKey() + " Reverse: " + entry.getValue() ));


    }

    public static void rotateArray(int[] intArray, int rotation)
    {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int value : intArray)
        {
            linkedList.add(value);
        }

        for(int i=1; i<=rotation; i++)
        {
            int removed = linkedList.removeLast();
            linkedList.addFirst(removed);
        }

        linkedList.stream().forEach(System.out::print);
    }

    public static void countNoOfWordsInString(Consumer<String> printer)
    {
        printer.accept("\n\n\n\n\n");
        printer.accept("============================");
        printer.accept("Counting of words in string");
        printer.accept("============================");
        String sample = "This string contains two words which is fine by this mechanism of mechanism and words which contains two of those words";
        String[] sampleArray = sample.split(" ");

        Map<String, Integer> map =  Arrays
                .stream(sampleArray)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1))
                        )
                .entrySet().stream()
                .sorted
                        (Map.Entry.comparingByValue((o1, o2) -> o2.compareTo(o1)))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );
        //Map<String, Integer> result = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
        for(Map.Entry entry : map.entrySet())
        {
            printer.accept(entry.getKey() + " - " + entry.getValue() );
        }
    }

    public static void listFunction()
    {
        List<String> strList = Arrays.asList("Burhan", "Valikarimwala", "Banu", "Jalalbhaiwala", "Alifiya", "Zaynab");
        Function<String, String> reverseString = s -> new StringBuffer(s).reverse().toString();
        strList.stream().map(s -> reverseString.apply(s)).forEach(System.out::println);
    }

    public static void squareArray(double[] array)
    {
        System.out.println("Full Array:");
        Arrays.stream(array).mapToObj(i -> i*i).forEach(System.out::println);
        System.out.println("Min Amount: " + Arrays.stream(array).mapToObj(i -> i*i).sorted(Double::compareTo).findFirst().toString());

        System.out.println("Min Amount: " + Arrays.stream(array).mapToObj(i -> i*i).sorted(
                (o1, o2) ->
                {
                    if(o1.doubleValue() > o2.doubleValue())
                    {
                        return -1;
                    }
                    else if(o1.doubleValue() < o2.doubleValue())
                    {
                        return 1;
                    }
                    else
                        return 0;
                }).findFirst().toString());
    }
}
