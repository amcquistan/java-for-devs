// InheritanceApp.java

package com.thecodinginterface.inheritance;

public class InheritanceApp {
    public static void main(String[] args) {
        // straight forward use of class A instance
        A a = new A();
        System.out.println(a.greet());
        System.out.println(a.greet("Howdy,"));
        
        // polymorphic use of B as an extension of A
        A aAsB = new B();
        System.out.println(aAsB.greet());
        System.out.println(aAsB.greet("Howdy,"));

        // compiler error: no suitable method found for greet(String, String).
        // uh oh, A doesn't have a greet(String, String) signature.
        // System.out.println(aAsB.greet("Howdy,", "Have a great day."));
        // System.out.println(aAsB.greet("Howdy,", "Have a great day."));

        // using B as a B type but, using inherited behavior of A
        B b = new B();
        System.out.println(b.greet("Howdy,", "Have a great day."));
        System.out.println(b.greet("Howdy,", "Have a great day."));

        // now force aAsB into an instance of B by casting aAsB into B type
        System.out.println(((B) aAsB).greet("Howdy,", "Have a great day."));
        System.out.println(((B) aAsB).greet("Howdy,", "Have a great day."));
    }
}

/* 
*  Not good practice in Java to define multiple classes in one file.
*  This is just for example purposes.
*/

class A {

  // example of overloading the greet method
  String greet(String prefix) {
    return prefix + " " + greet();
  }

  // example of overloading the greet method
  String greet() {
     return "I am class A.";
  }
}

class B extends A {

  // this is not overriding but, it is overloading the void greet() method of B
  String greet(String prefix, String suffix) {
    return greet(prefix) + " " + suffix;
  }

  // example of overriding the greet method in class A
  @Override
  String greet() {
    return "I am class B.";
  }
}