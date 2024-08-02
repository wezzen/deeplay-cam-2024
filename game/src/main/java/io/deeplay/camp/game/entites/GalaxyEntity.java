package io.deeplay.camp.game.entites;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalaxyEntity that = (GalaxyEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
