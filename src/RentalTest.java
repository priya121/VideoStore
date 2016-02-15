import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RentalTest {
    Movie officeSpace = new Movie("Office Space", 5);
    Rental twoDayRental = new Rental(officeSpace, 2);

    @Test
    public void checksMovieRented() {
        assertEquals(officeSpace, twoDayRental.getMovie());
    }

    @Test
    public void checksNumberOfDaysRented() {
        assertEquals(2, twoDayRental.getDaysRented());
    }
}