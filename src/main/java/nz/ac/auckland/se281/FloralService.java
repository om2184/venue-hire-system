package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.FloralType;

public class FloralService extends Service {

  public FloralService(String bookingReference, FloralType floralType) {
    super(floralType.getName(), floralType.getCost());
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
        "Floral (" + serviceName + ")", bookingReference);
  }

  @Override
  public int calculateCost(int attendees) {
    return serviceCost;
  }
}
