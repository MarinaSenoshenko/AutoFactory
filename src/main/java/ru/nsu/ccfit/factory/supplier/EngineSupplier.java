package ru.nsu.ccfit.factory.supplier;

import ru.nsu.ccfit.factory.detail.Engine;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.UUID;

public final class EngineSupplier extends DetailSupplier<Engine> {

    public EngineSupplier(Storage<Engine> storage, int time) {
        super(storage, time);
    }

    @Override
    protected Engine construct() {
        return new Engine(UUID.randomUUID().toString().replace("-", ""));
    }
}
