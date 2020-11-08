package com.bsu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try(Scanner cin = new Scanner(System.in)) {
            System.out.println("Enter the number of elements in array: ");
            int n = Integer.parseInt(cin.nextLine());

            Integer[] array = new Integer[n];

            Random generator = new Random();
            for(int i=0;i<n;i++){
                array[i] = generator.nextInt(1000);
            }

            System.out.println("Size: " + n);
            for(int i=0;i<n;i++){
                System.out.print(array[i] + " ");
            }
            System.out.print(System.lineSeparator());

            System.out.println("Enter 1 if the sorting order should be ascending by order");
            System.out.println("Enter 2 if the sorting order should be descending by order");
            System.out.println("Enter 3 if the sorting order should be ascending by the number of digits");
            System.out.println("Enter 4 if the sorting order should be descending by the number of digits");

            int orderType = Integer.parseInt(cin.nextLine());
            Comparator<Integer> comparator;
            switch (orderType){
                case 1:
                    comparator = new SortByAscendingOrder();
                    break;
                case 2:
                    comparator = new SortByDescendingOrder();
                    break;
                case 3:
                    comparator = new SortByAscendingDigits();
                    break;
                case 4:
                    comparator = new SortByDescendingDigits();
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
            for(int i=0;i<n;i++){
                System.out.print(ls.get(i) + " ");
            }

        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }

    static int getNumberOfDigits(Integer number) {
        int t = number;
        int answer = 0;
        while(t != 0){
            t/=10;
            answer++;
        }
        return answer;
    }
}

class SortByAscendingOrder implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}

class SortByDescendingOrder implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
}

class SortByAscendingDigits implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Main.getNumberOfDigits(o1) - Main.getNumberOfDigits(o2);
    }
}

class SortByDescendingDigits implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Main.getNumberOfDigits(o2) - Main.getNumberOfDigits(o1);
    }
}


class SortingThread implements Runnable{
    private List<Integer> array;
    private Comparator<Integer> comparator;
    boolean isSorted = false;

    public List<Integer> getArray() {
        return array;
    }

    SortingThread(List<Integer> array, Comparator<Integer> comparator){
        this.array = array;
        this.comparator = comparator;
        this.isSorted = true;
    }

    @Override
    public void run() {
        try {
            array.sort(comparator);
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
}