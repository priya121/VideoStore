import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {
    Movie officeSpace = new Movie("Office Space", 5);

    @Test
    public void checksTitle() {
        assertEquals("Office Space", officeSpace.getTitle());
    }

    @Test
    public void checksPriceCode() {
        assertEquals(5, officeSpace.getPriceCode());
    }

    @Test
    public void changePriceCode() {
        officeSpace.setPriceCode(9);
        assertEquals(9, officeSpace.getPriceCode());
    }

}