package com.binchencoder.study.locks.synchronize;

public class Service {

  public void mSleep() {
    synchronized (this) {
      try {
//        System.out.println("Sleep, 当前时间：" + System.currentTimeMillis());
        Thread.sleep(3 * 1000);

        this.notifyAll();
        System.out.println(" 唤醒等待 。 结束时间：" + System.currentTimeMillis());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void mWait() {
    synchronized (this) {
      try {
//      System.out.println("Wait, 结束时间：" + System.currentTimeMillis());

        System.out.println("before wait, 等待开始 。 当前时间：" + System.currentTimeMillis());
        this.wait();
        System.out.println("after wait, 当前时间：" + System.currentTimeMillis());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
