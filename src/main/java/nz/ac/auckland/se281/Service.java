package nz.ac.auckland.se281;

public abstract class Service {
  protected String bookingReference;

  public Service(String bookingReference) {
    this.bookingReference = bookingReference;
  }

  public abstract void addService();

  @Override
  public String toString() {
    return "Service{" + "bookingReference='" + bookingReference + '\'' + '}';
  }
}
