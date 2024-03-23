package nz.ac.auckland.se281;

public class Venue {

    private String venueName;
    private String venueCode;
    private int capacity;
    private double hireFee;


  public Venue(String venueName, String venueCode, int capacity, double hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
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
    return Double.toString(this.hireFee);
  }

}

