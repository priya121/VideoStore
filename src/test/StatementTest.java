package test;

import junit.framework.Assert;
import main.Movie;
import main.Rental;
import main.Statement;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StatementTest {

    private Statement statement;

    @Before
    public void setup() {
        statement = new Statement("Customer");
    }

    Movie regularMovie = new Movie("Regular Movie", Movie.REGULAR);
    Movie childrensMovie = new Movie("Childrens Movie", Movie.CHILDRENS);
    Movie newRelease = new Movie("New Release Movie", Movie.NEW_RELEASE);

    Rental twoDayOfficeSpace = new Rental(regularMovie, 2);
    Rental threeDayOfficeSpace = new Rental(regularMovie, 3);
    Rental threeDayHarryPotter = new Rental(childrensMovie, 3);
    Rental fourDayHarryPotter = new Rental(childrensMovie, 4);
    Rental twoDayStarWars = new Rental(newRelease, 3);

    @Test
    public void createsStatementForCustomerPriyaSingleRegularRental() {
        statement.addRental(twoDayOfficeSpace);
        assertEquals(2.0, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForCustomerPriyaLongerRegularRental() {
        statement.addRental(threeDayOfficeSpace);
        assertEquals(3.5, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForCustomerJoMultipleRentals() {
        statement.addRental(threeDayHarryPotter);
        statement.addRental(twoDayOfficeSpace);
        statement.generate();
        assertEquals(3.5, statement.getTotalCharge());
        assertEquals(2, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForLongerChildrens() {
        statement.addRental(fourDayHarryPotter);
        assertEquals(3.0, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForNewRelease() {
        statement.addRental(twoDayStarWars);
        assertEquals(9.0, statement.getTotalCharge());
        assertEquals(2, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(twoDayStarWars);
        statement.addRental(fourDayHarryPotter);
        statement.addRental(threeDayOfficeSpace);
        Assert.assertEquals(15.5, statement.getTotalCharge());
        Assert.assertEquals(4, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(twoDayStarWars);
        statement.addRental(fourDayHarryPotter);
        statement.addRental(threeDayOfficeSpace);
        assertEquals("Rental Record for Customer\n" +
                        "\tNew Release Movie\t9.0\n" +
                        "\tChildrens Movie\t3.0\n" +
                        "\tRegular Movie\t3.5\n" +
                        "Amount owed is 15.5\n" +
                        "You earned 4 frequent renter points",
                statement.generate());
    }

}