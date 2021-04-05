import Races.*;

import java.sql.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class Handler
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Racer.class)
                                .addAnnotatedClass(Team.class)
                                .addAnnotatedClass(Track.class)
                                .addAnnotatedClass(Competition.class)
                                .buildSessionFactory();
                                
        Session session = factory.getCurrentSession();
        try {		
            session.beginTransaction();
            List<Racer> racers = new ArrayList<Racer>( session.createCriteria(Racer.class).list() );
            List<Team> teams = new ArrayList<Team>( session.createCriteria(Team.class).list() );
            List<Track> tracks = new ArrayList<Track>( session.createCriteria(Track.class).list() );
            List<Competition> competitions = new ArrayList<Competition>( session.createCriteria(Competition.class).list() );
            session.getTransaction().commit();

            teams = removeCopies(teams);
            competitions = removeCopies(competitions);

            System.out.println("\n===========RACERS===========\n");
            if(racers != null)
                for(Racer racer : racers)
                    System.out.println(racer);

            System.out.println("\n===========TEAMS===========\n");
            if(teams != null)
                for(Team team : teams)
                {
                    System.out.println(team);
                    System.out.println(team.getRacers());
                }

            System.out.println("\n===========TRACKS===========\n");
                if(tracks != null)
                    for(Track track : tracks)
                        System.out.println(track);

            System.out.println("\n===========Comps===========\n");
                if(competitions != null)
                    for(Competition comp : competitions)
                        System.out.println(comp);

            System.out.println("\n===========================\n");
            
            
			/*System.out.println("Creating...");
            Team team = new Team("Test");
            Racer racer = new Racer("Paul", "Wall", team);
            Racer racer2 = new Racer("Wes", "FVA", team);
            Track track = new Track("TestTrack", "TestCountry");
            Competition comp = new Competition("Comp", new java.util.Date(), track);
            comp.addRacer(racer);
            comp.addRacer(racer2);
            comp.setWinner(racer2);
            track.addWinner(racer);
            team.addRacer(racer);
            team.addRacer(racer2);
            System.out.println(track.getWinners().get(0));
			session.beginTransaction();
	
			// save the racer object
            System.out.println("Saving...");
            session.save(team);
			session.save(racer);
            session.save(racer2);
            session.save(track);
            session.save(comp);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");*/
		}
		finally {
			factory.close();
		}
    }

    private static <T> List<T> removeCopies(List<T> list)
    {
        List<T> resultList = new ArrayList<T>();
        for(T el : list)
        {
            if(!resultList.contains(el))
                resultList.add(el);
        }
        return resultList;
    }
}