package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class StringController implements Controller {
    private final List<String> history;
    private String nextString;
    
    public StringController() {
        this.history = new ArrayList<>();
    }

    @Override
    public void setString(final String stringToPrint) {
        if (stringToPrint == null) {
            throw new NullPointerException("no null values");
        } else {
            this.nextString = stringToPrint;
        }
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("no string to print");
        } else {
            System.out.println(this.nextString);
            this.history.add(nextString);
        } 
    }
}
