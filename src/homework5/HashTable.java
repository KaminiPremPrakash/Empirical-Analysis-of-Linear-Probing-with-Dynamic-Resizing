/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework5;

import java.util.Random;

/**
 *
 * @author Kamini Prakash
 */
public class HashTable<Key, Value> {

    private int count;
    private int maxCapacity;
    private Key[] keys;
    private Value[] vals;
    private long startTime;
    private long endTime;
    private long resizeStart;
    private long resizeEnd;

    public HashTable(int capacity) {
        maxCapacity = capacity;
        count = 0;
        keys = (Key[]) new Object[maxCapacity];
        vals = (Value[]) new Object[maxCapacity];
    }

    //0x7fffffff is all 1's except the signed bit
    //will result is positive integer
    private int hash(Key key) {
        int hashValue = (key.hashCode() & 0x7fffffff) % maxCapacity;
        return hashValue;
    }

    public void resize(int capacity) {
        System.out.println("----------------Doubling the hashtable capacity : ----------------");
        System.out.println("\n");
        HashTable<Key, Value> ht = new HashTable<Key, Value>(capacity);
        //  resizeStart = System.currentTimeMillis();
        for (int i = 0; i < maxCapacity; i++) {
            if (keys[i] != null) {
                ht.insert(keys[i], vals[i]);
            }
        }
        // resizeEnd = System.currentTimeMillis();
        keys = ht.keys;
        vals = ht.vals;
        maxCapacity = ht.maxCapacity;
        // getInsertionTimeAfterResizing(resizeStart, resizeEnd);
    }

    public void insert(Key key, Value val) {
//        int i;
//        //to check if key already exits
//        for (i = hash(key); keys[i] != null; i = (i + 1) % maxCapacity) {
//            //if already present the value is updated
//            if (keys[i].equals(key)) {
//                vals[i] = val;
//                return;
//            }
//        }
//
//        keys[i] = key;
//        vals[i] = val;
        if (count >= maxCapacity / 2) {
            resize(2 * maxCapacity);
        }

        int i;
        //to check if key already exits
        for (i = hash(key); keys[i] != null; i = (i + 1) % maxCapacity) {
            //if already present the value is updated
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        count = count + 1;

    }

    public void getInsertionTimeAfterResizing(long sta, long sto) {
        System.out.println("Time taken for Insertion after Resizing : " + (sto - sta) + "ms");
    }

    public void printHashTable() {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxCapacity; i++) {
            if (keys[i] != null) {
                System.out.println("Key " + keys[i] + " Value " + vals[i]);
            }
        }
        System.out.println();
    }

    public void getResult(long endT) {
        System.out.println("Number of buckets used " + count);
        System.out.println("Table size " + maxCapacity);
        System.out.println("Load factor : " + ((double) count / (double) maxCapacity));
        System.out.println("Total time of insertion : " + (endT - startTime) + "ms");
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int hashSize = 400;
        int numOfElements = 400;
        HashTable hs = new HashTable(hashSize);
        hs.startTime = System.currentTimeMillis();
        Random randomInt = new Random();
        int key;
        int value;

        for (int i = 0; i < numOfElements; i++) {
            key = randomInt.nextInt(numOfElements);
            // System.out.println(key);
            value = randomInt.nextInt(numOfElements);
            //System.out.println(value);
            hs.insert(key, value);
            hs.endTime = System.currentTimeMillis();
            hs.getResult(hs.endTime);
        }
        //  hs.resize(2 * hashSize);
        //  hs.printHashTable();
    }
}
