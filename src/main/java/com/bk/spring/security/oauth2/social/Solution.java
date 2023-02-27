package com.bk.spring.security.oauth2.social;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'miniMaxSum' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        Integer max = arr.stream().max(Integer::compareTo).get();
        Integer min = arr.stream().min(Comparator.comparing(Integer::intValue)).get();

        System.out.println("1%2 " + 1%2);
        long maxSum = (long) arr.stream().filter(i -> i!= min.intValue()).mapToLong(Integer::intValue).reduce(0,(a,b)->a+b);
        long minSum = (long) arr.stream().filter(i -> i!= max.intValue()).mapToLong(Integer::intValue).reduce(0,(a,b)->a+b);

        System.out.println(minSum + " " + maxSum);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ").toString());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.miniMaxSum(arr);

        bufferedReader.close();
    }
}

