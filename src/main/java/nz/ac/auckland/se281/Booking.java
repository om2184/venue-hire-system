package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String venueName;
  private String venueCode;
  private String dateOfBooking;
  private String partyDate;
  private String email;
  private String attendees;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<>();

  public Booking(
      String venueName,
      String venueCode,
      String date,
      String email,
      String attendees,
      String dateOfBooking) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.dateOfBooking = dateOfBooking;
    this.partyDate = date;
    this.email = email;
    this.attendees = attendees;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        this.bookingReference, this.venueName, this.partyDate, this.attendees);
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

  public String getPartyDate() {
    return partyDate;
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

  public int getServicesCost() {
    // Calculate the total cost of all services
    int totalCost = 0;
    for (Service service : services) {
      totalCost += service.calculateCost(Integer.parseInt(attendees));
    }
    return totalCost;
  }
}
