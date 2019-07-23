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
