package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;

public class CateringService extends Service {

  public CateringService(String bookingReference, CateringType cateringType) {
    super(cateringType.getName(), cateringType.getCostPerPerson());
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Catering (" + serviceName + ")", bookingReference);
  }

  // Calculate the cost of the catering service based on the number of attendees
  @Override
  public int calculateCost(int attendees) {
    int totalCost = attendees * this.serviceCost;
    this.serviceCost = totalCost;
    return serviceCost;
  }
}
