package leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * 解题思路：
 * 解法1：利用hashmap对新旧node做映射，以next为主线进行遍历
 * 解法2：将复制的节点暂存在旧表的相应节点的下一个，然后根据random简历链接，最后分拆两表
 *
 * Created by xzbang on 2015/5/16.
 */
public class CopyListwithRandomPointer {
    //Definition for singly-linked list with a random pointer.
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    //新旧node索引
    private HashMap<RandomListNode,RandomListNode> index = new HashMap<RandomListNode, RandomListNode>();

    public RandomListNode copyRandomList1(RandomListNode head) {
        if(head==null){
            return null;
        }
        RandomListNode now = head;
        RandomListNode newHead = null;
        while(now!=null){
            RandomListNode newNode = null;
            if(index.containsKey(now)){
                newNode = index.get(now);
            }else{
                newNode = new RandomListNode(now.label);
                index.put(now,newNode);
            }

            if(now.next==null){
                newNode.next=null;
            }else{
                RandomListNode newNodeNext = null;
                if(index.containsKey(now.next)){
                    newNodeNext = index.get(now.next);
                }else {
                    newNodeNext = new RandomListNode(now.next.label);
                    index.put(now.next,newNodeNext);
                }
                newNode.next = newNodeNext;
            }

            if(now.random==null){
                newNode.random=null;
            }else{
                RandomListNode newNodeRandom = null;
                if(index.containsKey(now.random)){
                    newNodeRandom = index.get(now.random);
                }else {
                    newNodeRandom = new RandomListNode(now.random.label);
                    index.put(now.random,newNodeRandom);
                }
                newNode.random = newNodeRandom;
            }

            if(now==head)
                newHead = newNode;

            now = now.next;
        }
        return newHead;
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }
}
