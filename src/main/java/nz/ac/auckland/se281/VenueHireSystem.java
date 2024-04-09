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

    // check if there are no venues in the system
    if (venuesList.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    int numberOfVenues = venuesList.size();

    // check if there is only one venue in the system
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
    } else if (numberOfVenues < 10) { // check if there are less than 10 venues in the system
      // create an array of numbers to be used in the message
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
    } else if (numberOfVenues >= 10) { // check if there are 10 or more venues in the system
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

    // check if the venue name is empty
    venueName = venueName.trim();
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // check if the venue code does not exist in array already
    for (Venue venue : venuesList) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
    }

    // check if the capacity is a valid number
    int capacity;
    try {
      double capacityDoubled = Double.parseDouble(capacityInput);
      // check if the capacity is a whole number
      if (capacityDoubled == (int) capacityDoubled) {
        capacity = (int) capacityDoubled;
        if (capacity <= 0) { // capacity has to be a positive number
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

    // check if the hire fee is a valid number
    int hireFee;
    try {
      double hireFeeDoubled = Double.parseDouble(hireFeeInput);
      // check if the hire fee is a whole number
      if (hireFeeDoubled == (int) hireFeeDoubled) {
        hireFee = (int) hireFeeDoubled;
        if (hireFee <= 0) { // hire fee has to be a positive number
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

    // create the venue and add it to the list of venues
    Venue newVenue = new Venue(venueName, venueCode, capacity, hireFee);
    venuesList.add(newVenue);
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);

    // update the next available date for each venue
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
    if (venueToBook == null) { // booking not made, venue code does not exist
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      return;
    }

    String[] systemDateParts = systemDate.split("/");
    String[] bookingDateParts = options[1].split("/");

    // check if date is in the past
    // compare the year first, then the month, then the day
    if (Integer.parseInt(systemDateParts[2]) > Integer.parseInt(bookingDateParts[2])) { // year
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
      return;
    } else if (Integer.parseInt(systemDateParts[2]) == Integer.parseInt(bookingDateParts[2])) {
      if (Integer.parseInt(systemDateParts[1]) > Integer.parseInt(bookingDateParts[1])) { // month
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
        return;
      } else if (Integer.parseInt(systemDateParts[1]) == Integer.parseInt(bookingDateParts[1])) {
        if (Integer.parseInt(systemDateParts[0]) > Integer.parseInt(bookingDateParts[0])) { // day
          MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
          return;
        }
      }
    }

    // check if the venue is already booked on the date
    for (Booking booking : bookingsList) {
      if (booking.getVenueCode().equals(options[0])
          && booking.getDateBookedFor().equals(options[1])) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
            venueToBook.getVenueName(), options[1]);
        return;
      }
    }

    // adjust number of attendees to be a valid number
    int capacity = Integer.parseInt(venueToBook.getCapacity());
    int attendees = Integer.parseInt(options[3]);
    // if there are too many attendees, adjust the number to the venue capacity
    if (attendees > capacity) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], venueToBook.getCapacity(), venueToBook.getCapacity());
      options[3] = venueToBook.getCapacity();
    }
    // if there are too few attendees, adjust the number
    else if (attendees < 1) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], "1", venueToBook.getCapacity());
      options[3] = "1";
    } else if (attendees < capacity / 4) {
      String quarterCapacity = String.valueOf(capacity / 4);
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3], quarterCapacity, venueToBook.getCapacity());
      options[3] = quarterCapacity;
    }

    // create booking for venue
    Booking newBooking = venueToBook.makeBooking(options[1], options[2], options[3], systemDate);
    bookingsList.add(newBooking);

    // update the next available date for each venue
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

    // check if there are any bookings for the venue
    boolean hasBookings = false;
    for (Booking booking : bookingsList) {
      if (booking.getVenueCode().equals(venueCode)) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage( // booking for venue found
            booking.getBookingReference(), booking.getDateBookedFor());
        hasBookings = true;
      }
    }
    if (!hasBookings) { // no bookings for the venue
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

    // add catering service to booking
    Service cateringService = new CateringService(bookingReference, cateringType);
    bookingToAddCatering.addService(cateringService);
  }

  public void addServiceMusic(String bookingReference) {

    // check if booking reference exists
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

    // add music service to booking
    Service musicService = new MusicService(bookingReference);
    bookingToAddMusic.addService(musicService);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {

    // check if booking reference exists
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

    // add floral service to booking
    Service floralService = new FloralService(bookingReference, floralType);
    bookingToAddFloral.addService(floralService);
  }

  public void viewInvoice(String bookingReference) {

    // check if booking reference exists
    Booking bookingToViewInvoice = null;
    for (Booking booking : bookingsList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        bookingToViewInvoice = booking;
        break;
      }
    }
    if (bookingToViewInvoice == null) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
      return;
    }

    // print customer details for booking
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingToViewInvoice.getBookingReference(),
        bookingToViewInvoice.getEmail(),
        bookingToViewInvoice.getDateOfBooking(),
        bookingToViewInvoice.getDateBookedFor(),
        bookingToViewInvoice.getAttendees(),
        bookingToViewInvoice.getVenueName());

    // find which venue the booking is for
    Venue venueForBooking = null;
    for (Venue venue : venuesList) {
      if (venue.getVenueCode().equals(bookingToViewInvoice.getVenueCode())) {
        venueForBooking = venue;
        break;
      }
    }

    // print the venue hire fee
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueForBooking.getHireFee());

    // initialise costs for services
    int cateringCost = 0;
    int musicCost = 0;
    int floralCost = 0;

    ArrayList<Service> services = bookingToViewInvoice.getServices();
    for (Service service : services) {
      if (service instanceof CateringService) { // calculate and print catering cost
        CateringService cateringService = (CateringService) service;
        cateringCost =
            cateringService.calculateCost(Integer.parseInt(bookingToViewInvoice.getAttendees()));
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
            cateringService.getServiceName(), Integer.toString(cateringCost));
      } else if (service instanceof MusicService) { // calculate and print music cost
        MusicService musicService = (MusicService) service;
        musicCost =
            musicService.calculateCost(Integer.parseInt(bookingToViewInvoice.getAttendees()));
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(Integer.toString(musicCost));
      } else if (service instanceof FloralService) { // calculate and print floral cost
        FloralService floralService = (FloralService) service;
        floralCost =
            floralService.calculateCost(Integer.parseInt(bookingToViewInvoice.getAttendees()));
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
            floralService.getServiceName(), Integer.toString(floralCost));
      }
    }

    // calculate and print total cost
    int totalCost =
        Integer.parseInt(venueForBooking.getHireFee()) + cateringCost + musicCost + floralCost;
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(totalCost));
  }
}
