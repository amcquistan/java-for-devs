// CollectionsApp.java

package com.thecodinginterface.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CollectionsApp {
    public static void main(String[] args) {
        List<String> letters = new ArrayList<>();

        // add single items with add(E e)
        letters.add("A");
        letters.add("B");
        letters.add("C");

        // check for inclusivity with contains(Object o)
        if (letters.contains("B")) {
            System.out.println("letters contains B");
        }

        // The Collection parent interface of List implements the Iterable<E>
        // interface which allows for their use in the enhanced for in loop
        for (String letter : letters) {
            // the number of items in the Collection is found with size()
            System.out.println(letter + " is one of the " + letters.size() + " letters in list");
        }

        // take items out with remove(Object o)
        if (letters.remove("B")) {
            System.out.println("letters no longer has B");
        }

        // to empty the List Collection use clear()
        letters.clear();

        // and prove it with isEmpty()
        if (letters.isEmpty()) {
            System.out.println("letters is now empty");
        }

        // and many more available methods ... so,
        // check the docs: https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html

        // A common implementation of Map is HashMap.
        // Also worth pointing out is that the Collection framework only works 
        // with reference types so, use numeric wrappers in place of primitive numerics.
        Map<Integer, String> lettersMap = new HashMap<>();

        // use put(key, value) to add items to the map which maps the number
        // 1 to the String letter A.
        lettersMap.put(Integer.valueOf(1), "A");

        // map 2 => B
        lettersMap.put(Integer.valueOf(2), "B");

        // map 3 => C
        lettersMap.put(Integer.valueOf(3), "C");

        // check for inclusivity of key value using containsKey()
        var key = Integer.valueOf(2);
        if (lettersMap.containsKey(key)) {
            // use get(key) to retrieve the value mapped to key
            System.out.println(key + " maps to value " + lettersMap.get(key));
        }

        // iterating over a Map is often done with first retrieving all
        // the keys in the form of a Set<E> collection using keySet()
        // in combination with a enhanced for in loop.
        for (Integer num : lettersMap.keySet()) {
            System.out.println(num + " maps to value " + lettersMap.get(num));
        }

        // clear() clears the map
        lettersMap.clear();

        // size() again, gets the number of mapped pairs in the map
        System.out.println("lettersMap now contains " + lettersMap.size() + " mapped pairs");

        // many other methods available in the Map interface so,
        // check the docs: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
        
    }
}
