package sample.Models;

public class Shifts {

  // prop

  private long shiftId;
  private java.sql.Time startTime;
  private java.sql.Time endTime;

  // Constructor

  public Shifts(){
    shiftId=0;
    startTime=null;
    endTime=null;
  }
  public Shifts(long shiftId,java.sql.Time startTime,java.sql.Time endTime){
    this.shiftId = shiftId;
    this.startTime = startTime;
    this.endTime = endTime;
  }
  public Shifts(java.sql.Time startTime,java.sql.Time endTime){
    this.shiftId = shiftId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  // Getter

  public long getShiftId() {
    return shiftId;
  }
  public java.sql.Time getStartTime() {
    return startTime;
  }
  public java.sql.Time getEndTime() {
    return endTime;
  }


  // Setter

  public void setShiftId(long shiftId) {
    this.shiftId = shiftId;
  }
  public void setStartTime(java.sql.Time startTime) {
    this.startTime = startTime;
  }
  public void setEndTime(java.sql.Time endTime) {
    this.endTime = endTime;
  }

}
