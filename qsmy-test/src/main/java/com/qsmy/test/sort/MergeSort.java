package com.qsmy.test.sort;

import java.util.Arrays;

/**
 * @author qsmy
 */
public class MergeSort {

    public void mergeSort(int[] arr) {

        int length = arr.length;
        int[] temp = new int[length];
        sort(arr, 0, length - 1, temp);
    }

    private void sort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {
            int mid = left + ((right - left) >> 1);
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int index = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[index++] = arr[j++];
            } else {
                temp[index++] = arr[i++];
            }
        }

        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= right) {
            temp[index++] = arr[j++];
        }
        index = 0;
        while (left <= right) {
            arr[left++] = temp[index++];
        }
        System.out.println("------start-------");
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(temp));
        System.out.println("------end-------");
        temp = new int[10];
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[]{80, 30, 60, 40, 20, 10, 50, 70};
        mergeSort.mergeSort(arr);
    }
}
