// ControlStructuresApp.java

package com.thecodinginterface.controlstructures;

public class ControlStructuresApp {

    public static void main(String[] args) {
        int x = 10;
        int y = 100;
        int z = 1000;

        // regular if
        if (x == 10) {
          System.out.println("x is 10");
        }

        // if / else
        if (y == 10) {
          System.out.println("y is 10");
        } else {
          System.out.println("y is not 10");
        }

        // ternary equivalent from if / else example
        System.out.println((y == 10) ? "y is 10" : "y is not 10");

        // if / else if / else
        if (y < 100) {
          System.out.println("y is less than 100");
        } else if (y == 100) {
          System.out.println("y is 100");
        } else {
          System.out.println("y is greater than 100");
        }

        // remember when I said reference types use equals and that may not be enough?
        String name = "Adam";
        String name2 = new String("Adam");

        // here name and name2 are not the same object instances
        if (name != name2) {
          System.out.println("These are not the same object instances");
        }

        // String class implements equals to check for logical equivalence
        if (name.equals(name2)) {
          System.out.println("The names are logically the same but, are different object instances");
        }

        // String literals in Java are actually reused in something called the String pool because
        // they are expensive and occur often. Strings are the only reference types like this.
        if (name == "Adam") {
          System.out.println("Actually the exact same references in the JVM");
        }

        // switch statements are limited to a subset of types:
        // - whole number numeric types: byte / Byte, short / Short, int / Integer 
        // - Strings and char
        // - enums
        switch(z) {
          case 10:
            System.out.println("z is 10");
            break;
          case 100:
            System.out.println("z is 100");
            break;
          case 1000:
            System.out.println("z is 1000");
            break;
          default:
            System.out.println("I'm out of options...");
        }
    }
}


