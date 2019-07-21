// Nebraskan.java

package com.thecodinginterface.accessmodifiers.models;

import java.time.LocalDate;
import java.time.Period;;

public class Nebraskan extends Person {
  public static final int MIN_AGE_FOR_EMPLOYMENT = 14;

  public Nebraskan(Person person) {
    // Use the parent constructor to populate name and dob field.
    // Since Person#firstName and Person#lastName are private the 
    //   Nebraskan subclass must use the public getters but, since
    //   Person#dob is protected the Nebraskan subclass can access it.
    super(person.getFirstName(), person.getLastName(), person.dob);
  }

  public boolean isEmployable() {
    Period period = Period.between(dob, LocalDate.now());
    return period.getYears() >= MIN_AGE_FOR_EMPLOYMENT;
  }
}

