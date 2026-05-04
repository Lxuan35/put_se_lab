package put.io.testing.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailureOrErrorTest {

    @Test
    public void test1(){
        assertEquals(2, 3 + 1);
    }

    @Test
    public void test2(){
        throw new RuntimeException("Exception.");
    }

    @Test
    public void test3() {
        try {
            assertTrue(false, "This will fail");
        } catch (AssertionError e) {
            System.out.println("Caught an AssertionError!");
            e.printStackTrace();
        }
    }


}
