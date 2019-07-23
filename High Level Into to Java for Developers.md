# High Level Introduction to Java for Developers

### Introduction

This article is a high level overview of the Java programming language intended for software developers proficient in other languages such as JavaScript, Python, C++, ect. The reader is expected to be familar with general programming constructs and the OOP paradigm. I draw some correlations among other language features here and there but, the intent is not to provide a comparative analysis between Java and other such languages.

Code snippets are heavily utilized to demonstrate language features and are composed of a collection of mini programs as Gradle projects hosted on GitHub available for play and experimentation.

### Installing OpenJDK11

In order to write and compile executable Java code the Java Development Kit (JDK) is required. For this I will be utilizing the OpenJDK version 11 which is a free, community supported, open-source, implementation of the Java Standard Edition originally developed by Sun Micorsystems and now maintained by Oracle. 

To install OpenJDK first down from the archives and extract to a location of your choice (I'll give some suggested locations soon).

##### Installing on Mac / Linux

Once the archive is downloaded extract it to a suitable location such as /Library/Java/JavaVirtualMachines on Mac or /usr/java on Linux so it looks as follows.

```
$ pwd
/Library/Java/JavaVirtualMachines
$ ls
jdk-11.0.2.jdk
```

Then update your user's .bash_profile to include a definition for an environment variable named JAVA_HOME and append to PATH the path to OpenJDK's bin directory as shown below. Modify as necessary if you installed to a different location or the version of the OpenJDK is different.

```
echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home' >> ~/.bash_profile
echo 'export PATH="$PATH:/Library/Java/JavaVirtualMachines/jdk-11.0.2.jdk/Contents/Home/bin"' >> ~/.bash_profile
```

Source the updated .bash_profile and check the Java version

```
$ . ~/.bash_profile
$ java --version
openjdk 11.0.2 2019-01-15
OpenJDK Runtime Environment 18.9 (build 11.0.2+9)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.2+9, mixed mode)
```

##### Installing on Windows

For windows extract the archive to some place like C:\java then update system environment variables to include a JAVA_HOME variable along with a path to the bin directory of the OpenJDK as shown below.

JAVA_HOME variable

*** JAVA_HOME_WIN.PNG **


Updated system PATH variable

*** JAVA_PATH_WIN.PNG ***

Testing java version from windows command prompt

*** JAVA_VESION_WIN.PNG ***

### Hello World Example

Similar to C / C++ every Java program requires a main method to serve as an entry point but, in Java that main method is always a static class method (more on static and class methods later). For example, I can create a file named HelloWorldApp.java, place the following in it and I have a program (note the file name HelloWorldApp.java and the HelloWorldApp class name inside it).

```
// HelloWorldApp.java

class HelloWorldApp {
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }
}
```

To compile to JVM byte code (this must be done in the same directory as the HelloWorldApp.java file).

```
$ javac HelloWorldApp.java 
```
To run the compiled program.

```
$ java HelloWorldApp
Hello World!
```

Take a quick look back at the System.out.println("Hello World!") bit in HelloWorldApp.java. You may be asking something along the lines of "where did the System... bit come from?". Its automatically brought in by an implicit import of the java.lang package which contains many common classes like String and System.

### Java Development with Gradle

Part of the trouble with getting up to speed with Java, in my opinion, is learning all the tooling like IDEs and what not that most Java devs work with. To keep things as simple as possible the code examples are all individual Gradle projects that can be opened in any ole minimal text editing progam a developer is used to and runnable with simple Gradle wrapper CLI commands.

For example, clone the repo and take a look at the modified_helloworld example below. This version of the hello world app is in a Gradle project and lives in an organizational directory structure known as a package named similar to namespaces in other languages like C. In this example the package is named com.thecodinginterface.helloworld (commas denote directory separators).

```
$ git clone https://github.com/amcquistan/java-for-devs.git
$ cd java-for-devs
$ cd modified_helloworld
$ cat src/main/java/com/thecodinginterface/helloworld/HelloWorldApp.java
package com.thecodinginterface.helloworld;

public class HelloWorldApp {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

To run the Gradle project on windows use the following command from within the modified_helloworld directory:

```
gradlew.bat run

> Task :run
Hello World

BUILD SUCCESSFUL in 12s
2 actionable tasks: 2 executed
```

and on Mac or Linux

```
$ ./gradlew run

> Task :run
Hello World

BUILD SUCCESSFUL in 12s
2 actionable tasks: 2 executed
```

### Data Types

In Java there are two data types: primitives and references (classes).

##### Primitive types

* boolean: true / false
* char: 16-bit unicode
* byte 8-bit int
* short: 16-bit int
* int: 32-bit int
* long: 64-bit int
* float: 32-bit floating point value
* double: 64-bit 64-bit floating point value

These should be pretty familar from most other languages and all can be assigned as literals but, in some cases you need a little extra info to get the point across for  what you want.

*Code Samples from primitive_types project*

```
// PrimitivesApp.java

package com.thecodinginterface.primitives;

import com.thecodinginterface.utils.TciTypeUtils;

public class PrimitivesApp {
    public static void main(String[] args) {
        TciTypeUtils.setVerbose(true);

        byte b = 123;
        TciTypeUtils.printType(b);

        // compiler error (incompatible types): possible lossy conversion from int to byte
        // byte b2 = 128; 
        // TciTypeUtils.printType(b2);

        short s = 250;
        TciTypeUtils.printType(s);

        // compiler error (incompatible types): possible lossy conversion from int to short 
        // oops ... math with shorts and bytes get cast to ints
        //   before the op and leaves you with a int. Java won't down
        //   cast a type without interventions to keep from loosing data/
        // short byteShortTotal = s + b;
        // TciTypeUtils.printType(byteShortTotal);

        // see ... byte + short => int
        int byteShortTotalAsInt = s + b;
        TciTypeUtils.printType(byteShortTotalAsInt);

        // explicitly cast back to short
        short byteShortTotal2 = (short) (s + b);
        TciTypeUtils.printType(byteShortTotal2);

        // compiler error: integer number too large
        // Java always assumes whole number literals are ints but,
        // 1_000_000_000_000 exceeds the 32-bit size of an int causing 
        // an overflow
        // long l = 1000000000000;
        // TciTypeUtils.printType(l);

        // add L suffix so Java knows the literal should be a long
        long l2 = 1000000000000L;
        TciTypeUtils.printType(l2);

        // floats and doubles are opposite, if you really want a 
        // literal to be a float add a f suffix;
        double d = 123.45;
        TciTypeUtils.printType(d);
        
        // compiler error (incompatible types): possible lossy conversion from double to float
        // float f = 123.45;
        // TciTypeUtils.printType(f);

        // fix with f suffix
        float f2 = 123.45f;
        TciTypeUtils.printType(f2);

        // char are pretty standard with other languages, use '' instead of ""
        char c = 'd';
        TciTypeUtils.printType(c);
    }
}
```

##### Reference Types

Here are a few Reference class types.

* Object
* String
* Integer
* Double
* System
* and way more ...

With the exception of String all reference types need to be instantiated to be used.

*Code samples from reference_types project*

```
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

      // Autoboxing and Unboxing
      // Java can convienently convert back in forth between primitives and
      // their Reference counter parts in what is known as autoboxing / unboxing

      int literalInt = 23;

      // autobox primitive int, literalInto, to Integer reference type
      Integer autoboxedInt = literalInt;
      TciTypeUtils.printType(autoboxedInt);

      // unbox autoboxedInt back to a primitive int
      int unboxedInt = autoboxedInt;
      TciTypeUtils.printType(unboxedInt);
    }
}
```

### Working with Classes

Unlike other languages Java does not have a concept of standalone functions that can live outside of a class. Classes are where all the action happens.

In Java its good practice to define just one class per .java file but, its not mandatory. The class name should match the name of the file. For example, in the PrimitivesApp.java file the class in it was named PrimitivesApp. Inside the .java source file there is an order in which the source code appears. Below I have annotated the PrimitivesApp.java file (in the primitive_types Gradle project), explaining this further.

```
// PrimitivesApp.java

// 1) package declarations come first but, are optional. 
//    A .java source code file without a package declaration (as in the first
//    HelloWorldApp.java example) is known as having an "unnamed package"
package com.thecodinginterface.primitives;

// 2) import statements that pull in classes from other packages are next
//    and they to are optional
import com.thecodinginterface.utils.TciTypeUtils;

// 3) Next are the class definitions. This is really the only mandatory part.
public class PrimitivesApp {
    public static void main(String[] args) {
		// ... omitting for brevity
    }
}
```

##### Class Members

Java classes are composed of members that can be separated into fields holding state data and methods providing behavior usually based off the data held in fields. Fields and methods are further subdivided into class members (aka static) and instance members. 

Methods are required to specify their return types which can be any primitive or reference type or, they may return nothing and are referred to as void methods.

Class fields members which are not explicitly initalized receive default values as listed below.

* numeric primitives like double and int are implicitly initialized to zero (ie, ints are 0 and doubles are 0.0)
* booleans default to false
* reference types default to null

Additionally, locally scoped variables (ie, those within a set of curly brackets at the method level and lower like an if statement) may be given the var keyword in place of a primitive or reference type.

*Code samples from class_members project*

This example program is broken up into two files as a more realistic example.

```
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
```

Below is the main method containing class that creates instances of the A class and interacts with it's members. 

```
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
```

##### Access Modifiers, Inheritence, and Encapsulation

Java has 3 explicit access modifiers for class members and one that is implicit.

* private: only accessible within it's class and is explicit
* protected: accessible within its class or subclasses and is explicit
* public: accessible from anywhere and is explicit
* package protected: accessible within the classes in the same package and is implicit (ie, used if no access modify is explicitly given)

Together the access modifiers work to provide encapsulation whereby instance fields are generally defined as private and lesser restricted methods are used to either set the values of private fields as well as get those values (ie, setters and getters). 

Similar to other languages Java methods have what are known as signatures. Below is an annotated main method signature.

*** JAVA_METHOD_SIGNATURE.PNG ***

* access modifier, optional
* static modifier, optional
* return type
* method name
* parameter list, optional
* throwable exceptions list, optional
* body

I assume the reader is familar with the general concept of [inheritance](https://en.wikipedia.org/wiki/Inheritance_(object-oriented_programming)) where one class (the subclass) gains functionality and state from a parent class. If this not a familar concept please consult an introductory article or text on the subject from the "Learning More about Java (books and other articles)" section. In Java you use the __extends__ keyword in the class definition to specify __up to one class__ you would like to inherit from as in.

```
class B extends A {
  
}
```
Another important concept for inheritance is polymorphism which is accomplishable by a subclass overriding the behavior of its parent class. In order for a subclass method to override the behavior of a parent class the method signatures must match identically. Additionally, its good practice to annotate the subclasses method with @Override which helps to the compiler your intent and allows it to check that the subclass's method signature does in fact match a method of the parent class. 

Closely related to method overriding is method overloading which is when two method in the samne class differ only in their parameter list but, this is not really related to inheritance.

*Code sample from inheritance project*

```
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

  // this is not overriding but, it is overloading
  String greet(String prefix, String suffix) {
    return greet(prefix) + " " + suffix;
  }

  // this is not overriding but, it is overloading the void greet() method of B
  @Override
  String greet() {
    return "I am class B.";
  }
}
```

*Code sample from access_modifiers project*

In this example there are relationships being modeled via inheritance and encapsulation is being used to limit access to the state of objects. Specifically, there is a base class Person that maintains information such as name and date of birth (DOB). 

```
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
```

Person is extended by another class named Nebraskan which defines a constant class field representing the age at which a person is considered employable (ie, 14 years old). 

```
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
```

The Person class is also extended by an Employee class which maintains info on what department an employee is currently part of. Additionally, the Employee class has a factory method for creating instances of Employee based off a instance of a Nebraskan object plus a department to assign to them. The factory method also checks to see that the Nebraskan instance is of minimum employment age before creating an instance or throwing an exception.

```
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
```

##### All Classes Inherit from java.lang.Object

Java reference types (classes) all implicitly inherit from the java.lang.Object class giving them access to its methods shown below.

* toString
* getClass
* equals
* hashCode
* clone
* finalize
* wait & notify

There is a lot that can be said about what this means for the reference classes that you interact with and implement. I will hit on the a few methods in later sections but, for a complete review I will leave it to the reader to further investigate using the resources in the "Learning More about Java (books and other articles)" section. 

### Logical Operators and Control Structures 

##### Logical Operators

Logical operators (as well as control structures) in Java are very much like most other C style languages. So unless your coming from a language like Python that uses the words and / or then you'll likely immediately feel comfortable with them. 

Java uses 

```
&& 
```

for stacked multiple equality comparisons and 

```
|| 
``` 

for stacked singular equality. These are both short circuiting operators that work left to right. Check the code samples if that wasn't clear (code speaks much more clearly than I do).

As for equality operators their choice will actually depend on whether you are dealing with primitives or reference types. 

For primitives you use

```
== 
```
for equality and,

```
!=
```

for not equal evaluations.

When comparing reference types you have to consider whether you are wanting to test for equality of object instances (aka, instance equality) using == or, if you are checking for logical equality based on the object's state in which case you will use the equals method available to all reference types. However, in some cases using the equals method may not be enough. 

Reference equality is one of the more complicated parts of Java so, bare with me here. By default the java.lang.Object#equals method behaves the same as == and it is left up to the class's implementors to override the base Object#equals implementation to handle what it means for two objects to be logically equivalent. 

*Code samples for logical_operators project* 

```
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
```

Note that this last example demonstrates the concept of polymorphic inheritance in Java by overriding both Object#toString and Object#equals. 

##### Control Structures

Java uses standard C-style control structures such as if, if / else, if / else if / else as well as switch statements. Java also supports the ternary operator. These should be very familar coming from at least all the other languages that I know of so, I will just jump right into code samples.

*Code sample from control_structures project*

```
// ControlStructuresApp.java

package com.thecodinginterface.controlstructures;

public class ControlStructuresApp {

    public static void main(String[] args) {
        int x = 10;
        int y = 100;
        int z = 1000;

        // regular if
        if (x == 10) {
          System.out.println("x is 10");
        }

        // if / else
        if (y == 10) {
          System.out.println("y is 10");
        } else {
          System.out.println("y is not 10");
        }

        // ternary equivalent from if / else example
        System.out.println((y == 10) ? "y is 10" : "y is not 10");

        // if / else if / else
        if (y < 100) {
          System.out.println("y is less than 100");
        } else if (y == 100) {
          System.out.println("y is 100");
        } else {
          System.out.println("y is greater than 100");
        }

        // remember when I said reference types use equals and that may not be enough?
        String name = "Adam";
        String name2 = new String("Adam");

        // here name and name2 are not the same object instances
        if (name != name2) {
          System.out.println("These are not the same object instances");
        }

        // String class implements equals to check for logical equivalence
        if (name.equals(name2)) {
          System.out.println("The names are logically the same but, are different object instances");
        }

        // String literals in Java are actually reused in something called the String pool because
        // they are expensive and occur often. Strings are the only reference types like this.
        if (name == "Adam") {
          System.out.println("Actually the exact same references in the JVM");
        }

        // switch statements are limited to a subset of types:
        // - whole number numeric types: byte / Byte, short / Short, int / Integer 
        // - Strings and char
        // - enums
        switch(z) {
          case 10:
            System.out.println("z is 10");
            break;
          case 100:
            System.out.println("z is 100");
            break;
          case 1000:
            System.out.println("z is 1000");
            break;
          default:
            System.out.println("I'm out of options...");
        }
    }
}
```

### Arrays

Arrays in Java are reference types which hold a fixed size homogenous sequence items that are of either primitive or other reference types. If you are coming from JavaScript, Python or other dynamic languages fear not, list like structures are available too, just hang with me for a bit. Also, just like essentially all other languages (minus R) all Java reference types that hold sequantial items (Strings, Arrays, Collections, ect ...) utilize zero based indexing. I think thats enough yammering for the moment so, I'll switch to code samples now.

*Code sample from arrays project*

```
// ArraysApp.java

package com.thecodinginterface.arrays;

import java.util.Arrays;

public class ArraysApp {
    public static void main(String[] args) {
        // define an array of type int, all defaulted to zero
        int[] ints = new int[3];
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        System.out.println(ints[2]);

        // throws runtime exception java.lang.ArrayIndexOutOfBoundsException
        // because its max index is 2
        // System.out.println(ints[3]);

        // assign values with indexes
        ints[1] = 23;
        System.out.println(ints[1]);

        // one way to define and initialize an array at the same time
        double[] doubles = new double[] {
          1.23, 3.14159
        };

        // shorter way to define and initialize the same array
        double[] doubles2 = { 1.23, 3.14159 };

        // array of reference types (Strings)
        String[] strings = { "My", "name", "is", "Adam" };

        // Array has a length field
        System.out.println("strings array has " + strings.length + " items");

        // Array does not have a logical implement of equals, just 
        // default instance equality from Object#equals
        String[] strings2 = { "My", "name", "is", "Adam" };
        if (!strings.equals(strings2)) {
          System.out.println("Uh oh, these seem like the same right?");
        }

        // multi-dimensional (nested) arrays can be defined like this
        char[][] chars = {
          { 'a', 'b', 'c' },
          { 'd', 'e' },
          { 'f' }
        };

        // java.util.Arrays.toString(arr) is a way to "pretty" print an array
        System.out.println(Arrays.toString(chars[0]));
    }
}
```

### Loops

Java has a set of what I will call traditional looping constructs that should be very familar to most people experienced in programming about any other language I know of. 

The looping structures available in the Java language are:

* while
* do / while
* traditional C-style for loop
* enhanced for in loop

Again, I feel code samples will best demonstrate the language syntax specifics.

*Code sample for loop_structures project*

```
// LoopsApp.java

package com.thecodinginterface.loops;

public class LoopsApp {
    public static void main(String[] args) {
        // while loop
        int i = 0;
        while (i < 3) {
          System.out.println(String.format("i is %d", i++));
        }

        // do / while
        i = 0;
        do {
          System.out.println(String.format("i is %d", i++));
        } while(i < 3);
    
        // C-style for loop
        int[] nums = { 1, 2, 3, 4, 5 };
        for (int j = 0; j < nums.length; j++) {
          System.out.println(String.format("index %d of nums is %d", j, nums[j]));
        }

        // enhanced for in loop
        for (int num : nums) {
          System.out.println(num);
        }
    }
}
```

### Interfaces

Interfaces, the extremely valuable contracts of OOP, are certainly not unique to Java. Defining an interface is much like defining a class except that the interface keyword is used in the place of class. Interfaces are still considered a reference type but, they can never be instantiated and only come to life when classes that implement them are instantiated. To implement an interface you add implements then the name of one or more interfaces after the name of the class like so.

```
// A is the class then Writing and Reading are the interfaces
class A implements Writing, Reading {

}
```

Interfaces may contain the following members:

* constant fields
* method signatures without a body which are implicitly public
* default methods
* static methods

*Code sample from interfaces project*

In this example project I have defined an Interface named RepositoryDAO which defines a single method signature that must be implemented by classes that implement the interface. The interface represents a type that saves an instance of the generic java.lang.Object class which, is a poor way to generically represent any class, but a better solution will be discussed in the Generics section.

```
// RepositoryDAO.java

package com.thecodinginterface.interfaces;

public interface RepositoryDAO {

    boolean save(Object o);
}
```

Next is a Movie class type that will be used for demonstrating an object to save to either a file or a database.

```
// Movie.java

package com.thecodinginterface.interfaces;

class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
```

Below is the first example class that implements the RepositoryDAO interface named MovieFileRepository which mocks saving an instance of Movie to a file.

```
// MovieFileRepository.java

package com.thecodinginterface.interfaces;

public class MovieFileRepository implements RepositoryDAO {

    /**
     * Save Movie object to file
     */
    public boolean save(Object o) {
        Movie movie = (Movie) o;
        System.out.println("saving " + movie.getTitle() + " to file");
        return true;
    }
}
```

Next I present a second example class that implements the RepositoryDAO interface named MovieDBRepository which mocks saving an instance of Movie to a database.

```
// MovieDBRepository.java

package com.thecodinginterface.interfaces;

public class MovieDBRepository implements RepositoryDAO {

    /**
     * Save Movie object to database
     */
    public boolean save(Object o) {
        Movie movie = (Movie) o;
        System.out.println("saving " + movie.getTitle() + " to database");
        return true;
    }
}
```

The InterfacesApp class below has an instance of MovieFileRepository and MovieDBRepository as class fields. The main method assembles an array of a few Movie instances then iterates over them calling a method that returns one of the two class implementations of the RepositoryDAO interface based off whether the movie title contains the word man. The returned RepositoryDAO instance type is then passed to another method named persist which accepts an instance of the RepositoryDAO interface and a Movie object then uses it to save the movie.

```
// InterfacesApp.java

package com.thecodinginterface.interfaces;

public class InterfacesApp {

    static RepositoryDAO fileRepo = new MovieFileRepository();
    static RepositoryDAO dbRepo = new MovieDBRepository();

    public static void main(String[] args) {
        Movie[] movies = {
            new Movie("Cars"),
            new Movie("Iron Man"),
            new Movie("Batman"),
            new Movie("The Incredibles")
        };

        for (Movie movie : movies) {
            RepositoryDAO repo = getRepo(movie);
            persist(repo, movie);
        }
    }

    // persist only requires that the reference type for repo variable
    // adhears to the contract specified in RepositoryDAO interface.
    // It doesn't know or care if MovieFileRepository or MovieDBRepository 
    // are actually being passed to it because they are both of the 
    // RepositoryDAO interface type
    static void persist(RepositoryDAO repo, Movie movie) {
        repo.save(movie);
    }

    static RepositoryDAO getRepo(Movie movie) {
        RepositoryDAO repo = fileRepo;
        if (movie.getTitle().toLowerCase().contains("man")) {
            repo = dbRepo;
        }
        return repo;
    }
}
```

Here is the output of running the interfaces Gradle project.

```
$ ./gradlew run

> Task :run
saving Cars to file
saving Iron Man to database
saving Batman to database
saving The Incredibles to file

BUILD SUCCESSFUL in 0s
2 actionable tasks: 2 executed
```

### Abstract Classes

Abstract classes in Java are very much like interfaces having significant overlap of structure and function. You define an abstract class by placing the abstract keyword before the class keyword and, just like interfaces, abstract classes cannot be instantiated directly but, they may have constructors which is different from interfaces. Abstract classes may also have non-final (ie, non-constant) fields in them and can have non-public methods whereas interfaces cannot. Since abstract classes are still classes you can only extend one at a time.

In case you are wondering which you should use well ... thats kinda up to the programmer and their style / preferences for class design but, it seems that interfaces are more common from my experience and likely because it affords the developer the option of multiple inheritance like behavior. 

*Sample code from abstract_classes project*

In this example program I actually give a comparison of using both interfaces and abstract classes to implement a set of read and write methods.  In the example interfaces, Readable and Writable are broken out into two very isolated interfaces.

```
// Writable.java

package com.thecodinginterface.abstractclasses;

public interface Writable {
    void write(String s);
}

// Readable.java

package com.thecodinginterface.abstractclasses;

public interface Readable {
    String read();
}
```

Below the class named A provides implementations for both interfaces to "write" string data in a terribly inefficient manner as well as to "read" string data which is just a getter.

```
// A.java

package com.thecodinginterface.abstractclasses;

class A implements Readable, Writable {

    private String s;

    public A(String s) {
        this.s = s;
    }

    public void write(String s) {
        String prefix = "";
        if (!s.startsWith("\n")) {
            prefix = "\n";
        }
        this.s += prefix + s;
    }

    public String read() {
        return s;
    }
}
```

For the abstract class I combine the two methods in one ReadAndWritable abstract class. The ReadAndWritable abstract class also defines a protected string field accessible to subclasses and provides a constructor.

```
// ReadAndWritable.java

package com.thecodinginterface.abstractclasses;

public abstract class ReadAndWritable {

    protected String s;

    public ReadAndWritable(String s) {
        this.s = s;
    }

    public abstract void write(String s);

    public abstract String read();
}
```
The below B class is really just a copy of A but, it extends the ReadAndWritable abstract class utilizing it's constructor and String field.

```
// B.java

package com.thecodinginterface.abstractclasses;

class B extends ReadAndWritable {

    public B(String s) {
        super(s);
    }

    public void write(String s) {
        String prefix = "";
        if (!s.startsWith("\n")) {
            prefix = "\n";
        }
        this.s += prefix + s;
    }

    public String read() {
        return s;
    }
}
```

The AbstractClassesApp class demonstrates using both of these class implementations and how they can still be separable based off the interface / abstract class.

```
// AbstractClassesApp.java

package com.thecodinginterface.abstractclasses;

public class AbstractClassesApp {
    public static void main(String[] args) {
        
        A a = new A("Howdy, I'm A.");
        // JVM knows to use writeToObj(Writable, String) for these
        writeToObj(a, "I implement the write method from Writable interface.");
        writeToObj(a, "I also implement the read method from the Readable interface.");
        System.out.println(a.read());

        
        B b = new B("Hello there, I'm B.");
        // JVM knows to use writeToObj(ReadAndWritable, String) for these
        writeToObj(b, "I implement the write method from ReadAndWritable abstract base class.");
        writeToObj(b, "I also implement the read method from the ReadAndWritable abstract base class.");
        System.out.println(b.read());
    }

    public static void writeToObj(Writable writable, String msg) {
        writable.write(msg);
    }

    public static void writeToObj(ReadAndWritable readAndWritable, String msg) {
        readAndWritable.write(msg);
    }
}
```

### Generics 

Generics in Java provide a beautiful combination of flexibility and type safety which leads to greater abstraction paired with improved code quality afforded by type checking. Realizing that sounds quite fluffy and vague let me try to prove my point by reintroducing the example from the Interfaces section which defined a RepositoryDAO interface shown below.

```
// RepositoryDAO.java

package com.thecodinginterface.interfaces;

public interface RepositoryDAO {

    boolean save(Object o);
}
```

At first glance it appears that the RepositoryDAO#save(Object o) signature is a brilliant choice affording flexibility enough to work with any reference type since all are decendents of Object. This argument is propped up by the fact that I implemented RepositoryDAO in the classes MovieDBRepository and MovieFileRepository which demonstrates my abuse of this flexibility by haphazardly force casting the Object parameter into Movie reference classes as shown below.

```
// MovieFileRepository.java

package com.thecodinginterface.interfaces;

public class MovieFileRepository implements RepositoryDAO {

    /**
     * Save Movie object to file
     */
    public boolean save(Object o) {
        Movie movie = (Movie) o;
        System.out.println("saving " + movie.getTitle() + " to file");
        return true;
    }
}
``` 
The problem become readily apparent if I accidentially pass in a type other than Movie, say a String like "Kung Fu Panda". 

```
RepositoryDAO fileRepo2 = new MovieFileRepository();
var favoriteMovie = "Kung Fu Panda";
fileRepo2.save(favoriteMovie);
```

What happens is the compiler thinks its perfectly normal behavior, afterall it is of type Object, and compiles the class into byte code to be used but, then the following line is reached at runtime 

```
Movie movie = (Movie) o;
```

and oops ...

```
Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class com.thecodinginterface.interfaces.Movie
```

This is exactly the type of problem that Generics were created to solve. To convert the RepositoryDAO interface into a generically typed interface I add the diamond operator to the end of the interface and place the common formal type parameter symbol E within it as in RepositoryDAO<E>. Then use the same E symbol in place of Object in the save(...) methods as in save(E e). Then in the implementation classes I replace E with Movie as well as anywhere the RepositoryDAO interface was used in the main class containing code.  See the updated version of the original interfaces example project, renamed to generics, shown below.

*Code sample from generics project*

RepositoryDAO is now a formally typed generic interface.

```
// RepositoryDAO.java

package com.thecodinginterface.generics;

public interface RepositoryDAO<E> {

  boolean save(E e);
}
```

MovieFileRepository class now implements RepositoryDAO typed to Movie.

```
// MovieFileRepository.java

package com.thecodinginterface.generics;

class MovieFileRepository implements RepositoryDAO<Movie> {

    @Override
    public boolean save(Movie movie) {
        System.out.println("saving " + movie.getTitle() + " to file system");
        return true;
    }
}
```

MovieDBRepository class is also updated to implement RepositoryDAO typed to Movie.

```
// MovieDBRepository.java

package com.thecodinginterface.generics;

class MovieDBRepository implements RepositoryDAO<Movie> {

    @Override
    public boolean save(Movie movie) {
        System.out.println("saving " + movie.getTitle() + " to database);
        return true;
    }
}
```

Every type declaration of the RepositoryDAO interface must now include the <Movie> suffix to tell the compiler that they are to be type checked against the Movie class.

```
// GenericsApp.java

package com.thecodinginterface.generics;

public class GenericsApp {

  // must specify Movie for the interface types to take 
  // advantage of type safety
  static RepositoryDAO<Movie> fileRepo = new MovieFileRepository();
  static RepositoryDAO<Movie> dbRepo = new MovieDBRepository();

  public static void main(String[] args) {

      // compiler error: incompatible types: String cannot be converted to Movie
      //RepositoryDAO<Movie> fileRepo2 = new MovieFileRepository();
      //var favoriteMovie = "Kung Fu Panda";
      //fileRepo2.save(favoriteMovie);

      Movie[] movies = {
          new Movie("Cars"),
          new Movie("Iron Man"),
          new Movie("Batman"),
          new Movie("The Incredibles")
      };

      for (Movie movie : movies) {
          RepositoryDAO<Movie> repo = getRepo(movie);
          persist(repo, movie);
      }
  }

  static void persist(RepositoryDAO<Movie> repo, Movie movie) {
      repo.save(movie);
  }

  static RepositoryDAO<Movie> getRepo(Movie movie) {
      RepositoryDAO<Movie> repo = fileRepo;
      if (movie.getTitle().toLowerCase().contains("man")) {
          repo = dbRepo;
      }
      return repo;
  }
}
```

And here is the output.

```
$ ./gradlew run

> Task :run
saving Cars to file system
saving Iron Man to database
saving Batman to database
saving The Incredibles to file system

BUILD SUCCESSFUL in 0s
2 actionable tasks: 2 executed
```

Generics are used extensively in the next topic, collections, so if the subject still feels a little merky more examples will follow soon.

### Collections 

The Java collections framework is rather large but, is also highly intuitive due to its well designed API. That being said, my intent is to provide enough converage to allow for comfortability in further exploration. The collections framework is recognized as a toolset composed of Interfaces, Implementation classes and, common algorithms that facilitate working with sequences of reference types.

Specifically, collections are composed from two interface definitions: [Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html) and [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html). Those interfaces along with their subinterfaces and implementation classes live in the [java.util](https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html) package. The Collection interface is designed to work with data structures that are flat list like sequences of reference types similar to that of arrays. The Map interface is designed to work with dictionary like (aka, hash map) data structures. 

Probably the most common interface that falls within the Collection parent interface is the generic List interface and often used in combination with the most common implementation class is ArrayList which is also a generic.

As with most things in programming examples speak volumes over text so, let me jump into the code sample.

*Code sample for collections project*

```
// CollectionsApp.java

package com.thecodinginterface.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CollectionsApp {
    public static void main(String[] args) {
        List<String> letters = new ArrayList<>();

        // add single items with add(E e)
        letters.add("A");
        letters.add("B");
        letters.add("C");

        // check for inclusivity with contains(Object o)
        if (letters.contains("B")) {
            System.out.println("letters contains B");
        }

        // The Collection parent interface of List implements the Iterable<E>
        // interface which allows for their use in the enhanced for in loop
        for (String letter : letters) {
            // the number of items in the Collection is found with size()
            System.out.println(letter + " is one of the " + letters.size() + " letters in list");
        }

        // take items out with remove(Object o)
        if (letters.remove("B")) {
            System.out.println("letters no longer has B");
        }

        // to empty the List Collection use clear()
        letters.clear();

        // and prove it with isEmpty()
        if (letters.isEmpty()) {
            System.out.println("letters is now empty");
        }

        // and many more available methods ... so,
        // check the docs: https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html

        // A common implementation of Map is HashMap.
        // Also worth pointing out is that the Collection framework only works 
        // with reference types so, use numeric wrappers in place of primitive numerics.
        Map<Integer, String> lettersMap = new HashMap<>();

        // use put(key, value) to add items to the map which maps the number
        // 1 to the String letter A.
        lettersMap.put(Integer.valueOf(1), "A");

        // map 2 => B
        lettersMap.put(Integer.valueOf(2), "B");

        // map 3 => C
        lettersMap.put(Integer.valueOf(3), "C");

        // check for inclusivity of key value using containsKey()
        var key = Integer.valueOf(2);
        if (lettersMap.containsKey(key)) {
            // use get(key) to retrieve the value mapped to key
            System.out.println(key + " maps to value " + lettersMap.get(key));
        }

        // iterating over a Map is often done with first retrieving all
        // the keys in the form of a Set<E> collection using keySet()
        // in combination with a enhanced for in loop.
        for (Integer num : lettersMap.keySet()) {
            System.out.println(num + " maps to value " + lettersMap.get(key));
        }

        // clear() clears the map
        lettersMap.clear();

        // size() again, gets the number of mapped pairs in the map
        System.out.println("lettersMap now contains " + lettersMap.size() + " mapped pairs");

        // many other methods available in the Map interface so,
        // check the docs: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
        
    }
}
```

Program output.

```
$ ./gradlew run

> Task :run
letters contains B
A is one of the 3 letters in list
B is one of the 3 letters in list
C is one of the 3 letters in list
letters no longer has B
letters is now empty
2 maps to value B
1 maps to value B
2 maps to value B
3 maps to value B
lettersMap now contains 0 mapped pairs

BUILD SUCCESSFUL in 0s
2 actionable tasks: 2 executed
```

### Wait, How Many Items are in that Thing?

There is a saying I really like that goes something like this, "When you know there is an Elephant in the room ... Introduce it". In Java that Elephant is the count of items in data structures that have a sequence of items like String, Array, and Collections because they all have a slightly different way of giving you their answer to the question "How many items are in that thing?".

For a String the number of consecutive char values that make up a particular String is determined via a call to String#length() method.

```
String name = "Adam";
name.length(); // 4
```

When working with an Array you access the Array#length field to find out the allocated space for items the array can hold.

```
int[] nums = new int[4];
nums.length; // 4
```

As you just saw in the previous section on Collections they use a method again to determine the number of items contained in the Collection but, it is named size().

```
List<String> items = new ArrayList<>();
items.add("book");
items.add("pencil");
items.add("ruler");
items.size(); // 3
```

Unfortanutely I don't have any real tricks for working with this Elephant but, now that you are acquainted spending some time with it will lead to enough familarity to because comfortable with this idiosynchrasy.

### Learning More about Java (books and other articles)

Here are a few additional resources that have helped me in my journey as a Java developer so, if you made it this far and are interested in further progression consider checking them out.

* I'm a big fan of the Murach books on programming because they do a great job of striking a balance between lots of code samples and short, to the point explanations, and [Murach's Java Programming (5th Edition)](https://www.amazon.com/gp/product/1943872074/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=1943872074&linkCode=as2&tag=thecodinginte-20&linkId=f8828ef151ff81c88b90535b96321a49) has been a good reference for me.
* When it comes to best practices in Java, [Effective Java, 3rd Ed](https://www.amazon.com/gp/product/0134685997/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0134685997&linkCode=as2&tag=thecodinginte-20&linkId=bc0211ce54ea15c02caa205e5fad43af) is unparalleled
* If you want a bit of a deep dive into the default methods inherited by all Java reference types I wrote a series on them over at [StackAbuse](https://stackabuse.com/javas-object-methods-tostring/)
* If you are interested in learning more about Gradle have a look at [Beginner's Guide to Gradle for Java Developers](https://thecodinginterface.com/blog/gradle-guide-for-java-developers/)

### Conclusion

For this article I have tried to limit the content to what I will refer to as robust, traditional enterprise grade, Java programming constructs and paradigms hopefully approachable to developers proficient in other programming languages. Java is a large, extremely popular, secure, battle tested language that has facilitated numerous innovations and incalculable economic impact but, unfortunately went through a period of resting on it's laurels. That being said the Java language has recently experienced some very useful, productivity boosting, and asethically appealing enhancements that I feel is leading to a revitalization of the language. I hope to cover some of these topics in the future like switch expressions, and functional interfaces, streams and lambda functions. 

As always, thanks for reading and don't be shy about commenting or critiquing below.