package io.deeplay.camp.engine.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private Ship ship = new Ship(Ship.ShipType.BASIC);
    private ArrayList<Ship> shipList = new ArrayList<>();
    private Fleet fleet = new Fleet(shipList, new Cell(0, 0));
    private Ship basicShip = new Ship(Ship.ShipType.BASIC);
    private Ship mediumShip = new Ship(Ship.ShipType.MEDIUM);
    private Ship powerfulShip = new Ship(Ship.ShipType.POWERFUL);


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
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0));  // Предположим, что у нас есть mock объект Fleet
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);
        assertEquals(Ship.ShipType.BASIC, ship.getShipType());
        assertEquals(100, ship.getAttackPoints());
        assertEquals(mockFleet, ship.fleetAffiliation());
    }

    @Test
    public void testSetFleetAffiliation() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0));
        Ship ship = new Ship(Ship.ShipType.BASIC);
        ship.setFleetAffiliation(mockFleet);
        assertEquals(mockFleet, ship.fleetAffiliation());
    }
    @Test
    void getAttackPoints1() {
        assertEquals(100, basicShip.getAttackPoints());
        assertEquals(150, mediumShip.getAttackPoints());
        assertEquals(200, powerfulShip.getAttackPoints());
    }

    @Test
    void getShipType1() {
        assertEquals(Ship.ShipType.BASIC, basicShip.getShipType());
        assertEquals(Ship.ShipType.MEDIUM, mediumShip.getShipType());
        assertEquals(Ship.ShipType.POWERFUL, powerfulShip.getShipType());
    }

    @Test
    void fleetAffiliation2() {
        assertNull(basicShip.fleetAffiliation());
        assertNull(mediumShip.fleetAffiliation());
        assertNull(powerfulShip.fleetAffiliation());
    }

    @Test
    void fleetAffiliation3() {
        basicShip.setFleetAffiliation(fleet);
        shipList.add(basicShip);
        assertEquals(fleet, basicShip.fleetAffiliation());
        assertEquals(shipList, fleet.getShipList());

        mediumShip.setFleetAffiliation(fleet);
        shipList.add(mediumShip);
        assertEquals(fleet, mediumShip.fleetAffiliation());
        assertEquals(shipList, fleet.getShipList());

        powerfulShip.setFleetAffiliation(fleet);
        shipList.add(powerfulShip);
        assertEquals(fleet, powerfulShip.fleetAffiliation());
        assertEquals(shipList, fleet.getShipList());
    }

    @Test
    public void testShipConstructorWithTypeAndFleet1() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0));
        Ship ship1 = new Ship(Ship.ShipType.BASIC, mockFleet);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM, mockFleet);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL, mockFleet);

        assertEquals(Ship.ShipType.BASIC, ship1.getShipType());
        assertEquals(100, ship1.getAttackPoints());
        assertEquals(mockFleet, ship1.fleetAffiliation());

        assertEquals(Ship.ShipType.MEDIUM, ship2.getShipType());
        assertEquals(150, ship2.getAttackPoints());
        assertEquals(mockFleet, ship2.fleetAffiliation());

        assertEquals(Ship.ShipType.POWERFUL, ship3.getShipType());
        assertEquals(200, ship3.getAttackPoints());
        assertEquals(mockFleet, ship3.fleetAffiliation());
    }

    @Test
    public void testSetFleetAffiliation1() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0));
        Ship ship1 = new Ship(Ship.ShipType.BASIC);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL);

        List<Ship> newShipList = new ArrayList<Ship>();
        newShipList.add(ship1);
        newShipList.add(ship2);
        newShipList.add(ship3);

        ship1.setFleetAffiliation(mockFleet);
        assertEquals(mockFleet, ship1.fleetAffiliation());

        ship2.setFleetAffiliation(mockFleet);
        assertEquals(mockFleet, ship2.fleetAffiliation());

        ship3.setFleetAffiliation(mockFleet);
        assertEquals(mockFleet, ship3.fleetAffiliation());

        assertEquals(fleet.getShipList(), newShipList);
    }

    // Новые тесты для проверки новых типов кораблей
    @Test
    void testMediumShipAttributes() {
        assertEquals(150, mediumShip.getAttackPoints());
        assertEquals("Medium", mediumShip.getShipType().toString());
    }

    @Test
    void testPowerfulShipAttributes() {
        assertEquals(200, powerfulShip.getAttackPoints());
        assertEquals("Powerful", powerfulShip.getShipType().toString());
    }
}
