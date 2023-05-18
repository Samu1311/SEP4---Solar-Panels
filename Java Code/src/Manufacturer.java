public class Manufacturer
{
  private int city_id;
  private int manufacturer_id;
  private String name;
  private int phone;
  private String email;

  public Manufacturer(int city_id, int manufacturer_id, String name, int phone, String email){
    this.city_id = city_id;
    this.manufacturer_id = manufacturer_id;
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public int getCity_id()
  {
    return city_id;
  }

  public void setCity_id(int city_id)
  {
    this.city_id = city_id;
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

  public int getPhone()
  {
    return phone;
  }

  public void setPhone(int phone)
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
}
