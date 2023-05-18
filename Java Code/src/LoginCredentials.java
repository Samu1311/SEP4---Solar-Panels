public class LoginCredentials
{
  private String username;
  private String password;
  private boolean employee_type;

  public LoginCredentials(String username, String password,
      boolean employee_type)
  {
    this.username = username;
    this.password = password;
    this.employee_type = employee_type;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public boolean isTechnicalTeam()
  {
    return employee_type;
  }

  public void setEmployee_type(boolean employee_type)
  {
    this.employee_type = employee_type;
  }
}
