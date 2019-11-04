package com.binchencoder.study.stream;

/**
 * Created by chenbin on 18-5-3.
 */
public class Stream {

  public long id;

  public String name;

  public Stream() {
  }

  public Stream(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
