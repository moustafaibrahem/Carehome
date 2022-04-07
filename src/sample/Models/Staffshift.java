package sample.Models;


public class Staffshift {

  // prop

  private long id;
  private long staffId;
  private long shiftId;

  // Constructor

  public Staffshift(long id, long staffId, long shiftId) {
    this.id = id;
    this.staffId = staffId;
    this.shiftId = shiftId;
  }

  public Staffshift() {
    this.id = 0;
    this.staffId = 0;
    this.shiftId = 0;
  }

  // Getter

  public long getId() {
    return id;
  }
  public long getStaffId() {
    return staffId;
  }
  public long getShiftId() {
    return shiftId;
  }

  // Setter

  public void setId(long id) {
    this.id = id;
  }
  public void setStaffId(long staffId) {
    this.staffId = staffId;
  }
  public void setShiftId(long shiftId) {
    this.shiftId = shiftId;
  }


}
