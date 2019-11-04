package com.binchencoder.study.locks.lock;

public class DeadLock {

  public static void main(String[] args) {
    Thread t1 = new Thread(new DeadLockRun(false), "DeadLock1");
    Thread t2 = new Thread(new DeadLockRun(true), "DeadLock2");

    t1.start();
    t2.start();
  }
}

class DeadLockRun implements Runnable {

  boolean lockFormer;

  static Object o1 = new Object();
  static Object o2 = new Object();

  public DeadLockRun(boolean lockFormer) {
    this.lockFormer = lockFormer;
  }

  @Override
  public void run() {
    if (this.lockFormer) {
      synchronized (o1) {
        System.out.println(Thread.currentThread().getName() + ": lock o1");
        try {
          Thread.sleep(500);
        } catch (Exception e) {
          e.printStackTrace();
        }

        synchronized (o2) {
          System.out.println(Thread.currentThread().getName() + ": lock o2");
        }
      }
    } else {
      synchronized (o2) {
        System.out.println(Thread.currentThread().getName() + ": lock o2");
        try {
          Thread.sleep(500);
        } catch (Exception e) {
          e.printStackTrace();
        }

        synchronized (o1) {
          System.out.println(Thread.currentThread().getName() + ": lock o1");
        }
      }
    }
  }
}
