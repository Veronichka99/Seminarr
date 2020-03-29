package Seminar2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SingleTone {
    private static AtomicReference<SingleTone> INSTANCE = new AtomicReference<>();
    private final int id;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public int getId() {
        return id;
    }

    private SingleTone(int id) {
        this.id = id;
    }

    public static SingleTone getSingleTone() {
        INSTANCE.compareAndSet(null, new SingleTone(counter.getAndIncrement()));
        return INSTANCE.get();
    }


}