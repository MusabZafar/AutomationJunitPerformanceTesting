package org.softwareTesting.sortingAlgorithms;

import java.util.Arrays;

public class Sorting {

    // Method to sort the array in ascending order
    public int[] sortArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        Arrays.sort(array);
        return array;
    }
}