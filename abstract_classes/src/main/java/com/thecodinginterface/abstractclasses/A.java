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


