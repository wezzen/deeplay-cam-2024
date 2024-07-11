package io.deeplay.camp.engine.Ships;

import io.deeplay.camp.engine.entities.Fleet;
import io.deeplay.camp.engine.entities.Move;
import io.deeplay.camp.engine.entities.Ship;

import java.util.Collection;

public class BasicShip extends Ship {
    protected BasicShip(Fleet fleetIncome) {
        super(ShipType.BASIC, fleetIncome);
    }

    public BasicShip() {
        super(ShipType.BASIC);
    }

    @Override
    public Ship replenishFleet(Fleet fleet) {
        return null;
    }

    @Override
    public Collection<Move> calculateLegalReplenish() {
        return null;
    }
}
