package Races;

import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class RaceHandler
{
    private List<Racer>racers = null;
    private List<Team>teams = null;
    private List<Track>tracks = null;
    private List<Competition>comps = null;

    private SessionFactory factory;
    private Session session;

    public RaceHandler()
    {
        loadData();
    }

    public List<Racer> getRacers() 
    {
        //return new ArrayList<>(racers);
        return racers;
    }

    public boolean addRacer(Racer racer)
    {
        if(racer != null)
        {
            racers.add(racer);
            return true;
        }
        return false;
    }

    public void setRacers(List<Racer> racers) 
    {
        this.racers = racers;
    }

    public List<Team> getTeams() 
    {
        return teams;
    }

    public boolean addTeam(Team team)
    {
        if(team != null)
        {
            teams.add(team);
            return true;
        }
        return false;
    }

    public void setTeams(List<Team> teams) 
    {
        this.teams = teams;
    }

    public List<Track> getTracks() 
    {
        return tracks;
    }

    public boolean addTrack(Track track)
    {
        if(track != null)
        {
            tracks.add(track);
            return true;
        }
        return false;
    }

    public void setTracks(List<Track> tracks) 
    {
        this.tracks = tracks;
    }

    public List<Competition> getComps() 
    {
        return comps;
    }

    public boolean addComp(Competition comp)
    {
        if(comp != null)
        {
            comps.add(comp);
            return true;
        }
        return false;
    }

    public void setComps(List<Competition> comps) 
    {
        this.comps = comps;
    }

    public void loadData()
    {
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Racer.class)
                                .addAnnotatedClass(Team.class)
                                .addAnnotatedClass(Track.class)
                                .addAnnotatedClass(Competition.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        try 
        {		
            /*racers = loadRacers();
            teams = loadTeams();
            tracks = loadTracks();
            comps = loadComps();*/
            session.beginTransaction();
            racers = new ArrayList<Racer>( session.createCriteria(Racer.class).list() );
            teams = new ArrayList<Team>( session.createCriteria(Team.class).list() );
            tracks = new ArrayList<Track>( session.createCriteria(Track.class).list() );
            comps = new ArrayList<Competition>( session.createCriteria(Competition.class).list() );
            session.getTransaction().commit();
            
            teams = removeCopies(teams);
            comps = removeCopies(comps);

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
                if(comps != null)
                    for(Competition comp : comps)
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

    public List<Racer> loadRacers()
    {
        List<Racer> loadedRacers = null;
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Racer.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        try 
        {		
            session.beginTransaction();
            loadedRacers = new ArrayList<Racer>( session.createCriteria(Racer.class).list() );
            session.getTransaction().commit();
        }
        catch(Throwable t)
        {
            System.out.println(t);
        }
        finally
        {
            factory.close();
        }
        return removeCopies(loadedRacers);
    }
    
    public List<Team> loadTeams()
    {
        List<Team> loadedTeams = null;
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Team.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        try 
        {		
            session.beginTransaction();
            loadedTeams = new ArrayList<Team>( session.createCriteria(Team.class).list() );
            session.getTransaction().commit();
        }
        catch(Throwable t)
        {
            System.out.println(t);
        }
        finally
        {
            factory.close();
        }
        return removeCopies(loadedTeams);
    }
    
    public List<Track> loadTracks()
    {
        List<Track> loadedTracks = null;
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Track.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        try 
        {		
            session.beginTransaction();
            loadedTracks = new ArrayList<Track>( session.createCriteria(Track.class).list() );
            session.getTransaction().commit();
        }
        catch(Throwable t)
        {
            System.out.println(t);
        }
        finally
        {
            factory.close();
        }
        return removeCopies(loadedTracks);
    }
    
    public List<Competition> loadComps()
    {
        List<Competition> loadedComps = null;
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Competition.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        try 
        {		
            session.beginTransaction();
            loadedComps = new ArrayList<Competition>( session.createCriteria(Competition.class).list() );
            session.getTransaction().commit();
        }
        catch(Throwable t)
        {
            System.out.println(t);
        }
        finally
        {
            factory.close();
        }
        return removeCopies(loadedComps);
    }

    private <T> List<T> removeCopies(List<T> list)
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