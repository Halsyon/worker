package com.project.customer.temp;

public class SomeTest {

    public static boolean getResultTest(int[] arr) {
        boolean isUp = true;

        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i + 1] >= arr.length - 1) {
                if(arr[i] > arr[i + 1]) {
                    isUp = false;
                }
            }
            if (!isUp) {
                break;
            }
        }

        return isUp;
    }

    public static void main(String[] args) {
        int[] someArr = new int[] { 1, 2, 3, 4, 5, 6};
        int[] someArr1 = new int[] { 13, 2, 3, 4, 5, 6};

        System.out.println(getResultTest(someArr1));
        System.out.println(getResultTest(someArr));
    }
}
