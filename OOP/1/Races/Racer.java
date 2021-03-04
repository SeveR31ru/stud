package Races;

public class Racer
{
    private int id;
    private String name = "Unknown";
    private String surname = "Unknown";
    private Team team;
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