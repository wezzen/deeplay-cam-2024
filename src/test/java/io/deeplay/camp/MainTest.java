package io.deeplay.camp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void addTest() {
        Assertions.assertEquals(5, Main.add(3, 2));
    }

}