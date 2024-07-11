package io.deeplay.camp.engine.entities;

public class Planet {
    private final int points;
    private final PlanetState state;
    private final int[] coordinates;

    public Planet(int points, PlanetState state, int[] coordinates) {
        this.points = points;
        this.coordinates = coordinates;
        this.state = state;
    }
    public Planet(int points, int[] coordinates) {
        this.points = points;
        this.coordinates = coordinates;
        this.state = PlanetState.FREE;
    }

    public int getPoints() {
        return points;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    //Ship newShip, не придумали механизм, как отрабатывать с кораблем
    public enum PlanetState {
        IS_OCCUPIED {
            @Override
            public boolean isСaptureable() {
                return true;
            }
        },
        CAPTURED{
            @Override
            public boolean isСaptureable() {
                return false;
            }
        },
        FREE{
            @Override
            public boolean isСaptureable() {
                return true;
            }
        };
        public abstract boolean isСaptureable();
    }
}
