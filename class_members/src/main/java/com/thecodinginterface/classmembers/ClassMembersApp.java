// ClassMembersApp.java

package com.thecodinginterface.classmembers;

public class ClassMembersApp {
    public static void main(String[] args) {

      // class members (fields and methods) are accessible by
      // referencing them the class in an uninstantiated state 
      // via client code
      System.out.println(A.getInstances());

      // instance members (fields and methods) are only accessible
      // from a instantiated object via client code
      A a1 = new A(10);
      System.out.println(a1.getX());
      System.out.println(A.getInstances());

      A a2 = new A(20);
      System.out.println(a2.getX());

      // class members can be accessed from either an instantiated or
      // uninstantiated reference but, they are class specific not
      // instance specific
      System.out.println(a1.getInstances());
      System.out.println(a2.getInstances());
      System.out.println(A.getInstances());

      // call to the empty constructor which leaves the x field 
      // with just the default value of 0
      A a3 = new A();
      System.out.println(a3.getX());
    }
}
