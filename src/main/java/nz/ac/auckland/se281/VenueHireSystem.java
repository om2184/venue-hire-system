package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venuesList;

  public VenueHireSystem() {
    this.venuesList = new ArrayList<Venue>();
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
      Venue venueInSystem = venuesList.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
          venueInSystem.getVenueName(),
          venueInSystem.getVenueCode(),
          venueInSystem.getCapacity(),
          venueInSystem.getHireFee(),
          "TODO");
      return;
    }

    if (numberOfVenues > 1 && numberOfVenues < 10) {
      String[] numbers = {"two", "three", "four", "five", "six", "seven", "eight", "nine"};
      MessageCli.NUMBER_VENUES.printMessage("are", numbers[numberOfVenues - 2], "s");
      for (Venue venue : venuesList) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            "TODO");
      }
      return;
    } else if (numberOfVenues >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(numberOfVenues), "s");
      for (Venue venue : venuesList) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.getVenueName(),
            venue.getVenueCode(),
            venue.getCapacity(),
            venue.getHireFee(),
            "TODO");
      }
      return;
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

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
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
