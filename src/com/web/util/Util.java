package com.web.util;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by erniu on 2015/11/29.
 */
public class Util {

    public static void print(Object args) {
        System.out.println(args);
        System.out.println("\n");

    }


    public static void main(String[] args) throws Exception {

        final HashMap<String, String> map = new HashMap<String, String>(2);

        map.put(null,"1");

        System.out.println(map.get(null));

        map.put(null,"2");

        System.out.println(map.get(null));
    }
}
