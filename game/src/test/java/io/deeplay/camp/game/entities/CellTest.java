package io.deeplay.camp.game.entities;

import io.deeplay.camp.game.entites.Cell;
import io.deeplay.camp.game.entites.Fleet;
import io.deeplay.camp.game.entites.Planet;
import io.deeplay.camp.game.entites.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testCellCreationWithoutPlanet() {
        Cell cell = new Cell(1, 2);
        assertEquals(1, cell.x, "Cell x = 1");
        assertEquals(2, cell.y, "Cell y = 2");
        assertNull(cell.planet, "Planet should be null");
    }

    @Test
    void testCellCreationWithPlanet() {
        Planet planet = new Planet(7);
        Cell cell = new Cell(3, 4, planet);
        assertEquals(3, cell.x, "Cell x = 3");
        assertEquals(4, cell.y, "Cell = 4");
        assertNotNull(cell.planet, "Planet should not be null when Planet");
        assertTrue(cell.planet.points >= 0 && cell.planet.points < 10,
                "Planet points should be between 0 and 9 inclusive");
    }

    @Test
    public void testCopyConstructor() {
        // Создаем оригинальную клетку с планетой
        Planet planet = new Planet(100);
        Cell original = new Cell(3, 5, planet);

        // Создаем копию с использованием конструктора копирования
        Cell copy = new Cell(original);

        // Проверяем, что координаты x и y скопированы правильно
        assertEquals(original.x, copy.x);
        assertEquals(original.y, copy.y);

        // Проверяем, что планета не скопирована (т.к. в конструкторе копирования она установлена в null)
        assertNotNull(copy.planet);
        assertNull(copy.getFleet());

        // Проверяем, что оригинальная клетка и копия - это разные объекты
        assertNotSame(original, copy);

        // Проверяем, что изменение копии не влияет на оригинал
        Fleet fleet = new Fleet(new Cell(1, 1), new Player(0, "Player1"));
        copy.setFleet(fleet);
        assertNull(original.getFleet());
        assertNotNull(copy.getFleet());

        // Проверяем, что создание копии с null вызывает исключение
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Cell(null);
        });
        assertEquals("Cell не может быть null", exception.getMessage());
    }
}
