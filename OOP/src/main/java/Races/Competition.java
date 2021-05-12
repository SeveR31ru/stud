package Races;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="competitions")
public class Competition
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="date")
    private Date date;

    @OneToOne (optional=false)
    //@JoinColumn (name="trackid")
    private Track track;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Racer>racers;

    @OneToOne (optional=false)
    //@JoinColumn (name="id")
    private Racer winner;

    public Competition() {}

    public Competition(String _name, Date _date, Track _track, List<Racer> _racers)
    {
        name = _name;
        date = _date;
        track = _track;
        racers = _racers;
    }

    public Competition(String _name, Date _date, Track _track)
    {
        name = _name;
        date = _date;
        track = _track;
        racers = new ArrayList<Racer>();
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

    public Date getDate()
    {
        return date;
    }

    public boolean setDate(Date _date) 
    {
        if(_date != null)
        {
            date = _date;
            return true;
        }
        return false;
    }

    public Track getTrack()
    {
        return track;
    }

    public boolean setTrack(Track newTrack) 
    {
        if(newTrack != null)
        {
            track = newTrack;
            return true;
        }
        return false;
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

    public boolean addRacer(Racer newRacer) 
    {
        if(newRacer != null)
        {
            racers.add(newRacer);
            return true;
        }
        return false;
    }

    public boolean removeRacer(Racer racer) 
    {
        if(racer != null)
        {
            racers.remove(racer);
            return true;
        }
        return false;
    }

    public Racer getWinner()
    {
        return winner;
    }

    public boolean setWinner(Racer newWinner) 
    {
        if(newWinner != null)
        {
            winner = newWinner;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String out = "Competition " + name + ". Track: " + track + ". Date: " + date;// + "\nList of racers:";
        /*for(int i = 0; i < racers.size(); i++)
        {
            out += "\nRacer: " + racers.get(i) + ". " + racers.get(i).getTeam();
        }*/
        return out;
    }
}