package Model;

public class SolarPanel
{
  private int solar_panel_id;
  private int roof_loc;
  private int series;

  public SolarPanel(int solar_panel_id,
      int roof_loc, int series)
  {
    this.solar_panel_id = solar_panel_id;
    this.roof_loc = roof_loc;
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

  public int getRoof_loc()
  {
    return roof_loc;
  }

  public void joinCoordinates()
  {

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