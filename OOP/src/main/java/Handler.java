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
                                .buildSessionFactory();
                                
        Session session = factory.getCurrentSession();
        try {		
            session.beginTransaction();
            List<Racer> racers = new ArrayList<Racer>( session.createCriteria(Racer.class).list() );
            List<Team> teams = new ArrayList<Team>( session.createCriteria(Team.class).list() );
            teams = removeCopies(teams);
            session.getTransaction().commit();

            System.out.println("\n===========RACERS===========\n");
            if(racers != null)
                for(Racer racer : racers)
                    System.out.println(racer);

            System.out.println("\n===========TEAMS===========\n");
            if(teams != null)
                for(Team team : teams)
                    System.out.println(team);

            System.out.println("\n===========================\n");
            
            
			/*System.out.println("Creating new racer object...");
            Team team = new Team(1, "Test");
            Racer racer = new Racer(2, "Paul", "Wall", team);
            Racer racer2 = new Racer(3, "Wes", "FVA", team);
            team.addRacer(racer);
            team.addRacer(racer2);
			session.beginTransaction();
	
			// save the racer object
            System.out.println("Saving the racer...");
            session.save(team);
			session.save(racer);
			session.save(racer2);
			
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