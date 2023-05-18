public class RoofLoc
{
  private int roof_loc_id;
  private int x_coord;
  private int y_coord;

  public RoofLoc(int roof_loc_id, int x_coord, int y_coord)
  {
    this.roof_loc_id = roof_loc_id;
    this.x_coord = x_coord;
    this.y_coord = y_coord;
  }

  public int getRoof_loc_id()
  {
    return roof_loc_id;
  }

  public void setRoof_loc_id(int roof_loc_id)
  {
    this.roof_loc_id = roof_loc_id;
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
}
