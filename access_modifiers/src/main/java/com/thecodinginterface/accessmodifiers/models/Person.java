// Person.java

package com.thecodinginterface.accessmodifiers.models;

import java.time.LocalDate;

// class is publically available by any client code
public class Person {
    // name fields are only available to the Person class
    private String firstName;
    private String lastName;

    // DOB is available to the Person class and it's subclasses
    // but, Person will not provide an accessor method
    protected LocalDate dob;

    // since class is public, the constructor should be also
    public Person(String firstName, String lastName, LocalDate dob) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.dob = dob;
    }

    // getter for firstname available to all client code
    public String getFirstName() {
      return firstName;
    }

    // getter for lastname available to all client code
    public String getLastName() {
      return lastName;
    }
}