package com.binchencoder.study.locks.wait;

import java.util.Random;

/**
 * 生产者
 */
public class Customer implements Runnable {

  private Storage storage;

  private Object lock;

  public Customer(Storage storage, Object lock) {
    super();
    this.storage = storage;
    this.lock = lock;
  }

  @Override
  public void run() {
    Random random = new Random();
    while (true) {
      int sleepTime = random.nextInt(1010);
      System.out.println("消费者sleep " + sleepTime + " millis");
      try {
        Thread.sleep(sleepTime);
//        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (lock) {
        while (storage.getFoods().size() <= 0) {
          try {
            System.out.println("货物已空，提示生产者生产");
            lock.wait();  //当前线程在lock上等待，并释放锁
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        storage.getFoods().remove(0);
        lock.notifyAll(); //唤醒消费者与生产者
        System.out.println(
            "消费者消费1, " + Thread.currentThread().getName() + ", 余量：" + storage.getFoods().size());
      }
    }
  }
}
