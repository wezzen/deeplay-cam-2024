package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Fleet;
import io.deeplay.camp.game.entites.Player;
import io.deeplay.camp.game.entites.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//todo переделать тесты
class FleetTest {
    private Player player = new Player(0, "test");
    private Player enemy = new Player(1, "enemy");

    private Fleet fleet;
    private Fleet fleetEnemy;


    private Ship ship;
    Cell position = new Cell(0, 0);
    Cell positionEnemy = new Cell(1, 0);


    @BeforeEach
    void setUp() {
        fleetEnemy = new Fleet(positionEnemy, enemy);
        fleet = new Fleet(position, player);
        ship = new Ship(Ship.ShipType.BASIC, fleet);
    }

    @Test
    void getFleetPower() {
        assertEquals(100, fleet.getFleetPower());
    }

    @Test
    void actualFleetPower0() {
        Ship basicShip = new Ship(Ship.ShipType.BASIC, fleet);
        assertEquals(200, fleet.getFleetPower());
    }


    @Test
    void updateShipList() {
        ArrayList<Ship> tryShip = new ArrayList<>();
        Ship basicShip = new Ship(Ship.ShipType.BASIC, fleet);
        tryShip.add(ship);
        tryShip.add(basicShip);
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
        newShipList.add(new Ship(Ship.ShipType.BASIC, fleet));
        newShipList.add(new Ship(Ship.ShipType.BASIC, fleet));
        fleet.addShipsIntoFleet(newShipList);

        assertEquals(5, fleet.getShipList().size());
        assertEquals(500, fleet.getFleetPower());
    }

    @Test
    public void testUpdateShipListWithNewShips() {
        ArrayList<Ship> newShipList = new ArrayList<>();
        newShipList.add(new Ship(Ship.ShipType.BASIC, fleet));
        newShipList.add(new Ship(Ship.ShipType.BASIC, fleet));

        assertEquals(3, fleet.getShipList().size());
        assertEquals(300, fleet.getFleetPower());
    }

    @Test
    void getShipList() {
        ArrayList<Ship> tryShip = new ArrayList<>();
        Ship basicShip = new Ship(Ship.ShipType.BASIC, fleet);
        tryShip.add(ship);
        tryShip.add(basicShip);
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
        Ship newShip = new Ship(Ship.ShipType.BASIC, fleet);

        assertEquals(2, fleet.getShipList().size());
        assertEquals(200, fleet.getFleetPower());

    }

    @Test
    public void testSetFleetPosition() {
        Cell newPosition = new Cell(10, 10);
        fleet.setFleetPosition(newPosition);
        assertEquals(newPosition, fleet.getFleetPosition());
    }

    @Test
    void getShipsByShipTypeTest() {
        Map<Ship.ShipType, Integer> shipTypeIntegerMap = fleet.getShipsByShipType();
        assertEquals(shipTypeIntegerMap.get(Ship.ShipType.BASIC), 1);
    }

    @Test
    void removeShipsFromFleetTest() {
        Map<Ship.ShipType, Integer> shipTypeIntegerMap = fleet.getShipsByShipType();
        Ship ship1 = new Ship(Ship.ShipType.BASIC, fleet);
        Ship ship2 = new Ship(Ship.ShipType.BASIC, fleet);

        List<Ship> result = fleet.removeShipsFromFleet(shipTypeIntegerMap);

        assertEquals(2, fleet.getShipList().size());
        assertEquals(200, fleet.getFleetPower());

        assertEquals(result.size(), 1);

    }

    //todo посмотреть что с переопределением хэшкода сейчас StackOverflow хотя Objects.hash должен исключать такое(?)
    @Test
    public void testEqualsAndHashCode() {
//        ArrayList<Ship> otherShipList = new ArrayList<>(List.of(
//                new Ship(Ship.ShipType.BASIC, fleet)
//        ));
//        Fleet sameFleet = new Fleet(otherShipList, new Cell(0, 0), player);
//        Fleet differentFleet = new Fleet(new ArrayList<>(), new Cell(6, 6), enemy);
//
//        assertEquals(fleet, sameFleet);
//        assertNotEquals(fleet, differentFleet);
//
//        assertEquals(fleet.hashCode(), sameFleet.hashCode());
//        assertNotEquals(fleet.hashCode(), differentFleet.hashCode());
    }

    @Test
    public void testFleetsClash() {
        // Создаем корабли
        Ship ship1 = new Ship(Ship.ShipType.BASIC, fleet);
        Ship ship2 = new Ship(Ship.ShipType.MEDIUM, fleet);
        Ship ship3 = new Ship(Ship.ShipType.POWERFUL, fleetEnemy);


        // Выполняем столкновение флотов
        fleet.fleetsClash(fleetEnemy, player, enemy);

        // Проверяем, что проигравший флот удален из списка флотов проигравшего игрока
        assertFalse(enemy.getFleetList().contains(fleetEnemy));
        // Проверяем, что победивший флот остался в списке флотов победившего игрока
        assertTrue(player.getFleetList().contains(fleet));
    }
}
