package io.deeplay.camp.engine.entities;

public class Planet {
    private final int points;
    private final PlanetState state;
    private final Cell cell;

    public Planet(int points, PlanetState state, Cell cell) {
        this.points = points;
        this.cell = cell;
        this.state = state;
    }

    public Planet(int points, Cell cell) {
        this.points = points;
        this.cell = cell;
        this.state = PlanetState.FREE;
    }

    public int getPoints() {
        return points;
    }

    public Cell getCell() {
        return cell;
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
