import Races.*;
import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        ArrayList<Racer>racers = new ArrayList<Racer>();
        Track t_test = new Track("Sarta", "France");
        Team team_test = new Team("The Greatest Racers");
        Racer r_test = new Racer(0, "Ken", "Block", null);
        team_test.addRacer(r_test);
        racers.add(r_test);
        Competition c_test = new Competition("Rally", t_test, racers);

        System.out.println(c_test);
    }
}