package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private String cateringName;
  private int cateringCostPerPerson;

  public CateringService(String bookingReference, CateringType cateringType) {
    super(bookingReference);
    this.cateringName = cateringType.getName();
    this.cateringCostPerPerson = cateringType.getCostPerPerson();
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringName + ")", bookingReference);
  }

  public String getCateringName() {
    return cateringName;
  }

  public int getCateringCost() {
    return cateringCostPerPerson;
  }

  @Override
  public void addService() {
    // Add the catering service to the booking
    return;
  }
}
