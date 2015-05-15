import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * 解题思路：利用hashmap存储key,value,并利用hashmap的查找速度；
 * 自定义双向链表，记录表头和表尾，方便删除操作；
 * 通过hashmap查找定位目标结点
 *
 * Created by xzbang on 2015/5/14.
 */
public class LRUCache {
    class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
            this.pre=null;
            this.next=null;
        }
    }
    HashMap<Integer, Node> cache;
    int capacity;
    int size;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<Integer, Node>();
        Node node = new Node(-1,-1);
        head=tail=node;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            fresh(node);
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        Node knode = cache.get(key);//先获取再判空可以节约时间，如果先判存在，再取，等于查找了两次，浪费时间
        if(knode!=null){
            knode.value=value;
            fresh(knode);
        }else{
            if(size == capacity){
                int tailKey = tail.key;
                tail=tail.pre;
                tail.next=null;
                cache.remove(tailKey);
                size--;
            }
            size++;
            Node node = new Node(key,value);
            node.pre=tail;
            tail.next=node;
            tail=node;
            cache.put(key,node);
            fresh(node);
        }
    }

    private void fresh(Node node) {
        Node nodePre = node.pre;
        Node nodeNext = node.next;
        if(nodePre == head)
            return;
        Node headNext = head.next;
        head.next = node;
        node.pre = head;
        node.next = headNext;
        headNext.pre = node;
        nodePre.next = nodeNext;
        if(nodeNext==null)
            tail=nodePre;
        else
            nodeNext.pre = nodePre;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(3,3);
        lruCache.set(4,4);
        lruCache.set(5,5);
        lruCache.set(3,6);
        System.out.println(lruCache.get(4));
        lruCache.set(8,7);
//        lruCache.link.remove(5);
        System.out.println(lruCache.get(4));
    }
}
