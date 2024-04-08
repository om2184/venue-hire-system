package nz.ac.auckland.se281;

public class Booking {

  private String venueName;
  private String date;
  private String email;
  private String attendees;
  private String bookingReference;

  public Booking(String venueName, String date, String email, String attendees) {
    this.venueName = venueName;
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
}
