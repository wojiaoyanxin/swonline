package com.sunlands.intl.yingshi.ui.my.handout.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yxin
 * @date 2020-03-03 - 13:08
 * @des
 */
public class MyHashMap<K, T> extends HashMap<K, T> {
    @Override
    public String toString() {
        String ans = "";
        for (Map.Entry<K, T> entry : entrySet()) {
            ans += entry.getKey() + ": " + entry.getValue().toString() + "\n";
        }
        return ans;
    }
}
