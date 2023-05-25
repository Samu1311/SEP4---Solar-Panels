public class Model
{
  private int model_id;
  private String name;
  private String manufacturer_name;
  private int price;
  private String dimensions;
  private int solar_cell_area;
  private String panel_type;

  public Model(int model_id, String name, String manufacturer_name, int price,
      String dimensions, int solar_cell_area, String panel_type)
  {
    this.model_id = model_id;
    this.name = name;
    this.manufacturer_name = manufacturer_name;
    this.price = price;
    this.dimensions = dimensions;
    this.solar_cell_area = solar_cell_area;
    this.panel_type = panel_type;
  }

  public int getModel_id()
  {
    return model_id;
  }

  public void setModel_id(int model_id)
  {
    this.model_id = model_id;
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

  public void setManufacturer_name(String manufacturer_name)
  {
    this.manufacturer_name = manufacturer_name;
  }

  public String getManufacturer_name()
  {
    return manufacturer_name;
  }

  public String getPanel_type()
  {
    return panel_type;
  }

  public void setPanel_type(String panel_type)
  {
    this.panel_type = panel_type;
  }
}
