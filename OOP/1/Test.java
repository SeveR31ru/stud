import Races.*;
import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        ArrayList<Racer>racers = new ArrayList<Racer>();
        Track t_test = new Track(0, "Sarta", "France");
        Team team_test = new Team(0, "The Greatest Racers");
        Racer r_test = new Racer(0, "Ken", "Block", null);
        r_test.setScore(50);
        Racer r_test2 = new Racer(0, "John", "Doe", null);
        r_test2.setScore(20);
        team_test.addRacer(r_test);
        racers.add(r_test);
        racers.add(r_test2);
        Competition c_test = new Competition(0, "Rally", new Date(), t_test, racers);

        System.out.println(c_test);
    }
}