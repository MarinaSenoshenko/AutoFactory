package ru.nsu.ccfit.factory;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.factory.controller.AutoController;
import ru.nsu.ccfit.factory.detail.Auto;
import ru.nsu.ccfit.factory.storage.Storage;


@AllArgsConstructor
@Slf4j
public class Dealer implements Runnable {
    private final Storage<Auto> autoStorage;
    private final AutoController autoController;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Auto auto = autoStorage.poll();
                synchronized (autoController) {
                    autoController.notify();
                }
                log.info("Dealer {} sold: {}", Thread.currentThread().getId(), auto.toString());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
