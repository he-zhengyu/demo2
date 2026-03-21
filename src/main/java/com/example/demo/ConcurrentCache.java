package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentCache<K, V>  {


    public static void main(String[] args) {

        String string = "了ds";
        string.toCharArray();
        string.codePoints();
        System.out.println(string.toCharArray().length);
        System.out.println(string.length());
    }
}

interface Cache<K, V> {
    V get(K key);
}

interface LoadingFunction<K, V> {
    V apply(K key);
}