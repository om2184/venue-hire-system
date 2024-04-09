package nz.ac.auckland.se281;

public class musicService extends Service {

  public musicService(String bookingReference) {
    super("music", 500);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  @Override
  public int calculateCost(int attendees) {
    return 500;
  }
}
