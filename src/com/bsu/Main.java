package com.bsu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            System.out.println("Enter the number of elements in array: ");
            int n = Integer.parseInt(cin.nextLine());

            Integer[] array = new Integer[n];

            Random generator = new Random();
            for (int i = 0; i < n; i++) {
                array[i] = generator.nextInt(1000);
            }

            System.out.println("Size: " + n);
            for (int i = 0; i < n; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.print(System.lineSeparator());

            System.out.println("Enter 1 if the sorting order should be ascending by order");
            System.out.println("Enter 2 if the sorting order should be descending by order");
            System.out.println("Enter 3 if the sorting order should be ascending by the number of digits");
            System.out.println("Enter 4 if the sorting order should be descending by the number of digits");

            int orderType = Integer.parseInt(cin.nextLine());
            Comparator<Integer> comparator;
            switch (orderType) {
                case 1:
                    comparator = (n1, n2) -> n1 - n2;
                    break;
                case 2:
                    comparator = (n1, n2) -> n2 - n1;
                    break;
                case 3:
                    comparator = (n1, n2) -> getNumberOfDigits(n1) - getNumberOfDigits(n2);
                    break;
                case 4:
                    comparator = (n1, n2) -> getNumberOfDigits(n2) - getNumberOfDigits(n1);
                    break;
                default:
                    throw new InputMismatchException("ERROR: wrong order type");
            }

            SortingThread runnable = new SortingThread(Arrays.asList(array), comparator);
            Thread sortingThread = new Thread(runnable);
            sortingThread.start();
            sortingThread.join();

            List<Integer> ls = runnable.getArray();

            System.out.println("Sorted array: ");
            for (int i = 0; i < n; i++) {
                System.out.print(ls.get(i) + " ");
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    static int getNumberOfDigits(Integer number) {
        int t = number;
        int answer = 0;
        while (t != 0) {
            t /= 10;
            answer++;
        }
        return answer;
    }
}

class SortingThread implements Runnable {
    private List<Integer> array;
    private Comparator<Integer> comparator;
    private boolean isSorted = false;

    List<Integer> getArray() {
        return array;
    }

    SortingThread(List<Integer> array, Comparator<Integer> comparator) {
        this.array = array;
        this.comparator = comparator;
        this.isSorted = true;
    }

    @Override
    public void run() {
        try {
            array.sort(comparator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}