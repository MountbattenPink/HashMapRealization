package com.task.bigdata;

import java.util.Arrays;

/**
 * @author Olha Shekera
 * This HashMap realization deals with int as keys
 * and String as values, however, native Java HashMap takes only
 * an object as key (in this occurrence - Integer).
 */

public class OwnHashMap {
    private int [] keys;
    private String [] values;
    private int size;
    //maximum possible size of this hashmap
    private static final int CAPABILITY = 256;
    private final int EMPTY_KEY = Integer.MIN_VALUE;


    public OwnHashMap() {
        keys = new int [CAPABILITY];
        values = new String[CAPABILITY];
        //mark all element in table as empty
        Arrays.fill(keys, EMPTY_KEY);
        }

    /*
        returns amount of existing pairs <key, value>
     */
    public int size(){
        return size;
    }

    /*  returns value by input key or null if key doesn't exist
     */
    public String get (int key){
        int index=getIndexByKey(key);
        int collision=0;
        while ( collision < CAPABILITY-index)
        {
            if (index+collision >= CAPABILITY){
                    index=0;
                    collision=0;
            }
            if (keys[index+collision]!=key) collision++;
            else return values[index+collision];
        }
        return null;
    }

    /*  as JDK HashMap.put() this method adds pair <key, value> to ownHashMap if pair with the same
        key doesn't exist yet or replace value by key if this key already exists in ownHashMap.
        returns previous value mapped to the key or null if key is new
     */
    public String put (int key, String value){
        if (size == CAPABILITY)return null;
       for ( int index=getIndexByKey(key);;index++)
        {
            if (index == CAPABILITY-1)index = 0;
            if ((keys[index] == EMPTY_KEY)) {
                keys[index] = key;
                values[index] = value;
                size++;
                return null;
            }
            else if (keys[index] == key){
                String prevVal=values[index];
                values[index] = value;
                return prevVal;
            }
        }
    }

    /*  function for key hashing
     */
    private static final int hashKey(int key){
        //Knuth's multiplicative method
        return (int) ((key * 2654435761L) % (Math.pow(2, 32)));
    }

    private static int getIndexByHash(int hash) {
        return Math.abs(hash)%CAPABILITY;
    }

    private static int getIndexByKey(int key) {
        return getIndexByHash(hashKey(key));
    }
}
