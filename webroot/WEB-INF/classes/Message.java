
public class Message {
  private String name;
  private String info;

  public Message(String name, String info) {
    this.name = name;
    this.info = info;
  }

  public String name() {
    return name;
  }

  public String info() {
    return info;
  }
}
