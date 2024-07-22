package io.deeplay.camp.engine.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void testAnswerCreation() {
        Object response = "Test Response";
        Answer answer = new Answer(response);
        assertNotNull(answer);
        assertEquals(response, answer.response());
    }

    @Test
    void testResponseGetter() {
        Object response = "Test Response";
        Answer answer = new Answer(response);
        assertEquals(response, answer.response());
    }

    @Test
    void testToString() {
        Object response = "Test Response";
        Answer answer = new Answer(response);
        String expectedString = "Answer{response='Test Response'}";
        assertEquals(expectedString, answer.toString());
    }
}
