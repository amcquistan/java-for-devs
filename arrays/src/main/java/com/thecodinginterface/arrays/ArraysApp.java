// ArraysApp.java

package com.thecodinginterface.arrays;

import java.util.Arrays;

public class ArraysApp {
    public static void main(String[] args) {
        // define an array of type int, all defaulted to zero
        int[] ints = new int[3];
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        System.out.println(ints[2]);

        // throws runtime exception java.lang.ArrayIndexOutOfBoundsException
        // because its max index is 2
        // System.out.println(ints[3]);

        // assign values with indexes
        ints[1] = 23;
        System.out.println(ints[1]);

        // one way to define and initialize an array at the same time
        double[] doubles = new double[] {
          1.23, 3.14159
        };

        // shorter way to define and initialize the same array
        double[] doubles2 = { 1.23, 3.14159 };

        // array of reference types (Strings)
        String[] strings = { "My", "name", "is", "Adam" };

        // Array has a length field
        System.out.println("strings array has " + strings.length + " items");

        // Array does not have a logical implement of equals, just 
        // default instance equality from Object#equals
        String[] strings2 = { "My", "name", "is", "Adam" };
        if (!strings.equals(strings2)) {
          System.out.println("Uh oh, these seem like the same right?");
        }

        // multi-dimensional (nested) arrays can be defined like this
        char[][] chars = {
          { 'a', 'b', 'c' },
          { 'd', 'e' },
          { 'f' }
        };

        // java.util.Arrays.toString(arr) is a way to "pretty" print an array
        System.out.println(Arrays.toString(chars[0]));
    }
}
