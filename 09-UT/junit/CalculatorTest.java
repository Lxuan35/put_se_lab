package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator = null;

    @BeforeEach
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void testAdd(){
        assertEquals(14, calculator.add(10, 4));
        assertEquals(0, calculator.add(-18, 18));
        assertEquals(-3, calculator.add(9, -12));
    }

    @Test
    public void testMultiply(){
        assertEquals(24, calculator.multiply(6, 4));
        assertEquals(-36, calculator.multiply(-9, 4));
        assertEquals(0, calculator.multiply(0, 3));
    }

    @Test
    public void testAddPositiveNumbers() {

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-3, 5);
        }, "Should throw IllegalArgumentException when x or y is negative");
    }
}