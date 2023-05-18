public class Model
{
  private int model_id;
  private int manufacturer_id;
  private String name;
  private int price;
  private String dimensions;
  private int solar_cell_area;

  public Model(int model_id, int manufacturer_id, String name, int price, String dimensions, int solar_cell_area){
    this.model_id = model_id;
    this.manufacturer_id = manufacturer_id;
    this.name = name;
    this.price = price;
    this.dimensions = dimensions;
    this.solar_cell_area = solar_cell_area;
  }

  public int getModel_id()
  {
    return model_id;
  }

  public void setModel_id(int model_id)
  {
    this.model_id = model_id;
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

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public String getDimensions()
  {
    return dimensions;
  }

  public void setDimensions(String dimensions)
  {
    this.dimensions = dimensions;
  }

  public int getSolar_cell_area()
  {
    return solar_cell_area;
  }

  public void setSolar_cell_area(int solar_cell_area)
  {
    this.solar_cell_area = solar_cell_area;
  }
}
