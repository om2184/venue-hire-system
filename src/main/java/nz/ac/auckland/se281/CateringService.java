package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private String cateringName;
  private int cateringCostPerPerson;

  public CateringService(String bookingReference, CateringType cateringType) {
    super(cateringType.getName(), cateringType.getCostPerPerson());
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType.getName() + ")", bookingReference);
  }

  @Override
  public int calculateCost(int attendees) {
    int totalCost = attendees * cateringCostPerPerson;
    return totalCost;
  }
}
