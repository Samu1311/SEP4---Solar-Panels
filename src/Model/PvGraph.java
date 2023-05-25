package Model;

public class PvGraph
{
  private String beginning_time;
  private String finishing_time;
  private String scale;
  private double voltage;
  private double energy_consumption;
  private double overproduced_energy;

  public PvGraph(String beginning_time, String finishing_time, String scale,
      double voltage, double energy_consumption, double overproduced_energy)
  {
    this.beginning_time = beginning_time;
    this.finishing_time = finishing_time;
    this.scale = scale;
    this.voltage = voltage;
    this.energy_consumption = energy_consumption;
    this.overproduced_energy = overproduced_energy;
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

  public double getVoltage()
  {
    return voltage;
  }

  public void setVoltage(double voltage)
  {
    this.voltage = voltage;
  }

  public double getEnergy_consumption()
  {
    return energy_consumption;
  }

  public void setEnergy_consumption(double energy_consumption)
  {
    this.energy_consumption = energy_consumption;
  }

  public double getOverproduced_energy()
  {
    return overproduced_energy;
  }

  public void setOverproduced_energy(double overproduced_energy)
  {
    this.overproduced_energy = overproduced_energy;
  }
}