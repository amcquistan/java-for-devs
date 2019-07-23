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
