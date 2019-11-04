package com.binchencoder.study.signal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by chenbin on 2018/5/27.
 */
public class SignalHandlerExample {

  public static void main(String[] args) {
    System.out.println("Signal handling example.");
    SignalHandler handler = new MySignalHandler();
    // kill命令
    Signal termSignal = new Signal("TERM");
    Signal.handle(termSignal, handler);
    // ctrl+c命令
    Signal intSignal = new Signal("INT");
    Signal.handle(intSignal, handler);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      System.out.println("Interrupted: " + e.getMessage());
    }
  }
}

class MySignalHandler implements SignalHandler {

  @Override
  public void handle(Signal signal) {
    System.out.println("Signal handler called for signal " + signal);
    try {
      System.out.println("Handling " + signal.getName());
      if (signal.getName().equals("INT")) {
        System.exit(1);
      }
    } catch (Exception e) {
      System.out.println("handle|Signal handler" + "failed, reason "
          + e.getMessage());
      e.printStackTrace();
    }
  }
}
