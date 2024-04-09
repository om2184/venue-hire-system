package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String venueName;
  private String venueCode;
  private String dateOfBooking;
  private String dateBookedFor;
  private String email;
  private String attendees;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<>();

  public Booking(
      String venueName,
      String venueCode,
      String dateBookedFor,
      String email,
      String attendees,
      String dateOfBooking) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.dateOfBooking = dateOfBooking; // Date of booking is the current system date
    this.dateBookedFor = dateBookedFor; // Date the booking is made for
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();

    // Print the booking confirmation message
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venueName, this.dateBookedFor, this.attendees);
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getDateOfBooking() {
    return dateOfBooking;
  }

  public String getDateBookedFor() {
    return dateBookedFor;
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
