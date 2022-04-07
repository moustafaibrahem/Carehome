package sample.Models;


public class Medicines {
  // prop

  private long medicineId;
  private String name;
  private String unit;
  private long amount;

  // Constructor

  public Medicines(long medicineId, String name, String unit, long amount) {
    this.medicineId = medicineId;
    this.name = name;
    this.unit = unit;
    this.amount = amount;
  }
  public Medicines() {
    this.medicineId = 0;
    this.name = "";
    this.unit = "";
    this.amount = 0;
  }

  // Getter

  public long getMedicineId() {
    return medicineId;
  }
  public String getName() {
    return name;
  }
  public String getUnit() {
    return unit;
  }
  public long getAmount() {
    return amount;
  }

  // Setter

  public void setMedicineId(long medicineId) {
    this.medicineId = medicineId;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setUnit(String unit) {
    this.unit = unit;
  }
  public void setAmount(long amount) {
    this.amount = amount;
  }


}
