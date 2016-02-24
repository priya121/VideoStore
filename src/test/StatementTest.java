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

    Rental twoDayRegular = new Rental(regularMovie, 2);
    Rental threeDayRegular = new Rental(regularMovie, 3);
    Rental threeDayChildrens = new Rental(childrensMovie, 3);
    Rental fourDayChildrens = new Rental(childrensMovie, 4);
    Rental twoDayNewRelease = new Rental(newRelease, 3);

    @Test
    public void testsTwoDayRegularRentalTotals() {
        statement.addRental(twoDayRegular);
        assertEquals(2.0, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsThreeDayRegularRentalTotals() {
        statement.addRental(threeDayRegular);
        assertEquals(3.5, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsCombinationThreeDayChildrenAndTwoDayRegularTotals() {
        statement.addRental(threeDayChildrens);
        statement.addRental(twoDayRegular);
        statement.generate();
        assertEquals(3.5, statement.getTotalCharge());
        assertEquals(2, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsFourDayChildrensTotals() {
        statement.addRental(fourDayChildrens);
        assertEquals(3.0, statement.getTotalCharge());
        assertEquals(1, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsTwoDayNewReleaseTotals() {
        statement.addRental(twoDayNewRelease);
        assertEquals(9.0, statement.getTotalCharge());
        assertEquals(2, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsMultipleRegularStatementTotals() {
        statement.addRental(twoDayNewRelease);
        statement.addRental(fourDayChildrens);
        statement.addRental(threeDayRegular);
        Assert.assertEquals(15.5, statement.getTotalCharge());
        Assert.assertEquals(4, statement.getTotalFrequentRenterPoints());
    }

    @Test
    public void testsMultipleRegularStatementFormat() {
        statement.addRental(twoDayNewRelease);
        statement.addRental(fourDayChildrens);
        statement.addRental(threeDayRegular);
        assertEquals("Rental Record for Customer\n" +
                        "\tNew Release Movie\t9.0\n" +
                        "\tChildrens Movie\t3.0\n" +
                        "\tRegular Movie\t3.5\n" +
                        "Amount owed is 15.5\n" +
                        "You earned 4 frequent renter points",
                statement.generate());
    }

}