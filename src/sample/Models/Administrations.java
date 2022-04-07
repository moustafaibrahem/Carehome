package sample.Models;


import java.util.Date;

public class Administrations {

  // prop
  private long id;
  private long prescriptionId;
  private long nurseId;
  private Date createdAt;
  private long def;

  // Constructor
  public Administrations(long id, long prescriptionId, long nurseId, long def) {
    this.id = id;
    this.prescriptionId = prescriptionId;
    this.nurseId = nurseId;
    this.def = def;
  }

  public Administrations() {
    this.id = 0;
    this.prescriptionId = 0;
    this.nurseId = 0;
    this.def = 0;
  }

  // Getter
  public long getDef() {
    return def;
  }
  public long getId() {
    return id;
  }
  public long getPrescriptionId() {
    return prescriptionId;
  }
  public long getNurseId() {
    return nurseId;
  }
  public Date getCreatedAt() {
    return createdAt;
  }

  // Setter
  public void setId(long id) {
    this.id = id;
  }
  public void setDef(long def) {
    this.def = def;
  }
  public void setPrescriptionId(long prescriptionId) {
    this.prescriptionId = prescriptionId;
  }
  public void setNurseId(long nurseId) {
    this.nurseId = nurseId;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }



}
