package ru.nsu.ccfit.factory.storage;

import ru.nsu.ccfit.factory.detail.Accessory;
import ru.nsu.ccfit.factory.detail.Auto;
import ru.nsu.ccfit.factory.detail.Body;
import ru.nsu.ccfit.factory.detail.Engine;

public record DetailsStorage(
        Storage<Accessory> accessoryStorage,
        Storage<Body> bodyStorage,
        Storage<Engine> engineStorage,
        Storage<Auto> autoStorage
) {
}
