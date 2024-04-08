package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<String> bookingsList;
  private String nextAvailableDate;

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    // Initialises the venue with the given parameters
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.bookingsList = new ArrayList<>();
    this.nextAvailableDate = "01/01/2024"; // Initialise to a default date
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(this.venueName, this.venueCode);
  }

  public String getVenueCode() {
    // Retruns the venue code for this venue
    return this.venueCode;
  }

  public String getVenueName() {
    // Retruns the venue name for this venue
    return this.venueName;
  }

  public String getCapacity() {
    // Retruns the capacity for this venue
    return Integer.toString(this.capacity);
  }

  public String getHireFee() {
    // Retruns the hire fee for this venue
    return Integer.toString(this.hireFee);
  }

  public String getNextAvailableDate() {
    return nextAvailableDate;
  }
}
