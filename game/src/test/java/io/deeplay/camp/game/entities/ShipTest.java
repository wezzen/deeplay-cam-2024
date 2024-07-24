package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Fleet;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.entites.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    private ArrayList<Ship> shipList = new ArrayList<>();
    private Player player = new Player(1, "test");
    private Fleet fleet = new Fleet(shipList, new Cell(0, 0), player);
    private Ship basicShip = new Ship(Ship.ShipType.BASIC, fleet);
    private Ship mediumShip = new Ship(Ship.ShipType.MEDIUM, fleet);
    private Ship powerfulShip = new Ship(Ship.ShipType.POWERFUL, fleet);


    @Test
    void getAttackPoint0() {
        assertEquals(100, Ship.ShipType.BASIC.getShipPower());
    }

    @Test
    void getAttackPoints1() {
        assertEquals(150, Ship.ShipType.MEDIUM.getShipPower());
    }

    @Test
    void getAttackPoints2() {
        assertEquals(200, Ship.ShipType.POWERFUL.getShipPower());
    }

    @Test
    void getAttackPoints3() {
        assertEquals(100, basicShip.getShipType().getShipPower());
    }

    @Test
    void getShipType0() {
        assertEquals(Ship.ShipType.BASIC, basicShip.getShipType());
    }

    @Test
    void getShipType1() {
        assertEquals(Ship.ShipType.BASIC, basicShip.getShipType());
        assertEquals(Ship.ShipType.MEDIUM, mediumShip.getShipType());
        assertEquals(Ship.ShipType.POWERFUL, powerfulShip.getShipType());
    }

    @Test
    void fleetAffiliation0() {
        assertNotNull(basicShip.fleetAffiliation());
        assertNotNull(mediumShip.fleetAffiliation());
        assertNotNull(powerfulShip.fleetAffiliation());
    }

    @Test
    void fleetAffiliation1() {
        Ship newShip = new Ship(Ship.ShipType.BASIC, fleet);
        assertTrue(fleet.getShipList().contains(newShip));
        assertEquals(fleet, newShip.fleetAffiliation());
    }

    @Test
    void fleetAffiliation2() {
        assertEquals(fleet, basicShip.fleetAffiliation());
        assertEquals(fleet, mediumShip.fleetAffiliation());
        assertEquals(fleet, powerfulShip.fleetAffiliation());
    }

    @Test
    public void testShipConstructorWithTypeAndFleet() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0), player);  // Предположим, что у нас есть mock объект Fleet
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);
        assertEquals(Ship.ShipType.BASIC, ship.getShipType());
        assertEquals(100, ship.getShipType().getShipPower());
        assertEquals(mockFleet, ship.fleetAffiliation());
    }

    @Test
    public void testSetFleetAffiliation() {
        Fleet mockFleet = new Fleet(new ArrayList<>(), new Cell(0, 0), player);
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);
        assertEquals(mockFleet, ship.fleetAffiliation());
    }

    @Test
    public void testShipConstructorWithTypeAndFleet1() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0), player);
        Ship ship1 = new Ship(Ship.ShipType.BASIC, mockFleet);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM, mockFleet);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL, mockFleet);

        assertEquals(Ship.ShipType.BASIC, ship1.getShipType());
        assertEquals(100, ship1.getShipType().getShipPower());
        assertEquals(mockFleet, ship1.fleetAffiliation());

        assertEquals(Ship.ShipType.MEDIUM, ship2.getShipType());
        assertEquals(150, ship2.getShipType().getShipPower());
        assertEquals(mockFleet, ship2.fleetAffiliation());

        assertEquals(Ship.ShipType.POWERFUL, ship3.getShipType());
        assertEquals(200, ship3.getShipType().getShipPower());
        assertEquals(mockFleet, ship3.fleetAffiliation());
    }

    @Test
    void testMediumShipAttributes() {
        assertEquals(150, mediumShip.getShipType().getShipPower());
        assertEquals("Medium", mediumShip.getShipType().toString());
    }

    @Test
    void testPowerfulShipAttributes() {
        assertEquals(200, powerfulShip.getShipType().getShipPower());
        assertEquals("Powerful", powerfulShip.getShipType().toString());
    }
}
