package ru.libre;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сломанный тест ")
public class SomeTest {

    @Test
    void someTest() {
        assertEquals(1, 2);
    }
}
