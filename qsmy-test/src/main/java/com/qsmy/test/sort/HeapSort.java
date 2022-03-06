package com.qsmy.test.sort;

/**
 * @author qsmy
 */
public class HeapSort {

    public void sort(int[] arr) {
        int length = arr.length;
        for (int i = (length >> 1) - 1; i >= 0; i--) {
            adjustHeap(arr, i, length);
        }

        for (int i = length - 1; i > 0; i--) {
            swap(arr, i, 0);
            adjustHeap(arr, 0, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void adjustHeap(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int leftChild = parent << 1 + 1;
        int half = length >> 1;
        while (parent < half) {
            if (leftChild + 1 < length && arr[leftChild] < arr[leftChild + 1]) {
                leftChild++;
            }
            if (temp < arr[leftChild]) {
                arr[parent] = arr[leftChild];
                parent = leftChild;
                leftChild = parent << 1 + 1;
            } else {
                break;
            }
        }

    }
}
