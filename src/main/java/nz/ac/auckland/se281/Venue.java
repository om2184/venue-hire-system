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
    // initialises the venue with the given parameters
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
    this.bookingsList = new ArrayList<>();
    this.nextAvailableDate = "01/01/2024"; // initialise to a default date
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(this.venueName, this.venueCode);
  }

  public String getVenueCode() {
    return this.venueCode;
  }

  public String getVenueName() {
    return this.venueName;
  }

  public String getCapacity() {
    return Integer.toString(this.capacity);
  }

  public String getHireFee() {
    return Integer.toString(this.hireFee);
  }

  public String getNextAvailableDate() {
    return nextAvailableDate;
  }

  public ArrayList<String> getBookingsList() {
    return bookingsList;
  }

  // make a booking for this venue
  public Booking makeBooking(String date, String email, String attendees, String systemDate) {
    Booking newBooking =
        new Booking(this.venueName, this.venueCode, date, email, attendees, systemDate);
    this.bookingsList.add(date); // add the booking date to the list of bookings
    return newBooking;
  }

  public void updateNextAvailableDate(String systemDate) {
    // get the next available date for this venue
    String[] dateParts = systemDate.split("/");
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    // check if this day is in the booking list
    boolean foundDay = false;
    while (foundDay == false) {
      // if day is in the booking list, increment the day
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
      } else { // if day is not in the booking list, set it to the next available date
        this.nextAvailableDate = String.format("%02d/%02d/%04d", day, month, year);
        foundDay = true;
      }
    }
  }
}
