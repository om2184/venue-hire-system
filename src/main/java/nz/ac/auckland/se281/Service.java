package nz.ac.auckland.se281;

public abstract class Service {
  protected String serviceName;
  protected int serviceCost;

  // Constructor for the service
  public Service(String serviceName, int serviceCost) {
    this.serviceName = serviceName;
    this.serviceCost = serviceCost;
  }

  // Calculate the cost of the service based on the number of attendees
  public abstract int calculateCost(int attendees);

  // Get the name of the service
  public String getServiceName() {
    return serviceName;
  }

  // Get the cost of the service
  public int getServiceCost() {
    return serviceCost;
  }
}
