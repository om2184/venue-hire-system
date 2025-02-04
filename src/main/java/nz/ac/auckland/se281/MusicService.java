package nz.ac.auckland.se281;

public class MusicService extends Service {

  // add music service to the booking and print confirmation message
  public MusicService(String bookingReference) {
    super("Music", 500);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(serviceName, bookingReference);
  }

  @Override
  public int calculateCost(int attendees) {
    return serviceCost;
  }
}
