package com.binchencoder.study.sort;

import org.junit.Test;

public class SortTest {

  @Test
  public void sort(){
    int[] arr = new int[]{1, 100};

    BuketSort sort = new BuketSort();
    sort.sort(arr);
  }

}
