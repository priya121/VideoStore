package test;

import main.Statement;
import main.Movie;
import main.Rental;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StatementTest {
    Statement priya = new Statement("Priya");
    Statement jo = new Statement("Jo");
    Statement andrew = new Statement("Andrew");
    Statement katherine = new Statement("Katherine");
    Statement sarah = new Statement("Sarah");

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
        priya.addRental(twoDayOfficeSpace);
        priya.generate();
        assertEquals(2.0, priya.getTotalCharge());
        assertEquals(1, priya.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForCustomerPriyaLongerRegularRental() {
        priya.addRental(threeDayOfficeSpace);
        priya.generate();
        assertEquals(3.5, priya.getTotalCharge());
        assertEquals(1, priya.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForCustomerJoMultipleRentals() {
        jo.addRental(threeDayHarryPotter);
        jo.addRental(twoDayOfficeSpace);
        jo.generate();
        assertEquals(3.5, jo.getTotalCharge());
        assertEquals(2, jo.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForLongerChildrens() {
        katherine.addRental(fourDayHarryPotter);
        katherine.generate();
        assertEquals(3.0, katherine.getTotalCharge());
        assertEquals(1, katherine.getTotalFrequentRenterPoints());
    }

    @Test
    public void createsStatementForNewRelease() {
        andrew.addRental(twoDayStarWars);
        andrew.generate();
        assertEquals(9.0, andrew.getTotalCharge());
        assertEquals(2, andrew.getTotalFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        sarah.addRental(twoDayStarWars);
        sarah.addRental(fourDayHarryPotter);
        sarah.addRental(threeDayOfficeSpace);
        assertEquals("Rental Record for Sarah\n" +
                        "\tStar Wars\t9.0\n" +
                        "\tHarry Potter\t3.0\n" +
                        "\tOffice Space\t3.5\n" +
                        "Amount owed is 15.5\n" +
                        "You earned 4 frequent renter points",
                sarah.generate());
    }

}