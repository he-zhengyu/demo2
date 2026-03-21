package com.example.demo;

public class Main_478307 {

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

        // 构建链表 1 -> 2 -> 3 -> 4 -> 5 -> null
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // 反转链表
        Node reversed = reverseNodeList(head);

        // 输出
        System.out.print("null ");
        while (reversed != null) {
            System.out.print(reversed.val + " ");
            reversed = reversed.next;
        }
    }

    private static Node reverseNodeList(Node head) {
        Node reversed = null;
        Node tmp;
        while (head != null) {
            tmp = reversed;
            reversed = head;
            head = head.next;
            reversed.next = tmp;

        }
        return reversed;
    }
}