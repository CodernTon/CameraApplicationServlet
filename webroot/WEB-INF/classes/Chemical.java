//package domain;

public class Chemical {
  private String name;
  private String casnr;
  private String egnr;
  private String priolevel;
  private String criteria;


  public static class ChemicalBuilder {
    private String name;
    private String casnr;
    private String egnr;
    private String priolevel;
    private String criteria;

    public ChemicalBuilder(String name, String criteria){
    this.name = name;
    this.criteria = criteria;
  }

    public ChemicalBuilder casNr(String casnr) {
      this.casnr = casnr;
      return this;
    }

    public ChemicalBuilder egNr(String egnr) {
      this.egnr = egnr;
      return this;
    }

    public ChemicalBuilder prioLevel(String priolevel) {
      this.priolevel = priolevel;
      return this;
    }

    public Chemical build() {
      return new Chemical(this);
    }
  }

  private Chemical(ChemicalBuilder cb) {
    this.name = cb.name;
    this.casnr = cb.casnr;
    this.egnr = cb.egnr;
    this.priolevel = cb.priolevel;
    this.criteria = cb.criteria;
  }

  public String name() {
    return name;
  }
  public String casnr() {
    return casnr;
  }
  public String egnr() {
    return egnr;
  }
  public String priolevel() {
    return priolevel;
  }
  public String criteria() {
    return criteria;
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n" +
    "Criteria: " + this.criteria + "\n" +
    "CAS number: " + this.casnr + "\n" +
    "EG number: " + this.egnr + "\n" +
    "Priority level: " + this.priolevel + "\n" +
    "----------" + "\n";
  }
}
