# High Level Into to Java for Developers

### Introduction

This article is designed to be a high level overview of the Java programming language intended for software developers proficient in other languages such as JavaScript, Python, C++, ect. As such I will be drawing some correlations among some language features here an there but, the intent is not to provide a comparative analysis between other such languages.

All code snippets are composed of a collection of mini programs hosted and available on GitHub for play and experimentation.

##### Contents

* installing openjdk (install, setup JAVA_HOME)
* hello world example (compile and run by cli)
* getting off on the right foot with Gradle
* data types (primitives vs references), Java wrapper classes Integer, Double, ect ... and autoboxing / unboxing
* classes (hit on Object class and implicit inheritance of it)
  - class members (fields): class variables (static), instance variables, local variables
  - class members (methods) (static methods, regular methods)
  - encapsulation and access modifiers
* control structures
* arrays and loops
* Interfaces and abstract classes
* collections and generics

### Installing OpenJDK and the Classic Hello World Example

In order to write and compile executable Java code the Java Development Kit (JDK) is required. For this article I will be utilizing the OpenJDK version 11 which is a free, open-source, implementation of the Java Standard Edition originally developed by Sun Micorsystems and now maintained by Oracle. 

To install OpenJDK version 11 first down from the OpenJDK archives and extract to a location of your choice (I'll give some suggested locations soon).

##### Installing on Mac / Linux

Once the archive is downloaded extract it to a suitable location such as /Library/Java/JavaVirtualMachines on Mac or /usr/java on Linux so it looks as follows.

```
$ pwd
/Library/Java/JavaVirtualMachines
$ ls
jdk-11.0.2.jdk
```

Then update your user's .bash_profile to include a definition for an environment variable named JAVA_HOME and append a path to the OpenJDK's bin directory as shown below. Modify as necessary if you installed to a different location or the version of the OpenJDK is different.

```
# java .bash_profile updates 
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

Part of the trouble with getting up to speed with Java, in my opinion, is learning all the tooling like IDEs and what not that most Java devs work with. To keep things as simple as possible the code examples are all individual Gradle projects that can be opened in any ole minimal text editing progam a developer is used to and runnable with simple CLI Gradle wrapper commands.

For example, clone the repo and take a look at the modified_helloworld example below. This version of the hello world app is in a gradle project and lives in an organizational directory structure known as a package and is similar to namespaces in other languages like C. In this example the package is named com.thecodinginterface.helloworld (commas denote directory heirarchy).

```
$ git clone .... # fill in repo url later
$ cd ..... # fill in cd into repo
$ ls -l # show output of final repo structure
$ cd modified_helloworld
$ cat src/main/java/com/thecodinginterface/helloworld/HelloWorldApp.java
package com.thecodinginterface.helloworld;

public class HelloWorldApp {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

To run the program Gradle on windows use the following command from within the modified_helloworld directory:

```
gradlw.bat run

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

If you are interested in learning more about Gradle have a look at [Beginner's Guide to Gradle for Java Developers](https://thecodinginterface.com/blog/gradle-guide-for-java-developers/)

### Data Types

In Java there are two data types: primitives and references (classes).

##### Primitive types

* boolean  true / false
* char 16-bit unicode
* byte 8-bit int
* short 16-bit int
* int 32-bit int
* long 64-bit int
* float 32-bit floating point value
* double 64-bit 64-bit floating point value

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

##### Reference Data Types

Here are a few Reference class types.

* Object
* String
* Integer
* Double
* System
* and way more ...

With the excetion of String all reference types need to be instantiated to be used.

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

Unlike other language Java does not have a concept of standalone functions that can live outside of a class. So classes are where all the action happens.

In Java its good practice to define just one class per .java file but, its not mandatory. The class name should match the name of the file. For example, in the HelloWorldApp.java file the class in it was named HelloWorldApp. Inside the .java source file there is an order in which the source code appears. Below I have annotated the PrimitivesApp.java file (in the primitive_types Gradle project), explaining this further.

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

Below is an example of an annotated Java class signature.



##### Class Members

Java classes are composed of members that can be separated into fields which hold state data and methods which provide behavior usually based off the state held in fields. Both fields and methods are further subdivided into class members (aka static) and instance members. Methods are required to specify their return types which can be any primitive or reference type or, could return nothing and in that case are known as void.

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
  A(int x) {
    this.x = x;
    instances++;
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
Below is the main method containing class that creates instances of the A class and interacts with it's fields. This class introduces a new type of field (variable) known as a local variable which is only visible inside a code block but, I am going to assume the reader already understands this concept.

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
      // from a instantiated object via client code.
      // Here the a1 variable of type A is of local scope (locally scoped to the main method)
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
    }
}
```

##### Access Modifiers, Inheritence and Encapsulation

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

The person class is also extended by an Employee class which maintains info on what department an employee is currently part of. Additionally, the employee class has a factory method for creating instances of Employee based off a instance of a Nebraskan object and a department to assign to them but, the method checks to see that the Nebraskan instance is of minimum employment age before creating an instance or throwing an exception.

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

### Control Structures

Control structures in Java are very much like most other C style languages. So unless your coming from a language like Python that uses the words and / or then you'll likely immediately feel comfortable with them. 

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

for not equal constructs.

When comparing reference types you have to consider whether your are wanting to test for equality of object instances (aka, instance equality) or if your are checking for logical equality based on the object's state.

For instance equality you can use

```
== // or !=
```

but, if you are trying to check to see if the objects are logically equivalent then you need to use the object's equals() method and, in some cases that may not be enough. This is one of the more complicated parts of Java so, bare with me here. By default the java.lang.Object#equals method behaves the same as == and it is left up to the classes implementors to override the base Object#equals implementation to handle what it means for two objects to be logically equivalent. Check the code samples below for examples of this.

##### Code samples for logical_operators project

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

Short circuiting operators below are evaluated left to right



* && for logical and comparison
* 

However, there is an important distinction should be made about how logical operators are used depending on working with primitives or reference types. 

### Arrays and Loops

### Interfaces and Abstract Classes

### Collections and Generics

### Learning More about Java (books and other articles)

### Conclusion
