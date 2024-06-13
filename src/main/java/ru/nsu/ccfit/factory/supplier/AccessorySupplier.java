package ru.nsu.ccfit.factory.supplier;

import ru.nsu.ccfit.factory.detail.Accessory;
import ru.nsu.ccfit.factory.storage.Storage;

import java.util.UUID;

public final class AccessorySupplier extends DetailSupplier<Accessory> {

    public AccessorySupplier(Storage<Accessory> storage, int time) {
        super(storage, time);
    }

    @Override
    protected Accessory construct() {
        return new Accessory(UUID.randomUUID().toString().replace("-", ""));
    }
}
