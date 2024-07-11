package io.deeplay.camp.engine.entities;

public class Planet {
    private final int points;
    private final PlanetState state;

    public Planet(int points, PlanetState state) {
        this.points = points;
        this.state = state;
    }

    public Planet(int points) {
        this.points = points;
        this.state = PlanetState.FREE;
    }

    public int getPoints() {
        return points;
    }


    //Ship newShip, не придумали механизм, как отрабатывать с кораблем
    public enum PlanetState {
        IS_OCCUPIED {
            @Override
            public boolean isCaptureable() {
                return true;
            }
        },
        CAPTURED {
            @Override
            public boolean isCaptureable() {
                return false;
            }
        },
        FREE {
            @Override
            public boolean isCaptureable() {
                return true;
            }
        };

        public abstract boolean isCaptureable();
    }
}
