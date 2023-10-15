package org.nmartinez.springcloud.backinventory.junit.repaso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class AssertNotEqualsTest {
    @Test
    public void shouldNotEquals() {
        assertNotEquals(1,2);
    }

}
