package ru.nsu.ccfit.factory.detail;

public final class Auto extends Detail {
    private final Accessory accessory;
    private final Body body;
    private final Engine engine;

    public Auto(String id, Accessory accessory, Body body, Engine engine) {
        super(id);
        this.accessory = accessory;
        this.body = body;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Auto(id=" + id +
                "): (Body(id=" + body.getId() +
                "), Engine(id=" + engine.getId() +
                "), Accessory(id=" + accessory.getId() +
                "))";
    }
}
