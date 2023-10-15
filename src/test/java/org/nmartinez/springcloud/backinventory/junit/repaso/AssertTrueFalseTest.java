package org.nmartinez.springcloud.backinventory.junit.repaso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AssertTrueFalseTest {
    @Test
    public void shouldTrueFalse() {

        assertTrue(true);
        assertFalse(false);
    }

    @Test
    public void shouldReturnTrue() {

        boolean expression1 = (4 == 4);
        boolean expression2 = (2 == 1);

        assertTrue(expression1);
        assertFalse(expression2);
    }

}
