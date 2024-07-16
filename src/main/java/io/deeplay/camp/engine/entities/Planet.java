package io.deeplay.camp.engine.entities;

import java.util.Objects;

/**
 * Класс планеты
 */
public class Planet {
    public final int points;

    public Planet(final int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet planet)) return false;
        return points == planet.points;
    }


    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

}

