package com.bk.spring.security.oauth2.social;

import java.util.HashMap;

public class PageCount {

    public static  void   main(String[] args)
    {
        int pages = pageCount(5, 4);
    }

    public static int pageCount(int n, int p) {
        // Write your code here
        int frontTurns = turnFromFront(0, 1, 0, p);
        int backTurns = turnFromBack(n, 0, 0, p);
        return frontTurns > backTurns ? backTurns : frontTurns;

    }

    private static int turnFromFront(int left, int right, int turns, int page)
    {

        if(left == page || right == page)
        {
            return turns;
        }
        else
        {
            return turnFromFront(right+1, right+2, turns+1, page);
        }
    }

    private static int turnFromBack(int left, int right, int turns, int page)
    {
        if(left == page || right == page)
        {
            return turns;
        }
        else
        {
            return turnFromBack(left-2, left-1, turns+1, page);
        }
    }
}
