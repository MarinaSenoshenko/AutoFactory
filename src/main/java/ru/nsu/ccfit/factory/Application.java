package ru.nsu.ccfit.factory;

import ru.nsu.ccfit.factory.controller.AutoController;
import ru.nsu.ccfit.factory.storage.DetailsStorage;
import ru.nsu.ccfit.factory.supplier.AccessorySupplier;
import ru.nsu.ccfit.factory.supplier.AutoConstructor;
import ru.nsu.ccfit.factory.supplier.BodySupplier;
import ru.nsu.ccfit.factory.supplier.EngineSupplier;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Application {
    private final Map<String, Integer> values = PropertiesReader.getValues();
    private final ThreadPoolExecutor workersPool;
    private final ExecutorService dealersPool;
    private final ExecutorService accessoryPool;
    private final DetailsStorage detailsStorage =
            new DetailsStorage(new Storage<>(values.get("ACCESSORY_LIMIT")),
                    new Storage<>(values.get("BODY_LIMIT")),
                    new Storage<>(values.get("ENGINE_LIMIT")),
                    new Storage<>(values.get("AUTO_LIMIT")));


    public Application() {
        workersPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(values.get("WORKERS_COUNT"));
        dealersPool = Executors.newFixedThreadPool(values.get("DEALERS_COUNT"));
        accessoryPool = Executors.newFixedThreadPool(values.get("ACCESSORY_COUNT"));
    }

    public void run() {
        try {
            AutoController autoController = new AutoController(detailsStorage.autoStorage(), workersPool,
                    new AutoConstructor(detailsStorage.accessoryStorage(), detailsStorage.bodyStorage(), detailsStorage.engineStorage(), detailsStorage.autoStorage(), values.get("AUTO_TIME")));

            Thread controllerThread = new Thread(autoController);
            Thread bodySupplierThread = new Thread(new BodySupplier(detailsStorage.bodyStorage(), values.get("BODY_TIME")));
            Thread engineSupplierThread = new Thread(new EngineSupplier(detailsStorage.engineStorage(), values.get("ENGINE_TIME")));

            IntStream.range(0, values.get("DEALERS_COUNT")).forEach(v ->
                    accessoryPool.submit(new AccessorySupplier(detailsStorage.accessoryStorage(), values.get("ACCESSORY_TIME")))
            );
            bodySupplierThread.start();
            engineSupplierThread.start();
            controllerThread.start();

            IntStream.range(0, values.get("DEALERS_COUNT")).forEach(v ->
                    dealersPool.submit(new Dealer(detailsStorage.autoStorage(), autoController))
            );
        } catch (Exception ignored) {}
    }
}
