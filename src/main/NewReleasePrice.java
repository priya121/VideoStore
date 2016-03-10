package main;

import java.util.function.Supplier;

public class NewReleasePrice extends Price implements Supplier<Price> {
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }

    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public Price get() {
        return null;
    }
}
