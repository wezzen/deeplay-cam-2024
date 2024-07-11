package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.Ships.BasicShip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    private Fleet fleet;
    int[] startPositions = new int[]{0, 0};
    ArrayList<Ship> shipList = new ArrayList<>();
    BasicShip basicShip = new BasicShip();

    @BeforeEach
    void setUp() {
        shipList.add(basicShip);
        fleet = new Fleet(shipList, startPositions);
    }

    @Test
    void getFleetPosition0() {
        assertArrayEquals(new int[]{0, 0}, fleet.getFleetPosition(), "Starting position is 0, 0");
    }
    @Test
    void getFleetPosition1() {
        assertArrayEquals(startPositions, fleet.getFleetPosition(), "Starting position is 0, 0");
    }

    @Test
    void getFleetPower() {
        assertEquals(100, fleet.getFleetPower());
    }

    @Test
    void actualFleetPower0() {
        BasicShip basicShip = new BasicShip();
        fleet.actualFleetPower(basicShip);
        assertEquals(200, fleet.getFleetPower());
    }

    @Test
    void actualFleetPower1() {
        BasicShip basicShip = new BasicShip();
        shipList.add(basicShip);
        fleet.actualFleetPower(shipList);
        assertEquals(200, fleet.getFleetPower());
    }

    @Test
    void updateShipList() {
        ArrayList<Ship> tryShip = new ArrayList<Ship>();
        BasicShip basicShip1 = new BasicShip();
        tryShip.add(basicShip);
        tryShip.add(basicShip1);
        fleet.updateShipList(basicShip1);
        assertEquals(tryShip, fleet.getShipList());
    }
}
