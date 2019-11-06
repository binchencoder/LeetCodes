package com.binchencoder.interview.bytedance;

import java.util.Stack;

/**
 * Created by chenbin at 2019/11/04
 *
 * 题目内容来源: ByteDance/bytedance20191104-2.jpg
 */
public class ByteDanceLinkedList {

  static int nodeLength(ListNode node) {
    int length = 0;

    ListNode temp = node;
    while (temp != null) {
      length++;
      temp = temp.next;
    }

    return length;
  }

  private static ListNode calc(ListNode a, ListNode b, int aLength, int bLength) {
    ListNode aNode = a;
    ListNode bNode = b;

    ListNode result = new ListNode();
    ListNode resultNode = result;

    // 计算b链表再a中的起始索引
    int aStartIndex = aLength - bLength;

    for (int i = 0; i < aLength; i++) {
      if (i >= aStartIndex) {
        resultNode.val = aNode.val + bNode.val;
        bNode = bNode.next;
      } else {
        resultNode.val = aNode.val;
      }

      aNode = aNode.next;
      if (aNode != null) {
        resultNode.next = new ListNode();
        resultNode = resultNode.next;
      }
    }

    return result;
  }

  public static ListNode addition(ListNode a, ListNode b) {
    ListNode result;

    // 计算位数
    int aLength = nodeLength(a);
    int bLength = nodeLength(b);

    if (aLength > bLength) {
      result = calc(a, b, aLength, bLength);
    } else {
      result = calc(b, a, bLength, aLength);
    }

    boolean isGreater9 = true;
    while (isGreater9) {
      isGreater9 = false;
      ListNode node = result;

      while (node != null) {
        // 检查是否有大于9的节点
        if (node.val > 9) {
          isGreater9 = true;
          break;
        }

        node = node.next;
      }

      // 没有大于9且需要进位的节点
      if (!isGreater9) {
        break;
      }

      node = result;
      if (node.val > 9) {
        // 头节点的内容跟大于9，需要进位
        result = new ListNode(1, node);

        node.val = node.val - 10;
      }

      while (node.next != null) {
        if (node.next.val > 9) {
          node.val += 1;
          node.next.val = node.next.val - 10;
        }
        node = node.next;
      }
    }

    return result;
  }

  // TODO(chenbin) I'll implements it myself
  static ListNode addTwoNums(ListNode n1, ListNode n2) {
    int n1Len = nodeLength(n1);
    int n2Len = nodeLength(n2);

    ListNode n1Temp = n1;
    ListNode n2Temp = n2;
    int diff;

    ListNode temp;
    if (n1Len > n2Len) {
      diff = n1Len - n2Len;

      n2Temp = new ListNode(0);
      temp = n2Temp;
      while (diff > 1) {
        n2Temp.next = new ListNode(0);
        diff--;
        break;
      }
      n2Temp.next = n2;
    } else {
      diff = n2Len - n1Len;
      n1Temp = new ListNode(0);
      while (diff > 1) {
        n1Temp.next = new ListNode(0);

      }
    }

    Stack stack1 = new Stack();

    return null;
  }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(1);
    n1.next = new ListNode(3);
    n1.next.next = new ListNode(5);
    n1.next.next.next = new ListNode(8);

    ListNode n2 = new ListNode(6);
    n2.next = new ListNode(6);

    ListNode result = addition(n1, n2);
  }

}

class ListNode {

  int val;

  ListNode next;

  public int getVal() {
    return val;
  }

  public void setVal(int val) {
    this.val = val;
  }

  public ListNode getNext() {
    return next;
  }

  public void setNext(ListNode next) {
    this.next = next;
  }

  public ListNode() {
  }

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}