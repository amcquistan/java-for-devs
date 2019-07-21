
// Employee.java

package com.thecodinginterface.accessmodifiers.models;

public class Employee extends Person {

  public enum Department {
    Accounting,
    Engineering,
    Marketing,
    Manufacturing
  }

  private Department department;

  public Employee(Person person, Department department) {
    // can use parent class (Person's) constructor to populate name and dob
    super(person.getFirstName(), person.getLastName(), person.dob);

    this.department = department;
  }
  
  // public getter
  public Department getDepartment() {
    return department;
  }

  /**
   * Factory method to create Employee objects
   * 
   * @param candidate
   * @param department
   * @return Employee instance
   * @throws Exception
   */
  public static Employee makeHire(Nebraskan candidate, Department department)
          throws Exception {
      if (!candidate.isEmployable()) {
        throw new Exception("Candidate is not employable");
      }

      return new Employee(candidate, department);
  }

}