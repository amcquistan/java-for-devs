// A.java

package com.thecodinginterface.classmembers;

class A {

  // class (static) field
  static int instances = 0;

  // instance field
  int x;

  // constructors are special methods that return intances of classes
  A() {
    // this is a default (empty) constructor
    instances++;
  }

  // you can have multiple constructors, each with different signatures, and
  // this one sets the instance field x and uses this() to call the default one.
  A(int x) {
    this(); // calls the A() constructor to increment the instances class field
    this.x = x;
  }

  /**
   * class method returning an int of number of classes 
   * that have been instantiated
   */
  static int getInstances() {
    return instances;
  }

  /**
   * instance method returning an int value that was passed
   * to the constructor producing an instance of class A and
   * assigned to instance field x
   */
  int getX() {
    return x;
  }
}
