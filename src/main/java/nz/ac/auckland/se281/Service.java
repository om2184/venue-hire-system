package nz.ac.auckland.se281;

public abstract class Service {
  protected String serviceName;
  protected String bookingReference;
  protected int serviceCost;

  public Service(String bookingReference, String serviceName, int serviceCost) {
    this.bookingReference = bookingReference;
    this.serviceName = serviceName;
    this.serviceCost = serviceCost;
  }

  public abstract int calculateCost();
}
