
public class Message {
  private String substanceName;
  private String substanceInfo;

  public Message(String substanceName, String substanceInfo) {
    this.substanceName = substanceName;
    this.substanceInfo = substanceInfo;
  }

  public String substanceName() {
    return substanceName;
  }

  public String substanceInfo() {
    return substanceInfo;
  }
}
