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
