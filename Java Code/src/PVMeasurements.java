import java.sql.Time;

public class PVMeasurements
{
  private Time timestamp;
  private int series_id;
  private double resistance;
  private double solar_flux;
  private double current;
  private double voltage;
  private double overproduced_energy;
  private double p_out;
  private double p_in;
  private double efficiency;

  public PVMeasurements(Time timestamp, int series_id, double resistance,
      double solar_flux, double current, double voltage,
      double overproduced_energy, double p_out, double p_in, double efficiency)
  {
    this.timestamp = timestamp;
    this.series_id = series_id;
    this.resistance = resistance;
    this.solar_flux = solar_flux;
    this.current = current;
    this.voltage = voltage;
    this.overproduced_energy = overproduced_energy;
    this.p_out = p_out;
    this.p_in = p_in;
    this.efficiency = efficiency;
  }

  public Time getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Time timestamp)
  {
    this.timestamp = timestamp;
  }

  public int getSeries_id()
  {
    return series_id;
  }

  public void setSeries_id(int series_id)
  {
    this.series_id = series_id;
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

  public double getCurrent()
  {
    return current;
  }

  public void setCurrent(double current)
  {
    this.current = current;
  }

  public double getVoltage()
  {
    return voltage;
  }

  public void setVoltage(double voltage)
  {
    this.voltage = voltage;
  }

  public double getOverproduced_energy()
  {
    return overproduced_energy;
  }

  public void setOverproduced_energy(double overproduced_energy)
  {
    this.overproduced_energy = overproduced_energy;
  }

  public double getP_out()
  {
    return p_out;
  }

  public void setP_out(double p_out)
  {
    this.p_out = p_out;
  }

  public double getP_in()
  {
    return p_in;
  }

  public void setP_in(double p_in)
  {
    this.p_in = p_in;
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
