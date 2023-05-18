import java.sql.Time;

public class ThermoMeasurements
{
  private Time timestamp;
  private int series_id;
  private int collection_time_period;
  private double collected_water_mass;
  private double outdoor_temperature;
  private double hot_water_temperature;
  private double cold_water_temperature;
  private double solar_flux;
  private double water_flow;
  private double p_out;
  private double p_in;
  private double efficiency;

  public ThermoMeasurements(int series_id, Time timestamp,
      int collection_time_period, double collected_water_mass,
      double outdoor_temperature, double hot_water_temperature,
      double cold_water_temperature, double solar_flux, double water_flow,
      double p_out, double p_in, double efficiency)
  {
    this.series_id = series_id;
    this.timestamp = timestamp;
    this.collection_time_period = collection_time_period;
    this.collected_water_mass = collected_water_mass;
    this.outdoor_temperature = outdoor_temperature;
    this.hot_water_temperature = hot_water_temperature;
    this.cold_water_temperature = cold_water_temperature;
    this.solar_flux = solar_flux;
    this.water_flow = water_flow;
    this.p_out = p_out;
    this.p_in = p_in;
    this.efficiency = efficiency;
  }

  public int getMeasurement_id()
  {
    return measurement_id;
  }

  public void setMeasurement_id(int measurement_id)
  {
    this.measurement_id = measurement_id;
  }

  public Time getTimestamp()
  {
    return timestamp;
  }

  public void setTimestamp(Time timestamp)
  {
    this.timestamp = timestamp;
  }

  public int getCollection_time_period()
  {
    return collection_time_period;
  }

  public void setCollection_time_period(int collection_time_period)
  {
    this.collection_time_period = collection_time_period;
  }

  public double getCollected_water_mass()
  {
    return collected_water_mass;
  }

  public void setCollected_water_mass(double collected_water_mass)
  {
    this.collected_water_mass = collected_water_mass;
  }

  public double getOutdoor_temperature()
  {
    return outdoor_temperature;
  }

  public void setOutdoor_temperature(double outdoor_temperature)
  {
    this.outdoor_temperature = outdoor_temperature;
  }

  public double getHot_water_temperature()
  {
    return hot_water_temperature;
  }

  public void setHot_water_temperature(double hot_water_temperature)
  {
    this.hot_water_temperature = hot_water_temperature;
  }

  public double getCold_water_temperature()
  {
    return cold_water_temperature;
  }

  public void setCold_water_temperature(double cold_water_temperature)
  {
    this.cold_water_temperature = cold_water_temperature;
  }

  public double getSolar_flux()
  {
    return solar_flux;
  }

  public void setSolar_flux(double solar_flux)
  {
    this.solar_flux = solar_flux;
  }

  public double getWater_flow()
  {
    return water_flow;
  }

  public void setWater_flow(double water_flow)
  {
    this.water_flow = water_flow;
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
