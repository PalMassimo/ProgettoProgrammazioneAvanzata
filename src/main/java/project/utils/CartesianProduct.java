/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A class that build a Cartesian Product
 */
public class CartesianProduct<K, V> {

    Set<Map<K, V>> tuples = new HashSet<>();

    public Set<Map<K, V>> getTuples() {
        return tuples;
    }

    public CartesianProduct(Map<K, V[]> inputMap) {
        build(inputMap);
    }

    public void build(Map<K, V[]> inputMap) {

        for (K key : inputMap.keySet()) {
            if (tuples.isEmpty()) {
                for (V value : inputMap.get(key)) {
                    Map<K, V> map = new HashMap<>();
                    map.put(key, value);
                    this.tuples.add(map);
                }
            } else {

                Set<Map<K, V>> outerSet = new HashSet<>();

                for (V value : inputMap.get(key)) {
                    Set<Map<K, V>> innerSet = getTuplesCopy();

                    for (Map<K, V> innerMap : innerSet) {
                        innerMap.put(key, value);
                    }

                    outerSet.addAll(innerSet);
                }
                this.tuples = outerSet;
            }

        }
    }

    private Set<Map<K, V>> getTuplesCopy() {
        Set<Map<K, V>> copySet = new HashSet<>();

        for (Map<K, V> map : tuples) {
            Map<K, V> copyMap = new HashMap<>();
            for (K key : map.keySet()) {
                copyMap.put(key, map.get(key));
            }
            copySet.add(copyMap);
        }

        return copySet;
    }
}
