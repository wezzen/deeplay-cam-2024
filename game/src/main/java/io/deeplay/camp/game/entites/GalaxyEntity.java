package io.deeplay.camp.game.entites;

import java.util.concurrent.atomic.AtomicLong;

public class GalaxyEntity {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private final long id;

    public GalaxyEntity() {
        this.id = idGenerator.getAndIncrement();
    }

    public long getId() {
        return id;
    }
}
