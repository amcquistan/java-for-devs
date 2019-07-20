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

Part of the trouble with getting up to speed with Java, in my opinion, is dealing with and learning all the tooling such as IDEs and what not that most Java devs work with. To keep things as simple as possible the code examples are all in self contained Gradle projects with code samples that can be opened in any ole minimal text editing progam a developer is used to and runnable with simple CLI Gradle wrapper commands.

For example, clone the code samples from the repo and take a look at the modified_helloworld example below which is only different in that it now is in a gradle project and lives in an organizational directory structure known as a package. Its package is named com.thecodinginterface.helloworld (commas denote directory heirarchy) and all that means is it is compiled from the level of the com directory

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

To run the program using Gradle on windows use the following commands from directly with the modified_helloworld directory:

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

If you are interested more in using Gradle have a look at [Beginner's Guide to Gradle for Java Developers](https://thecodinginterface.com/blog/gradle-guide-for-java-developers/)

### Data Types

Like many other languages there are two data types: primitives and references (classes).

##### Primitive types

* boolean  true / false
* char 16-bit unicode
* byte 8-bit int
* short 16-bit int
* int 32-bit int
* long 64-bit int
* float 32-bit floating point value
* double 64-bit 64-bit floating point value

These should be pretty familar from most other languages and all can be created and assign as literals but, in Java you need a little extra info to get the point across that you really want what you want.

##### Code Sample from primitive_types project

```
// PrimitivesApp.java

package com.thecodinginterface.primitives;

import com.thecodinginterface.utils.TciTypeUtils;

public class PrimitivesApp {
    public static void main(String[] args) {
        TciTypeUtils.setVerbose(true);

        byte b = 123; // below 128 so thats good to go
        
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

A few examples of Class types are.

* Object
* String
* Integer
* Double
* System
* and way more ...

With the excetion of String all reference class types need to be instantiated to be used.

##### Code samples from reference_types project

```
// ReferencesApp.java
package com.thecodinginterface.references;

import com.thecodinginterface.utils.TciTypeUtils;
import com.thecodinginterface.utils.TciUtils;

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

An important thing to know with Java reference types (aka class types) is that they all implicitly inherit from the java.lang.Object class and gives them access to its methods shown below.

* toString
* getClass
* equals
* hashCode
* clone
* finalize
* wait & notify

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
