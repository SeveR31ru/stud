package Races;

public class Victory
{
    private Racer winner;
    private Competition competition;

    public Victory(Racer _winner, Competition _comp)
    {
        winner = _winner;
        competition = _comp;
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

    public Competition getCompetition()
    {
        return competition;
    }

    public boolean setCompetition(Competition newCompetition)
    {
        if(newCompetition != null)
        {
            competition = newCompetition;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        String out = competition.toString();
        out += "\nWinner: " + winner;
        return out;
    }
}