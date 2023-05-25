public class PhotovoltaicSeries
{
  private int series_id;
  private double voltage;
  private double current;
  private double resistance;
  private double solar_flux;
  private double efficiency;

  public PhotovoltaicSeries(int series_id, double voltage, double current,
      double resistance, double solar_flux, double efficiency)
  {
    this.series_id = series_id;
    this.voltage = voltage;
    this.current = current;
    this.resistance = resistance;
    this.solar_flux = solar_flux;
    this.efficiency = efficiency;
  }

  public int getSeries_id()
  {
    return series_id;
  }

  public void setSeries_id(int series_id)
  {
    this.series_id = series_id;
  }

  public double getVoltage()
  {
    return voltage;
  }

  public void setVoltage(double voltage)
  {
    this.voltage = voltage;
  }

  public double getCurrent()
  {
    return current;
  }

  public void setCurrent(double current)
  {
    this.current = current;
  }

  public double getResistance()
  {
    return resistance;
  }

  public void setResistance(double resistance)
  {
    this.resistance = resistance;
  }

  public double getSolar_flux()
  {
    return solar_flux;
  }

  public void setSolar_flux(double solar_flux)
  {
    this.solar_flux = solar_flux;
  }

  public double getEfficiency()
  {
    return efficiency;
  }

  public void setEfficiency(double efficiency)
  {
    this.efficiency = efficiency;
  }
}
