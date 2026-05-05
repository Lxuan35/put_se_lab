package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator audiobookPriceCalculator = null;
    private Audiobook audiobook = null;
    private Customer customer1 = null;
    private Customer customer2 = null;
    private Customer customer3 = null;
    private Customer customer4 = null;


    @BeforeEach
    public void setUp(){
        audiobookPriceCalculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("Software Engineering - Unit tests", 14.99);
        customer1 = new Customer("John", Customer.LoyaltyLevel.STANDARD, true);
        customer2 = new Customer("Michael", Customer.LoyaltyLevel.SILVER, false);
        customer3 = new Customer("Jack", Customer.LoyaltyLevel.GOLD, false);
        customer4 = new Customer("John", Customer.LoyaltyLevel.STANDARD, false);

    }

    @Test
    public void testCalculateSubscriber(){
        assertEquals(0.0, audiobookPriceCalculator.calculate(customer1, audiobook));
    }

    @Test
    public void testCalculateSilverLoyalty(){
        assertEquals(13.491, audiobookPriceCalculator.calculate(customer2, audiobook));
    }

    @Test
    public void testCalculateGoldLoyalty(){
        assertEquals(11.992, audiobookPriceCalculator.calculate(customer3, audiobook));
    }

    @Test
    public void testCalculateStandardLoyalty(){
        assertEquals(14.99, audiobookPriceCalculator.calculate(customer4, audiobook));
    }

}
