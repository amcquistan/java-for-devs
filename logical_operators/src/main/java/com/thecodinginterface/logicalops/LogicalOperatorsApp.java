// LogicalOperatorsApp.java
package com.thecodinginterface.logicalops;

public class LogicalOperatorsApp {
    public static void main(String[] args) {

        // Equality of primitives
        System.out.println("1 == 1 " + (1 == 1));
        System.out.println("1 == 2 " + (1 == 2));
        System.out.println("1 != 2 " + (1 != 2));


        // Equality of reference type classes
        // The A class below does not provide an implementation of 
        // equals so it will have the default behavior of checking
        // for instance equality

        A a = new A(23);
        A a2 = a;
        A a3 = new A(23);

        // true, a2 points to the same object
        System.out.println(a + " == " + a2 + " " + (a == a2)); 

        // true, a2 points to the same object
        System.out.println(a + " equals " + a2 + " " + (a.equals(a2))); 

        // false, different instances
        System.out.println(a + " == " + a3 + " " + (a == a3)); 

        // false, but, prob not what you want
        System.out.println(a + " equals " + a3 + " " + (a.equals(a3))); 

        B b = new B(23);
        B b2 = new B(23);
        B b3 = new B(12);

        // false, not the same instance
        System.out.println(b + " == " + b2 + " " + (b == b2));

        // true, they are logically equivalent
        System.out.println(b + " equals " + b2 + " " + (b.equals(b2))); 

        // false, logically they are different
        System.out.println(b + " equals " + b3 + " " + (b.equals(b3))); 
    

        // Short circuit stacking logical operations

        // && is evaluated left to right and stops evaulation if a comparison yeilds false

        System.out.println("(1 == 1) && (2 == 2) " + ((1 == 1) && (2 == 2)));

        // (2 == 2) never evaluated because 1 != 1 is false
        System.out.println("(1 != 1) && (2 == 2) " + ((1 != 1) && (2 == 2)));

        // || also eval left to right and stops if true comparison is found
        // (2 == 2) is never evaluated because 1 == 1 is true
        System.out.println("(1 == 1) || (2 == 2) " + ((1 == 1) && (2 == 2)));
    }
}

/* 
*  Not good practice in Java to define multiple classes in one file.
*  This is just for example purposes.
*/

class A {
  int value;

  A(int value) { this.value = value; }
}

class B {
  int value;

  B(int value) { this.value = value; }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof B)) {
      return false;
    }

    B other = (B) o;
    if (this.value != other.value) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return String.format("{%s: value=%d}", 
      getClass().getSimpleName(), value);
  }
}
