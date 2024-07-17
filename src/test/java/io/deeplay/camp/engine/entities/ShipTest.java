package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private Ship ship= new Ship(Ship.ShipType.BASIC);
    private ArrayList<Ship> shipList= new ArrayList<>();
    private Fleet fleet= new Fleet(shipList, new Cell(0,0));

    @Test
    void getAttackPoints() {
        assertEquals(100, ship.getAttackPoints());
    }

    @Test
    void getShipType() {
        assertEquals(Ship.ShipType.BASIC, ship.getShipType());
    }

    @Test
    void fleetAffiliation0() {
        assertNull(ship.fleetAffiliation());
    }
    @Test
    void fleetAffiliation1() {
        ship.setFleetAffiliation(fleet);
        shipList.add(ship);
        assertEquals(fleet, ship.fleetAffiliation());
        assertEquals(shipList, fleet.getShipList());
    }
    @Test
    public void testShipConstructorWithTypeAndFleet() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0,0));  // Предположим, что у нас есть mock объект Fleet
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);
        assertEquals(Ship.ShipType.BASIC, ship.getShipType());
        assertEquals(100, ship.getAttackPoints());
        assertEquals(mockFleet, ship.fleetAffiliation());
    }
    @Test
    public void testSetFleetAffiliation() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0,0));
        Ship ship = new Ship(Ship.ShipType.BASIC);
        ship.setFleetAffiliation(mockFleet);
        assertEquals(mockFleet, ship.fleetAffiliation());
    }
}
