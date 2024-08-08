package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Fleet;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.entites.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    private final Player player = new Player(1, "test");
    private final ArrayList<Ship> shipList = new ArrayList<>();
    private final Fleet fleet = new Fleet(shipList, new Cell(0, 0), player);
    private final Ship basicShip = new Ship(Ship.ShipType.BASIC, fleet);
    private final Ship mediumShip = new Ship(Ship.ShipType.MEDIUM, fleet);
    private final Ship powerfulShip = new Ship(Ship.ShipType.POWERFUL, fleet);

    @Test
    void testShipTypeBasicAttackPoints() {
        assertEquals(100, Ship.ShipType.BASIC.getShipPower(), "BASIC ship type should have 100 attack points.");
    }

    @Test
    void testShipTypeMediumAttackPoints() {
        assertEquals(150, Ship.ShipType.MEDIUM.getShipPower(), "MEDIUM ship type should have 150 attack points.");
    }

    @Test
    void testShipTypePowerfulAttackPoints() {
        assertEquals(200, Ship.ShipType.POWERFUL.getShipPower(), "POWERFUL ship type should have 200 attack points.");
    }

    @Test
    void testBasicShipType() {
        assertEquals(Ship.ShipType.BASIC, basicShip.getShipType(), "Basic ship should be of type BASIC.");
    }

    @Test
    void testShipTypeConsistency() {
        assertEquals(Ship.ShipType.BASIC, basicShip.getShipType());
        assertEquals(Ship.ShipType.MEDIUM, mediumShip.getShipType());
        assertEquals(Ship.ShipType.POWERFUL, powerfulShip.getShipType());
    }

    @Test
    void testFleetAffiliationNotNull() {
        assertNotNull(basicShip.fleetAffiliation(), "Fleet affiliation should not be null.");
        assertNotNull(mediumShip.fleetAffiliation(), "Fleet affiliation should not be null.");
        assertNotNull(powerfulShip.fleetAffiliation(), "Fleet affiliation should not be null.");
    }

    @Test
    void testFleetAffiliationConsistency() {
        Fleet mockFleet = new Fleet(new ArrayList<>(), new Cell(0, 0), player);
        Ship newShip = new Ship(Ship.ShipType.BASIC, mockFleet);

        assertTrue(mockFleet.getShipList().contains(newShip), "Fleet should contain the newly created ship.");
        assertEquals(mockFleet, newShip.fleetAffiliation(), "Fleet affiliation should match the provided fleet.");
    }

    @Test
    void testFleetAffiliationConsistencyWithExistingFleet() {
        assertEquals(fleet, basicShip.fleetAffiliation());
        assertEquals(fleet, mediumShip.fleetAffiliation());
        assertEquals(fleet, powerfulShip.fleetAffiliation());
    }

    @Test
    void testShipConstructorWithTypeAndFleet() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0), player);
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);

        assertEquals(Ship.ShipType.BASIC, ship.getShipType(), "Ship type should be BASIC.");
        assertEquals(100, ship.getShipType().getShipPower(), "Ship attack power should be 100.");
        assertEquals(mockFleet, ship.fleetAffiliation(), "Fleet affiliation should match the provided fleet.");
    }

    @Test
    void testSetFleetAffiliation() {
        Fleet mockFleet = new Fleet(new ArrayList<>(), new Cell(0, 0), player);
        Ship ship = new Ship(Ship.ShipType.BASIC, mockFleet);

        assertEquals(mockFleet, ship.fleetAffiliation(), "Fleet affiliation should match the provided fleet.");
    }

    @Test
    void testMultipleShipConstructors() {
        Fleet mockFleet = new Fleet(shipList, new Cell(0, 0), player);
        Ship ship1 = new Ship(Ship.ShipType.BASIC, mockFleet);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM, mockFleet);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL, mockFleet);

        assertEquals(Ship.ShipType.BASIC, ship1.getShipType(), "Ship1 type should be BASIC.");
        assertEquals(100, ship1.getShipType().getShipPower(), "Ship1 attack power should be 100.");
        assertEquals(mockFleet, ship1.fleetAffiliation(), "Fleet affiliation of Ship1 should match.");

        assertEquals(Ship.ShipType.MEDIUM, ship2.getShipType(), "Ship2 type should be MEDIUM.");
        assertEquals(150, ship2.getShipType().getShipPower(), "Ship2 attack power should be 150.");
        assertEquals(mockFleet, ship2.fleetAffiliation(), "Fleet affiliation of Ship2 should match.");

        assertEquals(Ship.ShipType.POWERFUL, ship3.getShipType(), "Ship3 type should be POWERFUL.");
        assertEquals(200, ship3.getShipType().getShipPower(), "Ship3 attack power should be 200.");
        assertEquals(mockFleet, ship3.fleetAffiliation(), "Fleet affiliation of Ship3 should match.");
    }

    @Test
    void testMediumShipAttributes() {
        assertEquals(150, mediumShip.getShipType().getShipPower(), "Medium ship should have 150 attack points.");
        assertEquals("Medium", mediumShip.getShipType().toString(), "Medium ship type should have name 'Medium'.");
    }

    @Test
    void testPowerfulShipAttributes() {
        assertEquals(200, powerfulShip.getShipType().getShipPower(), "Powerful ship should have 200 attack points.");
        assertEquals("Powerful", powerfulShip.getShipType().toString(), "Powerful ship type should have name 'Powerful'.");
    }

    private final Fleet fleet1 = new Fleet(shipList, new Cell(0, 0), player);
    private final Fleet fleet2 = new Fleet(new ArrayList<>(), new Cell(1, 1), player);
    private final Ship basicShipFleet1 = new Ship(Ship.ShipType.BASIC, fleet1);
    private final Ship mediumShipFleet1 = new Ship(Ship.ShipType.MEDIUM, fleet1);
    private final Ship powerfulShipFleet1 = new Ship(Ship.ShipType.POWERFUL, fleet1);
    private final Ship basicShipFleet2 = new Ship(Ship.ShipType.BASIC, fleet2);
    private final Ship mediumShipFleet2 = new Ship(Ship.ShipType.MEDIUM, fleet2);
    private final Ship powerfulShipFleet2 = new Ship(Ship.ShipType.POWERFUL, fleet2);

    @Test
    void testShipEqualityDifferentFleets0() {
        // Проверяем, что корабли разных флотов считаются разными
        assertNotEquals(basicShipFleet1, basicShipFleet2, "Ships of different fleets should not be equal.");
        assertNotEquals(mediumShipFleet1, mediumShipFleet2, "Ships of different fleets should not be equal.");
        assertNotEquals(powerfulShipFleet1, powerfulShipFleet2, "Ships of different fleets should not be equal.");
    }

    @Test
    void testShipEqualityDifferentFleets1() {
        // Создаем разные флоты
        Fleet fleet1 = new Fleet(new Cell(0, 0), new Player(1, "Player1"));
        Fleet fleet2 = new Fleet(new Cell(1, 1), new Player(2, "Player2"));

        // Создаем корабли в разных флотов
        Ship basicShipFleet1 = new Ship(Ship.ShipType.BASIC, fleet1);
        Ship basicShipFleet2 = new Ship(Ship.ShipType.BASIC, fleet2);
        Ship mediumShipFleet1 = new Ship(Ship.ShipType.MEDIUM, fleet1);
        Ship mediumShipFleet2 = new Ship(Ship.ShipType.MEDIUM, fleet2);
        Ship powerfulShipFleet1 = new Ship(Ship.ShipType.POWERFUL, fleet1);
        Ship powerfulShipFleet2 = new Ship(Ship.ShipType.POWERFUL, fleet2);

        // Проверяем, что корабли разных флотов считаются разными
        assertNotEquals(basicShipFleet1, basicShipFleet2, "Ships of different fleets should not be equal.");
        assertNotEquals(mediumShipFleet1, mediumShipFleet2, "Ships of different fleets should not be equal.");
        assertNotEquals(powerfulShipFleet1, powerfulShipFleet2, "Ships of different fleets should not be equal.");
    }


    @Test
    void testShipFleetAffiliation() {
        // Проверяем, что корабли принадлежат правильным флотам
        assertEquals(fleet1, basicShipFleet1.fleetAffiliation(), "Basic ship from fleet1 should have fleet1 as its affiliation.");
        assertEquals(fleet1, mediumShipFleet1.fleetAffiliation(), "Medium ship from fleet1 should have fleet1 as its affiliation.");
        assertEquals(fleet1, powerfulShipFleet1.fleetAffiliation(), "Powerful ship from fleet1 should have fleet1 as its affiliation.");

        assertEquals(fleet2, basicShipFleet2.fleetAffiliation(), "Basic ship from fleet2 should have fleet2 as its affiliation.");
        assertEquals(fleet2, mediumShipFleet2.fleetAffiliation(), "Medium ship from fleet2 should have fleet2 as its affiliation.");
        assertEquals(fleet2, powerfulShipFleet2.fleetAffiliation(), "Powerful ship from fleet2 should have fleet2 as its affiliation.");
    }

    @Test
    void testFleetContainsShips() {
        // Проверяем, что флоты содержат правильные корабли
        assertTrue(fleet1.getShipList().contains(basicShipFleet1), "Fleet1 should contain basicShipFleet1.");
        assertTrue(fleet1.getShipList().contains(mediumShipFleet1), "Fleet1 should contain mediumShipFleet1.");
        assertTrue(fleet1.getShipList().contains(powerfulShipFleet1), "Fleet1 should contain powerfulShipFleet1.");

        assertTrue(fleet2.getShipList().contains(basicShipFleet2), "Fleet2 should contain basicShipFleet2.");
        assertTrue(fleet2.getShipList().contains(mediumShipFleet2), "Fleet2 should contain mediumShipFleet2.");
        assertTrue(fleet2.getShipList().contains(powerfulShipFleet2), "Fleet2 should contain powerfulShipFleet2.");
    }

    @Test
    void testCopyConstructor() {
        // Arrange
        Player test = new Player(0, "test");
        Cell position = new Cell(0,0);
        Fleet fleet = new Fleet(position, test);

        Ship originalShip = new Ship(Ship.ShipType.BASIC, fleet);

        // Act
        Ship copiedShip = new Ship(originalShip);

        // Assert
        assertEquals(originalShip.getShipType(), copiedShip.getShipType());
        assertNull(copiedShip.fleetAffiliation(), "Fleet affiliation should be null for copied ship");
    }

    @Test
    void testConstructorWithShipTypeAndFleet() {
        // Arrange
        Ship.ShipType type = Ship.ShipType.MEDIUM;
        Player test = new Player(0, "test");
        Cell position = new Cell(0,0);
        Fleet fleet = new Fleet(position, test);

        // Act
        Ship ship = new Ship(type, fleet);

        // Assert
        assertEquals(type, ship.getShipType());
        assertNotNull(ship.fleetAffiliation(), "Fleet affiliation should not be null");
        assertTrue(fleet.getShipList().contains(ship), "Fleet should contain the ship");
    }

    @Test
    void testSetFleetAffiliation1() {
        // Arrange
        Player test = new Player(0, "test");
        Cell position = new Cell(0,0);
        Fleet initialFleet = new Fleet(position, test);
        Fleet newFleetCopy = new Fleet(initialFleet);
        Ship ship = new Ship(Ship.ShipType.POWERFUL, initialFleet);
        Ship shipCopy = new Ship(ship);

        shipCopy.setFleetAffiliation(newFleetCopy);

        assertNotNull(ship.fleetAffiliation(), "Fleet affiliation should not be null");
        assertNotNull(shipCopy.fleetAffiliation(), "Fleet affiliation should not be null");
        assertTrue(newFleetCopy.getShipList().contains(shipCopy), "New fleet should contain the ship");
        assertFalse(newFleetCopy.getShipList().contains(ship), "New fleet should not contain the ship");
        assertTrue(initialFleet.getShipList().contains(ship), "Initial fleet should not contain the ship");
        assertFalse(initialFleet.getShipList().contains(shipCopy), "Initial fleet should not contain the ship");
        assertNotEquals(ship, shipCopy);
        assertNotEquals(initialFleet, newFleetCopy);
    }

}
