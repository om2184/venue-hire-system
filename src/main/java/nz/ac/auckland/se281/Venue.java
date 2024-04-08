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

  public ArrayList<String> getBookingsList() {
    return bookingsList;
  }

  public Booking makeBooking(String date, String email, String attendees) {
    // Create a Booking object
    Booking newBooking = new Booking(this.venueName, this.venueCode, date, email, attendees);
    this.bookingsList.add(date); // Add the booking date to the list of bookings
    return newBooking;
  }

  public void updateNextAvailableDate(String systemDate) {
    this.nextAvailableDate = systemDate;
  }

  public void updateNextAvailableDate() {
    // If no bookings have been made, the next available date is the system date
    // Check if the day after is available
    String[] dateParts = nextAvailableDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    // Check if this day is in the booking list
    boolean foundDay = false;
    while (foundDay = false) {
      if (this.bookingsList.contains(String.format("%02d/%02d/%04d", day, month, year))) {
        day++;
        if (day > 31) {
          day = 1;
          month++;
          if (month > 12) {
            month = 1;
            year++;
          }
        }
      } else {
        this.nextAvailableDate = String.format("%02d/%02d/%04d", day, month, year);
        foundDay = true;
      }
    }
  }
}
