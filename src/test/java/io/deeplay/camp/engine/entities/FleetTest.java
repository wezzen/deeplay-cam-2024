package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void testFleetCreation() {
        assertNotNull(fleet);
        assertEquals(position, fleet.getFleetPosition());
        assertEquals(1, fleet.getShipList().size());
        assertEquals(100, fleet.getFleetPower());
    }

    @Test
    public void testUpdateShipListWithFleet() {
        ArrayList<Ship> newShipList = new ArrayList<>();
        newShipList.add(new Ship(Ship.ShipType.BASIC));
        newShipList.add(new Ship(Ship.ShipType.BASIC));

        Fleet newFleet = new Fleet(newShipList, new Cell(6, 6));
        fleet.updateShipList(newFleet, true);

        assertEquals(3, fleet.getShipList().size());
        assertEquals(300, fleet.getFleetPower());
    }

    @Test
    public void testUpdateShipListWithNewShips() {
        ArrayList<Ship> newShipList = new ArrayList<>();
        newShipList.add(new Ship(Ship.ShipType.BASIC));
        newShipList.add(new Ship(Ship.ShipType.BASIC));

        fleet.updateShipList(newShipList, true);
        assertEquals(3, fleet.getShipList().size());
        assertEquals(300, fleet.getFleetPower());
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

    @Test
    public void testUpdateShipListWithSingleShip() {
        Ship newShip = new Ship(Ship.ShipType.BASIC);

        fleet.updateShipList(newShip, true);
        assertEquals(2, fleet.getShipList().size());
        assertEquals(200, fleet.getFleetPower());

        fleet.updateShipList(newShip, false);
        assertEquals(1, fleet.getShipList().size());
        assertEquals(100, fleet.getFleetPower());

        fleet.updateShipList(ship, false);
        assertEquals(0, fleet.getShipList().size());
        assertEquals(0, fleet.getFleetPower());
    }

    @Test
    public void testSetFleetPosition() {
        Cell newPosition = new Cell(10, 10);
        fleet.setFleetPosition(newPosition);
        assertEquals(newPosition, fleet.getFleetPosition());
    }

    @Test
    public void testEqualsAndHashCode() {
        ArrayList<Ship> otherShipList = new ArrayList<>(Arrays.asList(
                new Ship(Ship.ShipType.BASIC)
        ));
        Fleet sameFleet = new Fleet(otherShipList, new Cell(0, 0));
        Fleet differentFleet = new Fleet(new ArrayList<>(), new Cell(6, 6));

        assertEquals(fleet, sameFleet);
        assertNotEquals(fleet, differentFleet);

        assertEquals(fleet.hashCode(), sameFleet.hashCode());
        assertNotEquals(fleet.hashCode(), differentFleet.hashCode());
    }
}
