package sortingAlgorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwareTesting.sortingAlgorithms.Sorting;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    private static final Logger logger = LoggerFactory.getLogger(SortingTest.class);
    private Sorting sorting;

    @BeforeEach
    void setUp() {
        sorting = new Sorting();
    }

    @Test
    void testSortArray() {
        int[] unsortedArray = {5, 3, 8, 1, 2};
        int[] expectedArray = {1, 2, 3, 5, 8};

        int[] result = sorting.sortArray(unsortedArray);
        logger.info("Sorted array: {}", Arrays.toString(result));
        assertArrayEquals(expectedArray, result);
    }

    @Test
    void testSortEmptyArray() {
        int[] unsortedArray = {};
        int[] expectedArray = {};

        int[] result = sorting.sortArray(unsortedArray);
        logger.info("Sorted empty array: {}", Arrays.toString(result));
        assertArrayEquals(expectedArray, result);
    }

    @Test
    void testSortSingleElementArray() {
        int[] unsortedArray = {5};
        int[] expectedArray = {5};

        int[] result = sorting.sortArray(unsortedArray);
        logger.info("Sorted single element array: {}", Arrays.toString(result));
        assertArrayEquals(expectedArray, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'5,3,8,1,2', '1,2,3,5,8'",
            "'2, 2, 1, 3', '1,2,2,3'",
            "'1, 1, 1', '1,1,1'",
            "'10, 5, 7, 2', '2,5,7,10'"
    })
    void testSortArrayParameterized(String unsorted, String expected) {
        int[] unsortedArray = parseArray(unsorted);
        int[] expectedArray = parseArray(expected);

        logger.info("Testing sorting with array: {}", Arrays.toString(unsortedArray));
        int[] result = sorting.sortArray(unsortedArray);
        logger.info("Sorted result: {}", Arrays.toString(result));

        assertArrayEquals(expectedArray, result);
    }

    // Helper method to convert a CSV array string into an integer array
    private int[] parseArray(String arrayString) {
        String[] stringValues = arrayString.split(",");
        int[] array = new int[stringValues.length];
        for (int i = 0; i < stringValues.length; i++) {
            array[i] = Integer.parseInt(stringValues[i].trim());
        }
        return array;
    }
}