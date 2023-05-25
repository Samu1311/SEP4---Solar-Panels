public class SolarPanel
{
  private int solar_panel_id;
  private int x_coord;
  private int y_coord;
  private String roof_loc;
  private String model_name;
  private int series;

  public SolarPanel(int solar_panel_id, int x_coord, int y_coord,
      String roof_loc, String model_name, int series)
  {
    this.solar_panel_id = solar_panel_id;
    this.x_coord = x_coord;
    this.y_coord = y_coord;
    this.roof_loc = roof_loc;
    this.model_name = model_name;
    this.series = series;
  }

  public int getSolar_panel_id()
  {
    return solar_panel_id;
  }

  public void setSolar_panel_id(int solar_panel_id)
  {
    this.solar_panel_id = solar_panel_id;
  }

  public int getX_coord()
  {
    return x_coord;
  }

  public void setX_coord(int x_coord)
  {
    this.x_coord = x_coord;
  }

  public int getY_coord()
  {
    return y_coord;
  }

  public void setY_coord(int y_coord)
  {
    this.y_coord = y_coord;
  }

  public String getRoof_loc()
  {
    return roof_loc;
  }

  public void joinCoordinates()
  {

  }

  public String getModel_name()
  {
    return model_name;
  }

  public void setModel_name(String model_name)
  {
    this.model_name = model_name;
  }

  public int getSeries()
  {
    return series;
  }

  public void setSeries(int series)
  {
    this.series = series;
  }
}