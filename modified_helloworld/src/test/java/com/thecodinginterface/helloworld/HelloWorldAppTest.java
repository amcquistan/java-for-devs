
package com.thecodinginterface.helloworld;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldAppTest {
    @Test public void testAppHasAGreeting() {
        HelloWorldApp classUnderTest = new HelloWorldApp();
        assertNotNull(classUnderTest);
    }
}
