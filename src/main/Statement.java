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
            statementText += "\t" + rental.getMovie().getTitle() + "\t" +
                    String.valueOf(rental.getCharge()) + "\n";
        }
        return statementText;
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
