package com.binchencoder.study.locks.wait;

import java.util.ArrayList;
import java.util.List;

public class Storage {

  private List<Object> foods;

  public final static int MAX_SIZE = 5;

  public Storage() {
    this.foods = new ArrayList<>();
  }

  public List<Object> getFoods() {
    return foods;
  }

  public void setFoods(List<Object> foods) {
    this.foods = foods;
  }
}
