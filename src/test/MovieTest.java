package test;

import main.Movie;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {
    Movie officeSpace = new Movie("Office Space", 0);

    @Test
    public void checksTitle() {
        assertEquals("Office Space", officeSpace.getTitle());
    }

    @Test
    public void checksPriceCode() {
        assertEquals(0, officeSpace.getPriceCode());
    }

}