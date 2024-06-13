package ru.nsu.ccfit.factory.supplier;

import ru.nsu.ccfit.factory.detail.Accessory;
import ru.nsu.ccfit.factory.detail.Auto;
import ru.nsu.ccfit.factory.detail.Body;
import ru.nsu.ccfit.factory.detail.Engine;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.UUID;

public final class AutoConstructor extends DetailSupplier<Auto> {
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;

    public AutoConstructor(Storage<Accessory> accessoryStorage, Storage<Body> bodyStorage,
                           Storage<Engine> engineStorage, Storage<Auto> autoStorage, int time) {
        super(autoStorage, time);
        this.accessoryStorage = accessoryStorage;
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
    }

    @Override
    protected Auto construct() throws InterruptedException {
        return new Auto(UUID.randomUUID().toString().replace("-", ""),
                accessoryStorage.poll(), bodyStorage.poll(), engineStorage.poll());
    }
}
