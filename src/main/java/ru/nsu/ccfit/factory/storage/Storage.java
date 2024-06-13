package ru.nsu.ccfit.factory.storage;

import lombok.Getter;
import ru.nsu.ccfit.factory.detail.Detail;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
public class Storage<T extends Detail> {
    private final int limit;
    private final Queue<T> detailsQueue;

    public Storage(int limit) {
        this.limit = limit;
        this.detailsQueue = new ArrayDeque<>(limit);
    }

    public synchronized T poll() throws InterruptedException {
        T detail;
        while ((detail = detailsQueue.poll()) == null) {
            wait();
        }
        notify();
        return detail;
    }

    public synchronized void add(T detail) throws InterruptedException {
        while (detailsQueue.size() >= limit) {
            wait();
        }
        detailsQueue.add(detail);
        notify();
    }
}
