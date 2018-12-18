//package domain;

public class Chemical {
  private String substance;
  private String casnr;
  private String egnr;
  private String priolevel;
  private String criteria;


  public static class ChemicalBuilder {
    private String substance;
    private String casnr;
    private String egnr;
    private String priolevel;
    private String criteria;

    public ChemicalBuilder(String substance, String criteria){
    this.substance = substance;
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
    this.substance = cb.substance;
    this.casnr = cb.casnr;
    this.egnr = cb.egnr;
    this.priolevel = cb.priolevel;
    this.criteria = cb.criteria;
  }

  public String substance() {
    return substance;
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
    return "Substance: " + this.substance + "\n" +
    "Criteria: " + this.criteria + "\n" +
    "CAS number: " + this.casnr + "\n" +
    "EG number: " + this.egnr + "\n" +
    "Priority level: " + this.priolevel + "\n" +
    "----------" + "\n";
  }
}
