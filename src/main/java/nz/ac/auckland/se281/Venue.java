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

  void updateNextAvailableDate(String systemDate) {
    // If no bookings have been made, the next available date is the system date
    if (this.bookingsList.isEmpty()) {
      this.nextAvailableDate = systemDate;
      return;
    }

    // Find the next available date
    String[] systemDateParts = systemDate.split("/");
    String[] bookingDateParts = this.bookingsList.get(this.bookingsList.size() - 1).split("/");
    if (Integer.parseInt(systemDateParts[2]) > Integer.parseInt(bookingDateParts[2])) {
      this.nextAvailableDate = systemDate;
      return;
    } else if (Integer.parseInt(systemDateParts[2]) == Integer.parseInt(bookingDateParts[2])) {
      if (Integer.parseInt(systemDateParts[1]) > Integer.parseInt(bookingDateParts[1])) {
        this.nextAvailableDate = systemDate;
        return;
      } else if (Integer.parseInt(systemDateParts[1]) == Integer.parseInt(bookingDateParts[1])) {
        if (Integer.parseInt(systemDateParts[0]) > Integer.parseInt(bookingDateParts[0])) {
          this.nextAvailableDate = systemDate;
          return;
        }
      }
    }
  }
}
