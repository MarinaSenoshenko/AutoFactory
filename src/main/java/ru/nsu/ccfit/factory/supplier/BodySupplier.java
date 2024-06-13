package ru.nsu.ccfit.factory.supplier;

import ru.nsu.ccfit.factory.detail.Body;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.UUID;

public final class BodySupplier extends DetailSupplier<Body> {

    public BodySupplier(Storage<Body> storage, int time) {
        super(storage, time);
    }

    @Override
    protected Body construct() {
        return new Body(UUID.randomUUID().toString().replace("-", ""));
    }
}
