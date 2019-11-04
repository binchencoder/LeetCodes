package com.binchencoder.study.locks.lock;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {

  public static void main(String[] args) {
    Data data = new Data();

    new Producer("producer-1", data).start();
    new Consumer("consumer-1", data).start();
    new Consumer("consumer-2", data).start();
    new Producer("producer-2", data).start();
  }
}

class Data {

  private List<Integer> data = Lists.newArrayList(1, 2, 3, 4, 5);

  private Random random = new Random();

  Lock lock = new ReentrantLock();
  Condition full = lock.newCondition();
  Condition empty = lock.newCondition();

  public void put() {
    lock.lock();
    lock.tryLock();

    try {
      if (data.size() > 5) {
        full.await();
        System.out.println(Thread.currentThread().getName() + ": 生产者已满");
      } else {
        data.add(random.nextInt(100));
        System.out.println(Thread.currentThread().getName() + ": 生产者生产1");
        empty.signal();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public void get() {
    lock.lock();

    try {
      if (data.size() < 1) {
        empty.await();
        System.out.println(Thread.currentThread().getName() + ": 消费者已空");
      } else {
        data.remove(0);
        System.out.println(Thread.currentThread().getName() + ": 消费者消费1");
        full.signal();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

/**
 * 生产者
 */
class Producer extends Thread {

  private Data data;

  public Producer(String threadName, Data data) {
    this.setName(threadName);
    this.data = data;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      data.put();
    }
  }
}

/**
 * 消费者
 */
class Consumer extends Thread {

  private Data data;

  public Consumer(String threadName, Data data) {
    this.setName(threadName);
    this.data = data;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      data.get();
    }
  }
}
