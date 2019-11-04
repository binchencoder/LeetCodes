package com.binchencoder.study.sample;

import org.testng.annotations.Test;

/**
 * Created by chenbin on 18-6-6.
 */
public class IPConvert {

  /**
   * 通过左移位操作（<<）给每一段的数字加权
   * 第一段的权为2的24次方
   * 第二段的权为2的16次方
   * 第三段的权为2的8次方
   * 最后一段的权为1
   */
  @Test
  public void ipToInt() {
    String ipStr = "192.168.64.56";
    String[] ips = ipStr.split("\\.");

    System.out.println(String.join("", ips));
    int ip = (Integer.parseInt(ips[0]) << 24) + (Integer.parseInt(ips[1]) << 16)
        + (Integer.parseInt(ips[2]) << 8) + Integer.parseInt(ips[3]);
    System.out.print(ip);
  }

  /**
   * 将整数值进行右移位操作（>>）
   * 右移24位，右移时高位补0，得到的数字即为第一段IP
   * 右移16位，右移时高位补0，得到的数字即为第二段IP
   * 右移8位，右移时高位补0，得到的数字即为第三段IP
   * 最后一段的为第四段IP
   */
  @Test
  public void intToIp() {
    int ip = 1921687566;
    String ipStr =  ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."
        + ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    System.out.print(ipStr);
  }
}
