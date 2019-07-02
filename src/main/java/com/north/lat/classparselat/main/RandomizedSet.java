package com.north.lat.classparselat.main;


import java.util.*;

public class RandomizedSet {
    Random random;
    List<Integer> contents;
    Map<Integer, Integer> indexMap;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        random = new Random(System.currentTimeMillis());
        contents = new ArrayList<Integer>(Integer.MAX_VALUE);
        indexMap = new HashMap<Integer, Integer>(Integer.MAX_VALUE);
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(indexMap.containsKey(val)){
            return false;
        }
        indexMap.put(val, contents.size());
        contents.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(indexMap.containsKey(val)){
            Integer inx = indexMap.get(val);
    /*        if(inx != contents.size() - 1){
               Integer lastOne = contents.get(contents.size() - 1);
               contents.set(inx, lastOne);
               indexMap.put(lastOne, inx);
            }*/
            contents.remove(inx);
            indexMap.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int size = contents.size();
        return contents.get(random.nextInt(size));
    }
}
