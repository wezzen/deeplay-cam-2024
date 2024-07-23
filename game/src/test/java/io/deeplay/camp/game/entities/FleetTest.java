package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Fleet;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.entites.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FleetTest {
    /*private Fleet fleet;
    private Ship ship = new Ship(Ship.ShipType.BASIC);
    Cell position = new Cell(0, 0);
    List<Ship> shipList = new ArrayList<>();

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

    @Test
    public void testFleetsClash() {
        // Создаем корабли
        Ship ship1 = new Ship(Ship.ShipType.BASIC);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL);

        // Создаем флоты
        Fleet fleet1 = new Fleet(Arrays.asList(ship1, ship2), new Cell(0, 0));
        Fleet fleet2 = new Fleet(List.of(ship3), new Cell(1, 1));

        // Создаем игроков
        Player player1 = new Player(1, "Player1");
        Player player2 = new Player(2, "Player2");

        // Добавляем флоты игрокам
        player1.addFleet(fleet1);
        player2.addFleet(fleet2);

        // Выполняем столкновение флотов
        fleet1.fleetsClash(fleet2, player1, player2);

        // Проверяем, что проигравший флот удален из списка флотов проигравшего игрока
        assertFalse(player2.getFleetList().contains(fleet2));
        // Проверяем, что победивший флот остался в списке флотов победившего игрока
        assertTrue(player1.getFleetList().contains(fleet1));
    }*/
}
