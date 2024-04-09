package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {
  private String cateringName;
  private int cateringCostPerPerson;

  public CateringService(String bookingReference, CateringType cateringType) {
    super(cateringType.getName(), bookingReference, cateringType.getCostPerPerson());
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + cateringType.getName() + ")", bookingReference);
  }

  @Override
  public int calculateCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateCost'");
  }
}
