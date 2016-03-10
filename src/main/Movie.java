package main;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public void setPriceCode(int movieType) {
        Map<Object, Supplier<Price>> map = new HashMap<>();
        map.put(NEW_RELEASE, () -> new NewReleasePrice());
        map.put(REGULAR, () -> new RegularPrice());
        map.put(CHILDRENS, () -> new ChildrensPrice());
        Supplier<Price> supplier = map.get(movieType);
        price =  supplier.get();
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }
}
