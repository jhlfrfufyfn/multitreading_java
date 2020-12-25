package com.bsu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void TestSortingAscending() throws InterruptedException {
        List<Integer> list = Arrays.asList(10, 100, 23, 1, 2, 3, 2, 1, 2, 3, 1);
        SortingThread sThread = new SortingThread(list, (n1,n2)->n1-n2);
        Thread newThread = new Thread(sThread);
        newThread.start();
        newThread.join();
        assertArrayEquals(Arrays.asList(1,1,1,2,2,2,3,3,10,23,100).toArray(), sThread.getArray().toArray());
    }

    @Test
    void TestSortingDescending() throws InterruptedException {
        List<Integer> list = Arrays.asList(10, 100, 23, 1, 2, 3, 2, 1, 2, 3, 1);
        SortingThread sThread = new SortingThread(list, (n1,n2)->n2-n1);
        Thread newThread = new Thread(sThread);
        newThread.start();
        newThread.join();
        assertArrayEquals(Arrays.asList(100,23,10,3,3,2,2,2,1,1,1).toArray(), sThread.getArray().toArray());
    }

    @Test
    void TestSortingAscendingDigits() throws InterruptedException {
        List<Integer> list = Arrays.asList(10, 100, 2300, 2);
        SortingThread sThread = new SortingThread(list, (n1,n2)->Main.getNumberOfDigits(n1)-Main.getNumberOfDigits(n2));
        Thread newThread = new Thread(sThread);
        newThread.start();
        newThread.join();
        assertArrayEquals(Arrays.asList(2, 10, 100, 2300).toArray(), sThread.getArray().toArray());
    }

    @Test
    void TestSortingDescendingDigits() throws InterruptedException {
        List<Integer> list = Arrays.asList(10, 100, 2300, 2);
        SortingThread sThread = new SortingThread(list, (n1,n2)->Main.getNumberOfDigits(n2)-Main.getNumberOfDigits(n1));
        Thread newThread = new Thread(sThread);
        newThread.start();
        newThread.join();
        assertArrayEquals(Arrays.asList(2300,100,10,2).toArray(), sThread.getArray().toArray());
    }
}