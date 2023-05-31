package Model;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Manufacturer_log {

  private int manufacturer_id;
  private String name;
  private String phone;
  private String email;
  private String city;
  private Timestamp timestamp;

  private String info;

  private String action;


  public Manufacturer_log(Timestamp timestamp, int manufacturer_id, String name, String phone, String email, String city, String info, String action ){
    this.timestamp = timestamp;
    this.manufacturer_id = manufacturer_id;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.city = city;
    this.info = info;
    this.action = action;
  }

  public int getManufacturer_id()
  {
    return manufacturer_id;
  }

  public void setManufacturer_id(int manufacturer_id)
  {
    this.manufacturer_id = manufacturer_id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity_name(String city)
  {
    this.city= city;
  }

  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }

  public String getInfo()
  {
    return info;
  }

  public void setInfo(String info)
  {
    this.info = info;
  }

  public String getAction()
  {
    return action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }
}

