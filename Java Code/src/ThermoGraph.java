public class ThermoGraph
{
  private String beginning_time;
  private String finishing_time;
  private String scale;
  private double water_flow;
  private double water_consumption;

  public ThermoGraph(String beginning_time, String finishing_time, String scale,
      double water_flow, double water_consumption)
  {
    this.beginning_time = beginning_time;
    this.finishing_time = finishing_time;
    this.scale = scale;
    this.water_flow = water_flow;
    this.water_consumption = water_consumption;
  }

  public String getBeginning_time()
  {
    return beginning_time;
  }

  public void setBeginning_time(String beginning_time)
  {
    this.beginning_time = beginning_time;
  }

  public String getFinishing_time()
  {
    return finishing_time;
  }

  public void setFinishing_time(String finishing_time)
  {
    this.finishing_time = finishing_time;
  }

  public String getScale()
  {
    return scale;
  }

  public void setScale(String scale)
  {
    this.scale = scale;
  }

  public double getWater_flow()
  {
    return water_flow;
  }

  public void setWater_flow(double water_flow)
  {
    this.water_flow = water_flow;
  }

  public double getWater_consumption()
  {
    return water_consumption;
  }

  public void setWater_consumption(double water_consumption)
  {
    this.water_consumption = water_consumption;
  }
}
