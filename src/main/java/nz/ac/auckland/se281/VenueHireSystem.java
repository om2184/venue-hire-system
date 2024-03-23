package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venuesList = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
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
      try{
        double capacityDoubled = Double.parseDouble(capacityInput);
        int capacity;
        if (capacityDoubled == (int)capacityDoubled){
          capacity = (int) capacityDoubled;
        } else {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "whole number" );
          return;
        } 
      } catch (NumberFormatException e) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "number" );
        return;
      }
      
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
