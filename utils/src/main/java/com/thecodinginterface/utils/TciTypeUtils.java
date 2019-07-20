
package com.thecodinginterface.utils;

// taken from: https://stackoverflow.com/questions/3993982/how-to-check-type-of-variable-in-java
// note this only works with primitives due to autoboxing
public class TciTypeUtils {

    private static boolean verbose = false;

    public static void setVerbose(boolean value) {
      verbose = value;
    }

    public static void printType(byte value) {
        printHelper(value + "", "byte");
    }

    public static void printType(short value) {
        printHelper(value + "", "short");
    }

    public static void printType(int value) {
        printHelper(value + "", "int");
    }

    public static void printType(long value) {
        printHelper(value + "", "long");
    }

    public static void printType(boolean value) {
        printHelper(value + "", "boolean");
    }

    public static void printType(float value) {
        printHelper(value + "", "float");
    }

    public static void printType(double value) {
        printHelper(value + "", "double");
    }

    public static void printType(char value) {
        printHelper(value + "", "char");
    }

    public static void printType(Object value) {
        printHelper(value.toString(), value.getClass().getSimpleName());
    }

    private static void printHelper(String value, String type) {
      if (verbose) {
        System.out.println(value + " is a " + type);
      } else {
        System.out.println(type);
      }
    }
}
