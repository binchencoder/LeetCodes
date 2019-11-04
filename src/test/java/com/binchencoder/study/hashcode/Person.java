package com.binchencoder.study.hashcode;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by chenbin on 18-2-7.
 */
public class Person {

  private String name;

  private int age;

  private Sex sex;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(name).append(age).append(sex).toHashCode();
  }
}
