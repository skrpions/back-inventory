package org.nmartinez.springcloud.backinventory.junit.repaso;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssertArrayTest {

    @Test
    public void assertArray(){

        String [] array1 = {"aa","bb"};
        String [] array2 = {"aa","bb"};
        String [] array3 = {"aa","bb", "cc"};

        assertArrayEquals(array1, array2);
    }
}
