package Races;

import java.util.*;

public class Competition
{
    private String name = "Unknown";
    private Date dateOfCompetition;
    private Track track;
    private ArrayList<Racer>racers;
    private Racer winner;

    public Competition(String _name, Date date, Track _track, ArrayList<Racer> _racers)
    {
        name = _name;
        dateOfCompetition = date;
        track = _track;
        racers = _racers;
    }

    public Competition(String _name, Track _track, ArrayList<Racer> _racers)
    {
        name = _name;
        dateOfCompetition = new Date();
        track = _track;
        racers = _racers;
    }

    public Competition(String _name)
    {
        name = _name;
        dateOfCompetition = new Date();
        racers = new ArrayList<Racer>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public Date getDate()
    {
        return dateOfCompetition;
    }

    public void setDate(Date date) 
    {
        dateOfCompetition = date;
    }

    public Track getTrack()
    {
        return track;
    }

    public void setTrack(Track newTrack) 
    {
        track = newTrack;
    }

    public void setRacers(ArrayList<Racer> newRacers) 
    {
        racers = newRacers;
    }

    public ArrayList<Racer> getRacers()
    {
        return new ArrayList<Racer>(racers);
    }

    public Racer getWinner()
    {
        return winner;
    }

    public void setWinner(Racer newWinner) 
    {
        winner = newWinner;
    }

    @Override
    public String toString()
    {
        String out = "Competition " + name + ". Track: " + track + ". Date: " + dateOfCompetition + "\nList of racers:";
        for(int i = 0; i < racers.size(); i++)
        {
            out += "\nRacer: " + racers.get(i) + ". " + racers.get(i).getTeam();
        }
        return out;
    }
}