package com.example.demo;

public class Solution_12347894 {
    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        ListNode<Integer> listNodeListNode = new ListNode<>();
        while (head != null) {
            ListNode<Integer> current = new ListNode<>(head.val);
            listNodeListNode.prepend(current);
            listNodeListNode = current;
            head = head.next;
        }
        return listNodeListNode;
    }

    public static void main(String[] args) {
        ListNode<Integer> listNode = new ListNode<>(0);
        ListNode<Integer> listNode2 = listNode;
        for (int i = 1; i < 10; i++) {
            listNode2.append(new ListNode<>(i));
            listNode2 = listNode2.next;
        }
        ListNode<Integer> listNode3 = listNode;
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        ListNode<Integer> listNode1 = reverseList(listNode3);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }

    public static class ListNode<Integer> {
        Integer val;
        ListNode<Integer> next;

        ListNode(Integer val) {
            this.val = val;
            this.next = null;
        }

        ListNode() {
            this.val = null;
            this.next = null;
        }

        public void append(ListNode<Integer> next) {
            this.next = next;
        }

        public void prepend(ListNode<Integer> head) {
            head.next = this;
        }
    }

}
