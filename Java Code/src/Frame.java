public class Frame
{
  private int frame_id;
  private int series_id;

  public Frame(int frame_id, int series_id)
  {
    this.frame_id = frame_id;
    this.series_id = series_id;
  }

  public int getFrame_id()
  {
    return frame_id;
  }

  public void setFrame_id(int frame_id)
  {
    this.frame_id = frame_id;
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
