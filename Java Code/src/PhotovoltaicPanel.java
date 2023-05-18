public class PhotovoltaicPanel
{
  private int solar_panel_id;
  private int roof_loc_id;
  private int model_id;
  private int series_id;

  public PhotovoltaicPanel(int solar_panel_id, int roof_loc_id, int model_id, int series_id){
    this.solar_panel_id = solar_panel_id;
    this.roof_loc_id = roof_loc_id;
    this.model_id = model_id;
    this.series_id = series_id;
  }

  public int getSolar_panel_id()
  {
    return solar_panel_id;
  }

  public void setSolar_panel_id(int solar_panel_id)
  {
    this.solar_panel_id = solar_panel_id;
  }

  public int getRoof_loc_id()
  {
    return roof_loc_id;
  }

  public void setRoof_loc_id(int roof_loc_id)
  {
    this.roof_loc_id = roof_loc_id;
  }

  public int getModel_id()
  {
    return model_id;
  }

  public void setModel_id(int model_id)
  {
    this.model_id = model_id;
  }

  public int getSeries_id()
  {
    return series_id;
  }

  public void setSeries_id(int series_id)
  {
    this.series_id = series_id;
  }
}
