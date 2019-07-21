// AccessModifiersApp.java

package com.thecodinginterface.accessmodifiers;

import java.time.LocalDate;
import java.time.Month;
import com.thecodinginterface.accessmodifiers.models.Person;
import com.thecodinginterface.accessmodifiers.models.Nebraskan;
import com.thecodinginterface.accessmodifiers.models.Employee;
import com.thecodinginterface.accessmodifiers.models.Employee.Department;

public class AccessModifiersApp {
    public static void main(String[] args) {
       
      Person jon = new Person("Jon", "Doe", LocalDate.of(1987, Month.SEPTEMBER, 23));
      Person jane = new Person("Jane", "Doe", LocalDate.of(2010, Month.AUGUST, 12));

      try {
        Employee employee = Employee.makeHire(
            new Nebraskan(jon),
            Department.Accounting
        );
        System.out.println(employee.getFirstName() + " is in " + employee.getDepartment());
      } catch(Exception e) {
        e.printStackTrace();
      }

      try {
        Employee employee = Employee.makeHire(
            new Nebraskan(jane),
            Department.Manufacturing
        );
        System.out.println(employee.getFirstName() + " is in " + employee.getDepartment());
      } catch(Exception e) {
        e.printStackTrace();
      }

    }
}
