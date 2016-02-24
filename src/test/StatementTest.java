package test;

import junit.framework.Assert;
import main.Statement;
import main.Movie;
import main.Rental;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StatementTest {
    Statement statement = new Statement("Customer");

    Movie officeSpace = new Movie("Office Space", Movie.REGULAR);
    Movie harryPotter = new Movie("Harry Potter", Movie.CHILDRENS);
    Movie starWars = new Movie("Star Wars", Movie.NEW_RELEASE);

    Rental twoDayOfficeSpace = new Rental(officeSpace, 2);
    Rental threeDayOfficeSpace = new Rental(officeSpace, 3);
    Rental threeDayHarryPotter = new Rental(harryPotter, 3);
    Rental fourDayHarryPotter = new Rental(harryPotter, 4);
    Rental twoDayStarWars = new Rental(starWars, 3);

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
                        "\tStar Wars\t9.0\n" +
                        "\tHarry Potter\t3.0\n" +
                        "\tOffice Space\t3.5\n" +
                        "Amount owed is 15.5\n" +
                        "You earned 4 frequent renter points",
                statement.generate());
    }

}