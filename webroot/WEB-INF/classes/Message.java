/*This is the Message-class. Messages about chemicals that aren't found in
the database are stored in these objects.
*/

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
