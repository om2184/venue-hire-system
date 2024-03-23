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
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }


  public String getVenueCode() {
    return venueCode;
  }


  public String getVenueName() {
    return venueName;
  }

}

