package ru.nsu.ccfit.factory.controller;

import ru.nsu.ccfit.factory.detail.Auto;
import ru.nsu.ccfit.factory.supplier.DetailSupplier;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.*;

@AllArgsConstructor
public class AutoController implements Runnable {
    private final Storage<Auto> storage;
    private final ThreadPoolExecutor workersThreadPool;
    private final DetailSupplier<Auto> autoDetailSupplier;

    @SneakyThrows
    @Override
    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (storage) {
                int sizeDifference = workersThreadPool.getCorePoolSize() - storage.getDetailsQueue().size();
                if (sizeDifference > 0) {
                    for (int i = 0; i < sizeDifference; i++) {
                        workersThreadPool.submit(autoDetailSupplier);
                    }
                    storage.notifyAll();
                }
            }
            wait();
        }
    }
}
