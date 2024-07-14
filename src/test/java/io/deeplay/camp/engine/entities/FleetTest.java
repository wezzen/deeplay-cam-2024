package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {
    private Fleet fleet;
    private Ship ship = new Ship(Ship.ShipType.BASIC);
    Cell position = new Cell(0, 0);
    ArrayList<Ship> shipList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        shipList.add(ship);
        fleet = new Fleet(shipList, position);
    }

    @Test
    void getFleetPower() {
        assertEquals(100, fleet.getFleetPower());
    }
    @Test
    void actualFleetPower0() {
        Ship basicShip = new Ship(Ship.ShipType.BASIC);
        fleet.actualFleetPower(basicShip);
        assertEquals(200, fleet.getFleetPower());
    }
    @Test
    void actualFleetPower1() {
        Ship basicShip = new Ship(Ship.ShipType.BASIC);
        shipList.add(basicShip);
        fleet.actualFleetPower(shipList);
        assertEquals(200, fleet.getFleetPower());
    }

    @Test
    void updateShipList() {
        ArrayList<Ship> tryShip = new ArrayList<>();
        Ship basicShip = new Ship(Ship.ShipType.BASIC);
        tryShip.add(ship);
        tryShip.add(basicShip);
        fleet.updateShipList(basicShip, true);
        assertEquals(tryShip, fleet.getShipList());
    }

    @Test
    void getShipList() {
        ArrayList<Ship> tryShip = new ArrayList<>();
        Ship basicShip = new Ship(Ship.ShipType.BASIC);
        tryShip.add(ship);
        tryShip.add(basicShip);
        fleet.updateShipList(basicShip, true);
        assertEquals(tryShip, fleet.getShipList());
    }

    @Test
    void getFleetPosition() {
        assertEquals(position, fleet.getFleetPosition());
    }

    @Test
    void setFleetPosition() {
        fleet.setFleetPosition(new Cell(2, 2));
        assertNotEquals(position, fleet.getFleetPosition());
    }
}
