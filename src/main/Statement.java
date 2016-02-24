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

    public String getName() {
        return name;
    }

    public String generate() {
        String statementText = header();
        for (Rental rental : rentals) {
            statementText += "\t" + rental.getMovie().getTitle() + "\t" +
                    String.valueOf(rental.getCharge()) + "\n";
        }
        statementText += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        statementText += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return statementText;
    }

    private String header() {
        return "Rental Record for " + getName() + "\n";
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
