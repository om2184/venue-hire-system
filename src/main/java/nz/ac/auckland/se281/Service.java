package nz.ac.auckland.se281;

public abstract class Service {
  protected String serviceName;
  protected int serviceCost;

  public Service(String serviceName, int serviceCost) {
    this.serviceName = serviceName;
    this.serviceCost = serviceCost;
  }

  public abstract int calculateCost(int attendees);
}
