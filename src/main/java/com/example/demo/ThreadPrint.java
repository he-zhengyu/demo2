package com.example.demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.*;

public class ThreadPrint {
    static Lock lock = new ReentrantLock();
    static Queue<Character> queue = new LinkedList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'));
    static volatile boolean needToWait = true;

    public static void main(String[] args) {
        Condition conditionMain = lock.newCondition();
        Condition condition_1 = lock.newCondition();
        Condition condition_2 = lock.newCondition();
        Condition condition_3 = lock.newCondition();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> printOnConditions(conditionMain, condition_1, condition_2));
        executor.execute(() -> printOnConditions(conditionMain, condition_2, condition_3));
        executor.execute(() -> printOnConditions(conditionMain, condition_3, condition_1));
        Future<?> future1 = executor.submit(() -> printOnConditions(conditionMain, condition_3, condition_1));
        Future<?> future2 = executor.submit(() -> printOnConditions(conditionMain, condition_3, condition_1));
        Future<?> future3 = executor.submit(() -> printOnConditions(conditionMain, condition_3, condition_1));

        lock.lock();
        needToWait = false;
        try {
            conditionMain.await();
        } catch (InterruptedException e) {
            System.out.println("main thread was interrupted");
        } finally {
            condition_1.signal();
            lock.unlock();
            future1.isDone();
        }
    }

    private static void printOnConditions(Condition conditionMain, Condition condition_wait, Condition condition_signal) {
        while (needToWait) {
            Thread.onSpinWait();
        }
        lock.lock();
        conditionMain.signal();
        try {
            while (true) {
                condition_wait.await();
                if (queue.peek() == null) {
                    condition_signal.signal();
                    break;
                }
                System.out.println(Thread.currentThread().threadId() + " :  " + queue.poll());
                condition_signal.signal();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}