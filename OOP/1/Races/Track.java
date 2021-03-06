package Races;

import java.util.*;

public class Track
{
    private int id;
    private String name;
    private String country;
    private ArrayList<Racer>winners;

    public Track(int _id, String t_name, String t_country)
    {
        id = _id;
        name = t_name;
        country = t_country;
        winners = new ArrayList<Racer>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int _id)
    {
        id = _id;
    }

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