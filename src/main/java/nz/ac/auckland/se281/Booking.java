package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String venueName;
  private String venueCode;
  private String dateOfBooking;
  private String date;
  private String email;
  private String attendees;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<>();

  public Booking(String venueName, String venueCode, String date, String email, String attendees) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.date = date;
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venueName, this.date, this.attendees);
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getDate() {
    return date;
  }

  public String getEmail() {
    return email;
  }

  public String getAttendees() {
    return attendees;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public void addService(Service service) {
    services.add(service);
  }

  public ArrayList<Service> getServices() {
    return services;
  }
}
