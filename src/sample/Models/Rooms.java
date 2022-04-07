package sample.Models;


public class Rooms {

  // prop

  private long roomId;
  private String name;
  private String typeId;
  private long wardId;

  // Constructor

  public Rooms(long roomId, String name, String typeId, long wardId) {
    this.roomId = roomId;
    this.name = name;
    this.typeId = typeId;
    this.wardId = wardId;
  }

  public Rooms() {
    this.roomId = 0;
    this.name = "";
    this.typeId = "";
    this.wardId = 0;
  }

  // Getter

  public long getRoomId() {
    return roomId;
  }
  public String getName() {
    return name;
  }
  public String getTypeId() {
    return typeId;
  }
  public long getWardId() {
    return wardId;
  }

  // Setter

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }
  public void setWardId(long wardId) {
    this.wardId = wardId;
  }

}
