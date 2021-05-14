package Races;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="tracks")
public class Track
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="country")
    private String country;

    public Track() {}

    public Track(String t_name, String t_country)
    {
        name = t_name;
        country = t_country;
    }

    public int getId()
    {
        return id;
    }

    /*public void setId(int _id)
    {
        id = _id;
    }*/

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String newCountry)
    {
        country = newCountry;
    }

    @Override
    public String toString()
    {
        return name + "(" + country + ")";
    }
}