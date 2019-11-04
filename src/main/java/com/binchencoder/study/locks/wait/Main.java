package com.binchencoder.study.locks.wait;

public class Main {

  public static void main(String[] args) {
    Object lock = new Object();
    Storage storage = new Storage();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
    new Thread(new Customer(storage, lock)).start();
    new Thread(new Producer(storage, lock)).start();
  }
}
