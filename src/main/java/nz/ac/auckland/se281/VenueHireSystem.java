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
      int capacity;
      // Check if the capacity is a valid number
      try{
        double capacityDoubled = Double.parseDouble(capacityInput);
        if (capacityDoubled == (int)capacityDoubled){
          capacity = (int) capacityDoubled;
          if (capacity <= 0) {
            MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive" );
            return;
          }
        } else {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " whole" );
          return;
        } 
      } catch (NumberFormatException e) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
        return;
      }
      
      // Check if the hire fee is a valid number
      int hireFee;
      try{
        double hireFeeDoubled = Double.parseDouble(hireFeeInput);
        if (hireFeeDoubled == (int)hireFeeDoubled){
          hireFee = (int) hireFeeDoubled;
          if (hireFee <= 0) {
            MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive" );
            return;
          }
        } else {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " whole" );
          return;
        } 
      } catch(NumberFormatException e) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "" );
        return;
      }
     
    Venue newVenue = new Venue(venueName, venueCode, capacity, hireFee);
    
    
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
