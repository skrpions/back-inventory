package org.nmartinez.springcloud.backinventory.junit.repaso;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    Calculadora calculadora;
    static int count;

    @BeforeAll // Se ejecuta antes que inicien las pruebas unitarias una sola vez
    public static void primero() {
        System.out.println("Primero");
        System.out.println("------------------ ------------------");

    }

    @AfterAll // Se ejecuta al finalizar todas las pruebas unitarias
    public static void ultimo() {
        System.out.println("------------------ ------------------");
        System.out.println("Último");
    }

    @BeforeEach // Se ejecuta al iniciar cada prueba unitaria
    public void primeroPorCadaPrueba() {
        count++;
        System.out.println("* Test: " + count);
        //System.out.println("Primero Por Cada Prueba");
        calculadora = new Calculadora();
    }

    @AfterEach // Se ejecuta al finalizar cada prueba unitaria
    public void ultimoPorCadaPrueba() {
        System.out.println("Último Por Cada Prueba \n");
    }

    // Start Unit Testing
    // --------------------------------------------------------
    @Test
    @DisplayName("Test Suma")
    public void sumarTest() {

        assertEquals(2, calculadora.sumar(1,1) );
        assertFalse(calculadora.sumar(1,1) == 5);

    }

    @Test
    @DisplayName("Test Resta")
    public void restarTest() {

        assertEquals(4, calculadora.restar(5,1) );

    }

    @Test
    @DisplayName("Test Multiplicar")
    public void multiplicarTest() {

        assertEquals(25, calculadora.multiplicar(5,5) );

    }

    @Test
    @DisplayName("Test Dividir")
    //@Disabled("Se deshabilita por efectos educativos \n")
    public void dividirTest() {

        assertTrue(calculadora.dividir(9,3) == 3);

    }

}
