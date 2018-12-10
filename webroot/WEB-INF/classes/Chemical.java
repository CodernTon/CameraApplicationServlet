//package domain;

public class Chemical {
  private String name;
  private String chemical_group;

  public Chemical(String name, String chemical_group) {
    this.name=name;
    this.chemical_group=chemical_group;
  }

  public String name() {
    return name;
  }

  public String chemGroup() {
    return chemical_group;
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" + "Chemical group: " + this.chemical_group + "\n";
  }
}
