package sample.Models;


import java.util.Objects;

public class PatientMedicines {

  private long prescriptionId;
  private long patientId;
  private long doctorId;
  private long medicineId;
  private String reason;
  private long hourTime;
  private long doseAmount;


  public PatientMedicines(long prescriptionId, long patientId, long doctorId, long medicineId, String reason, long hourTime, long doseAmount) {
    this.prescriptionId = prescriptionId;
    this.patientId = patientId;
    this.doctorId = doctorId;
    this.medicineId = medicineId;
    this.reason = reason;
    this.hourTime = hourTime;
    this.doseAmount = doseAmount;
  }
  public PatientMedicines() {
    this.prescriptionId = 0;
    this.patientId = 0;
    this.doctorId = 0;
    this.medicineId = 0;
    this.reason = "";
    this.hourTime = 0;
    this.doseAmount = 0;
  }

  public long getPrescriptionId() {
    return prescriptionId;
  }

  public void setPrescriptionId(long prescriptionId) {
    this.prescriptionId = prescriptionId;
  }


  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }


  public long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(long doctorId) {
    this.doctorId = doctorId;
  }


  public long getMedicineId() {
    return medicineId;
  }

  public void setMedicineId(long medicineId) {
    this.medicineId = medicineId;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public long getHourTime() {
    return hourTime;
  }

  public void setHourTime(long hourTime) {
    this.hourTime = hourTime;
  }


  public long getDoseAmount() {
    return doseAmount;
  }

  public void setDoseAmount(long doseAmount) {
    this.doseAmount = doseAmount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PatientMedicines)) return false;
    PatientMedicines that = (PatientMedicines) o;
    return getHourTime() == that.getHourTime() && getDoseAmount() == that.getDoseAmount() && getReason().equals(that.getReason());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReason(), getHourTime(), getDoseAmount());
  }
}
