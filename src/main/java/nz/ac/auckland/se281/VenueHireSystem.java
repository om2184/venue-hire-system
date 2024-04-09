package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venuesList;
  private ArrayList<Booking> bookingsList;
  private String systemDate;

  public VenueHireSystem() {
    this.venuesList = new ArrayList<Venue>();
    this.bookingsList = new ArrayList<Booking>();
  }

  public void printVenues() {

    // Check if there are no venues in the system
    if (venuesList.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    int numberOfVenues = venuesList.size();

    // Check if there is only one venue in the system
    if (numberOfVenues == 1) {
      Venue venue = venuesList.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getVenueName(),
          venue.getVenueCode(),
          venue.getCapacity(),
          venue.getHireFee(),
          venue.getNextAvailableDate());
      return;
    } else if (numberOfVenues < 10) { // Check if there are less than 10 venues in the system
      String[] numbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[numberOfVenues - 2], "s");
      for (Venue venue : venuesList) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            venue.getNextAvailableDate());
      }
      return;
    } else if (numberOfVenues >= 10) { // Check if there are 10 or more venues in the system
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(numberOfVenues), "s");
      for (Venue venue : venuesList) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            venue.getNextAvailableDate());
      }
      return;
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // Check if the venue name is empty
    venueName = venueName.trim();
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Check if the venue code does not exist in array already
    for (Venue venue : venuesList) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
    }

    // Check if the capacity is a valid number
    int capacity;
    try {
      double capacityDoubled = Double.parseDouble(capacityInput);
      if (capacityDoubled == (int) capacityDoubled) {
        capacity = (int) capacityDoubled;
        if (capacity <= 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
          return;
        }
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " whole");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    // Check if the hire fee is a valid number
    int hireFee;
    try {
      double hireFeeDoubled = Double.parseDouble(hireFeeInput);
      if (hireFeeDoubled == (int) hireFeeDoubled) {
        hireFee = (int) hireFeeDoubled;
        if (hireFee <= 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
          return;
        }
      } else {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " whole");
        return;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // Create the venue and add it to the list of venues
    Venue newVenue = new Venue(venueName, venueCode, capacity, hireFee);
    venuesList.add(newVenue);
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);

    // Update the next available date for each venue
    for (Venue venue : venuesList) {
      venue.updateNextAvailableDate(systemDate);
    }
  }

  public void printSystemDate() {
    if (systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {
    // check if system date is set or no venues are created
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    } else if (venuesList.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // check if venue code exists
    Venue venueToBook = null;
    for (Venue venue : venuesList) {
      if (venue.getVenueCode().equals(options[0])) {
        venueToBook = venue;
        break;
      }
    }
    if (venueToBook == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      return;
    }

    // check if date is in the past
    String[] systemDateParts = systemDate.split("/");
    String[] bookingDateParts = options[1].split("/");
    if (Integer.parseInt(systemDateParts[2]) > Integer.parseInt(bookingDateParts[2])) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
      return;
    } else if (Integer.parseInt(systemDateParts[2]) == Integer.parseInt(bookingDateParts[2])) {
      if (Integer.parseInt(systemDateParts[1]) > Integer.parseInt(bookingDateParts[1])) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
        return;
      } else if (Integer.parseInt(systemDateParts[1]) == Integer.parseInt(bookingDateParts[1])) {
        if (Integer.parseInt(systemDateParts[0]) > Integer.parseInt(bookingDateParts[0])) {
          MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
          return;
        }
      }
    }

    // check if venue is already booked
    for (Booking booking : bookingsList) {
      if (booking.getVenueCode().equals(options[0]) && booking.getDate().equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            venueToBook.getVenueName(), options[1]);
        return;
      }
    }

    // Adjust number of attendees to be a valid number
    int capacity = Integer.parseInt(venueToBook.getCapacity());
    int attendees = Integer.parseInt(options[3]);
    if (attendees > capacity) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], venueToBook.getCapacity(), venueToBook.getCapacity());
      options[3] = venueToBook.getCapacity();
    } else if (attendees < capacity / 4) {
      String quarterCapacity = String.valueOf(capacity / 4);
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], quarterCapacity, venueToBook.getCapacity());
      options[3] = quarterCapacity;
    }

    // Create booking for venue
    Booking newBooking = venueToBook.makeBooking(options[1], options[2], options[3]);
    bookingsList.add(newBooking);

    // Update the next available date for each venue
    for (Venue venue : venuesList) {
      venue.updateNextAvailableDate(systemDate);
    }
  }

  public void printBookings(String venueCode) {
    // check if venue code exists
    Venue venueToPrint = null;
    for (Venue venue : venuesList) {
      if (venue.getVenueCode().equals(venueCode)) {
        venueToPrint = venue;
        break;
      }
    }

    if (venueToPrint == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    } else {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueToPrint.getVenueName());
    }

    // check if there are bookings for the venue
    boolean hasBookings = false;
    for (Booking booking : bookingsList) {
      if (booking.getVenueCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getBookingReference(), booking.getDate());
        hasBookings = true;
      }
    }
    if (!hasBookings) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueToPrint.getVenueName());
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // check if booking reference exists
    Booking bookingToAddCatering = null;

    for (Booking booking : bookingsList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingToAddCatering = booking;
        break;
      }
    }
    if (bookingToAddCatering == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }
  }

  public void addServiceMusic(String bookingReference) {
    Booking bookingToAddMusic = null;

    for (Booking booking : bookingsList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingToAddMusic = booking;
        break;
      }
    }
    if (bookingToAddMusic == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    Booking bookingToAddFloral = null;

    for (Booking booking : bookingsList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingToAddFloral = booking;
        break;
      }
    }
    if (bookingToAddFloral == null) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
