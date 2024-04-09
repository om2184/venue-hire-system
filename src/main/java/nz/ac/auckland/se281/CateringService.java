package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private CateringType cateringType;
  private String cateringDescription;
  private String cateringCost;

  public CateringService(String bookingReference, CateringType cateringType) {
    super(bookingReference);
    this.cateringType = cateringType;
    this.cateringDescription = cateringDescription;
    this.cateringCost = cateringCost;
  }

  public CateringType getCateringType() {
    return cateringType;
  }

  public String getCateringDescription() {
    return cateringDescription;
  }

  public String getCateringCost() {
    return cateringCost;
  }

  @Override
  public void addService() {
    // Add the catering service to the booking
    return;
  }
}
