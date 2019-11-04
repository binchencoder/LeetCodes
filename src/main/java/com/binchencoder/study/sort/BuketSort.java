package com.chenbin.study.sort;

import java.util.Arrays;

public class BuketSort {

  public int[] sort(int[] sourceArry) {
    // 对array进行拷贝，不改变参数内容
    int[] arr = Arrays.copyOf(sourceArry, sourceArry.length);

    return buketSort(arr, 5);
  }

  private int[] buketSort(int[] arr, int buketSize) {
    if (arr.length == 0) {
      return arr;
    }

    int minValue = arr[0];
    int maxValue = arr[0];
    for (int val : arr) {
      if (val < minValue) {
        minValue = val;
      } else if (val > maxValue) {
        maxValue = val;
      }
    }

    int buketCnt = (int) Math.floor((maxValue - minValue) / buketSize) + 1;
    int[][] bukets = new int[buketCnt][0];

    // 利用映射函数将数据分配到各个桶中
    for (int i = 0; i < arr.length; i++) {
      int index = (int) Math.floor((arr[i] - minValue) / buketSize);
      bukets[index] = arrAppend(bukets[index], arr[i]);
    }

    return arr;
  }

  private int[] arrAppend(int[] arr, int value) {
    arr = Arrays.copyOf(arr, arr.length + 1);
    arr[arr.length - 1] = value;
    return arr;
  }

}
