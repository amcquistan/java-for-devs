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

      // Class members are accessible from either an instantiated or
      // uninstantiated reference but, they are class specific not
      // instance specific. Its generally best practice to use the class
      // rather than the instance variable but, there is technically nothing
      // stoping you from using an instance variable.
      System.out.println(a1.getInstances());
      System.out.println(a2.getInstances());
      System.out.println(A.getInstances());

      // call to the empty constructor which leaves the x field 
      // with just the default value of 0
      A a3 = new A();
      System.out.println(a3.getX());

      // here locally scoped variable a4 is used with var keyword as it's type
      // which is easily inferred from the new'd up A class on the right hand side
      var a4 = new A(99);
      
      // var can be used with reference or primitive types
      var result = a4.getX() * 100;
      System.out.println("a4.getX() * 100 = " + result);
    }
}
