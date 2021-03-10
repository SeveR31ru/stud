package Races;

import java.util.*;

public class Team
{
    private int id;
    private String name;
    private ArrayList<Racer>racers;

    public Team(int _id, String t_name, ArrayList<Racer> t_racers)
    {
        id = _id;
        name = t_name;
        racers = t_racers;
    }

    public Team(int _id, String t_name)
    {
        id = _id;
        name = t_name;
        racers = new ArrayList<Racer>();
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

    public ArrayList<Racer> getRacers()
    {
        return new ArrayList<Racer>(racers);
    }

    public boolean setRacers(ArrayList<Racer> newRacers)
    {
        if(newRacers != null)
        {
            racers = newRacers;
            return true;
        }
        return false;
    }

    public boolean addRacer(Racer racer)
    {
        if(racer != null)
        {
            racers.add(racer);
            racer.setTeam(this);
            return true;
        }
        return false;
    }

    public boolean removeRacer(Racer racer)
    {
        int index = racers.indexOf(racer);
        if(index != -1)
        {
            racers.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        /*int score = 0;
        for(int i = 0; i < racers.size(); i++)
            score += racers.get(i).getScore();*/
        return "Team: " + name;// + ". Total score: " + score + ". Number of racers in team: " + racers.size();
    }
}