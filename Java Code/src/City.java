public class City
{
  private int city_id;
  private String name;
  private int postal_code;
  private int country_id;

  public City(int city_id, String name, int postal_code, int country_id){
    this.city_id = city_id;
    this.name = name;
    this.postal_code = postal_code;
    this.country_id = country_id;
  }

  public int getCity_id()
  {
    return city_id;
  }

  public void setCity_id(int city_id)
  {
    this.city_id = city_id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getPostal_code()
  {
    return postal_code;
  }

  public void setPostal_code(int postal_code)
  {
    this.postal_code = postal_code;
  }

  public int getCountry_id()
  {
    return country_id;
  }

  public void setCountry_id(int country_id)
  {
    this.country_id = country_id;
  }
}

