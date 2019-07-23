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
