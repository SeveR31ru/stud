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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Racer>winners;

    public Track() {}

    public Track(String t_name, String t_country)
    {
        name = t_name;
        country = t_country;
        winners = new ArrayList<Racer>();
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

    public ArrayList<Racer> getWinners()
    {
        return new ArrayList<Racer>(winners);
    }

    public boolean setWinners(ArrayList<Racer>newWinners)
    {
        if(newWinners != null)
        {
            winners = newWinners;
            return true;
        }
        return false;
    }

    public boolean addWinner(Racer newWinner)
    {
        if(newWinner != null)
        {
            winners.add(newWinner);
            return true;
        }
        return false;
    }

    public boolean removeWinner(Racer racer)
    {
        int index = winners.indexOf(racer);
        if(index != -1)
        {
            winners.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return name + "(" + country + ")";
    }
}