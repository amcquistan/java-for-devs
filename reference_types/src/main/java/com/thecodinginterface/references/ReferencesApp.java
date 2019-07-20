// ReferencesApp.java
package com.thecodinginterface.references;

import com.thecodinginterface.utils.TciTypeUtils;

public class ReferencesApp {
    public static void main(String[] args) {

      TciTypeUtils.setVerbose(true);

      // String class is best used as a literal if you know
      // at compile time what it will be because of the String pool
      String s = "I am a string literal";
      TciTypeUtils.printType(s);

      // Can still use new to instantiate String also but, don't
      // get benefits of String pool reuse
      String s2 = new String("I am an instantiated string");
      TciTypeUtils.printType(s2);

      // Java also has Reference types for the Primitives
      Double d = new Double(123.45);
      TciTypeUtils.printType(d);

      // The Java Reference types for primitives should actually
      // use valueOf(...) factory methods because their contructors
      // are deprecated
      Double d2 = Double.valueOf(123.45);
      TciTypeUtils.printType(d2);

      Integer i = Integer.valueOf(567);
      TciTypeUtils.printType(i);

    }
}
