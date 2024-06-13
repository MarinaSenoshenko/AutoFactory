package ru.nsu.ccfit.factory.supplier;

import lombok.AllArgsConstructor;
import ru.nsu.ccfit.factory.detail.Detail;
import ru.nsu.ccfit.factory.storage.Storage;

@AllArgsConstructor
public abstract class DetailSupplier<T extends Detail> implements Runnable {
    protected final Storage<T> storage;
    protected int time;

    protected abstract T construct() throws InterruptedException;

    @Override
    public final void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(time);
                storage.add(construct());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

