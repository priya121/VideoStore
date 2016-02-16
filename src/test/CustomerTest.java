package test;

import main.Customer;
import main.Movie;
import main.Rental;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CustomerTest {
    Customer priya = new Customer("Priya");
    Customer jo = new Customer("Jo");
    Customer andrew = new Customer("Andrew");
    Customer katherine = new Customer("Katherine");
    Customer sarah = new Customer("Sarah");

    Movie officeSpace = new Movie("Office Space", Movie.REGULAR);
    Movie harryPotter = new Movie("Harry Potter", Movie.CHILDRENS);
    Movie starWars = new Movie("Star Wars", Movie.NEW_RELEASE);

    Rental twoDayOfficeSpace = new Rental(officeSpace, 2);
    Rental threeDayOfficeSpace = new Rental(officeSpace, 3);
    Rental threeDayHarryPotter = new Rental(harryPotter, 3);
    Rental fourDayHarryPotter= new Rental(harryPotter, 4);
    Rental twoDayStarWars = new Rental(starWars, 3);

    @Test
    public void createsStatementForCustomerPriyaSingleRegularRental() {
        priya.addRental(twoDayOfficeSpace);
        assertEquals("Rental Record for Priya\n" +
                "\tOffice Space\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points", priya.statement());
    }

    @Test
    public void createsStatementForCustomerPriyaLongerRegularRental() {
        priya.addRental(threeDayOfficeSpace);
        assertEquals("Rental Record for Priya\n" +
                "\tOffice Space\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points", priya.statement());
    }

    @Test
    public void createsStatementForCustomerJoMultipleRentals() {
        jo.addRental(threeDayHarryPotter);
        jo.addRental(twoDayOfficeSpace);
        assertEquals("Rental Record for Jo\n" +
                "\tHarry Potter\t1.5\n" +
                "\tOffice Space\t2.0\n" +
                "Amount owed is 3.5\n" +
                "You earned 2 frequent renter points", jo.statement());
    }

    @Test
    public void createsStatementForLongerChildrens() {
        katherine.addRental(fourDayHarryPotter);
        assertEquals("Rental Record for Katherine\n" +
                "\tHarry Potter\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points", katherine.statement());
    }

    @Test
    public void createsStatementForNewRelease() {
        andrew.addRental(twoDayStarWars);
        assertEquals("Rental Record for Andrew\n" +
                "\tStar Wars\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points", andrew.statement());
    }

    @Test
    public void createsStatementForCustomerWithNumerousRentals() {
        sarah.addRental(twoDayStarWars);
        sarah.addRental(fourDayHarryPotter);
        sarah.addRental(threeDayOfficeSpace);
        assertEquals("Rental Record for Sarah\n" +
                "\tStar Wars\t9.0\n" +
                "\tHarry Potter\t3.0\n" +
                "\tOffice Space\t3.5\n" +
                "Amount owed is 15.5\n" +
                "You earned 4 frequent renter points", sarah.statement());
    }
}