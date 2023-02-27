package com.bk.spring.security.oauth2.social;

import java.util.ArrayList;
import java.util.List;



public class Factors {

    public static void main(String[] args)
    {
        int number = 24
                ;

        List<Integer> factorsof24 = factors(number);
        List<Integer> factorsof36 = factors(36);

        System.out.println(factorsof36.retainAll(factorsof24));

        factorsof24.stream().forEach(i -> System.out.print(i + " "));
        System.out.println();
        factorsof36.stream().forEach(i -> System.out.print(i + " "));
    }

    private static List<Integer> factors(Integer number)
    {
        List<Integer> factors = new ArrayList<>();
        // loop runs from 1 to number
        for (int i = 1; i <= number; ++i) {
            if (number % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }
}
