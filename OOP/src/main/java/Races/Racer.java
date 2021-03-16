package Races;

import javax.annotation.processing.Generated;
import javax.persistance.*;

@Entity
@Table(name = "racers")
public class Racer
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    private Team team;
    @Column(name="score")
    private int score = 0;

    public Racer(int _id, String _name, String _surname, Team _team)
    {
        id = _id;
        name = _name;
        surname = _surname;
        team = _team;
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

    public String getSurname()
    {
        return surname;
    }
    
    public void setSurname(String newSurname)
    {
        surname = newSurname;
    }

    public Team getTeam()
    {
        return team;
    }

    public boolean setTeam(Team newTeam)
    {
        if(newTeam != null)
        {
            team = newTeam;
            return true;
        }
        return false;
    }

    public int getScore() 
    {
        return score;
    }

    public void setScore(int newScore)
    {
        score = newScore;
    }

    @Override
    public String toString()
    {
        return name + " " + surname + ", score: " + score;
    }
}