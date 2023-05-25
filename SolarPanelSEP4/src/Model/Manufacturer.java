package Model;

public class Manufacturer {
    private int manufacturerId;
    private String name;
    private String phone;
    private String email;
    private int cityId;

    // Constructors, getters, and setters

    public Manufacturer(int manufacturerId, String name, String phone,
                        String email, int cityId)
    {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
    }

    public Manufacturer()
    {

    }

    public int getManufacturerId()
    {
        return manufacturerId;
    }
    public void setManufacturerId(int manufacturerId)
    {
        this.manufacturerId = manufacturerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId=cityId;
}
}