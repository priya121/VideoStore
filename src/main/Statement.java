package main;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Statement(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String generate() {
        String statementText = header();
        statementText += rentalLines();
        statementText += footer();
        return statementText;
    }

    private String footer() {
        return String.format("Amount owed is %.1f\nYou earned %d frequent renter points",
                getTotalCharge(), getTotalFrequentRenterPoints());
    }

    private String rentalLines() {
        String statementText = "";
        for (Rental rental : rentals) {
            statementText += formatRentalLine(rental);
        }
        return statementText;
    }

    private String formatRentalLine(Rental rental) {
        return String.format("\t%s\t%.1f\n", rental.getMovie().getTitle(), rental.getCharge());
    }

    private String header() {
        return String.format("Rental Record for %s\n", name);
    }

    public double getTotalCharge() {
        double result = 0;
        for (Rental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    public int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }
}
