package io.deeplay.camp.engine.entities;

import io.deeplay.camp.engine.Players.BasicPlayer;
import io.deeplay.camp.engine.Ships.BasicShip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {
    /*private Planet planet;
    private Fleet fleet;*/


    /*@BeforeEach
    void setUp() {
        int[] planetCoordinates = new int[]{0, 0};
        Player owner = new BasicPlayer("Player1");
        planet = new Planet(100, 50, owner, planetCoordinates);

        BasicShip basicShip = new BasicShip();
        ArrayList<Ship> shipList = new ArrayList<>();
        shipList.add(basicShip);
        fleet = new Fleet(shipList, new int[]{1, 1});
    }*/

    /*@Test
    void testSetControlledArmor() {
        int initialArmor = planet.getArmor();
        int fleetPower = fleet.getFleetPower();

        planet.setControlledArmor(fleet);
        assertEquals(initialArmor + fleetPower, planet.getArmor());
    }*/

    @Test
    void getPoints() {
    }

    @Test
    void getOwner() {
    }

    @Test
    void getCoordinates() {
    }

    @Test
    void setControlledArmor() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}
