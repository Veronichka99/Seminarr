package Seminar2;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SingleToneTest {
    private static final int treadsNumber = 100;

    @Test
    public void getSingleTone() {
        ConcurrentSkipListSet<Integer> listSet = new ConcurrentSkipListSet<>();
        CountDownLatch startCdl = new CountDownLatch(treadsNumber);
        CountDownLatch endCdl = new CountDownLatch(treadsNumber);
        Executor executor = Executors.newFixedThreadPool(treadsNumber);
        for (int i = 0; i < treadsNumber; i++) {
            executor.execute(() -> {
                startCdl.countDown();
                try {
                    startCdl.await();
                } catch (InterruptedException e) {
                    fail();
                }
                SingleTone singleton = SingleTone.getSingleTone();
                listSet.add(singleton.getId());
                endCdl.countDown();
            });
        }
        try {
            endCdl.await();
        } catch (InterruptedException e) {
            fail();
        }

        assertEquals(1, listSet.size());
    }

}