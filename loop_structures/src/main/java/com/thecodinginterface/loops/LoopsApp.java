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
