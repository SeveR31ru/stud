package Races;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.persistence.*;

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
        System.out.println("Loading...");
        loadData();
    }

    public List<Racer> getRacers() 
    {
        return racers;
    }

    public boolean addRacer(Racer racer)
    {
        if(racer != null)
        {
            racers.add(racer);
            saveData(racer);
            return true;
        }
        return false;
    }

    public boolean removeRacer(Racer racer)
    {
        int index = racers.indexOf(racer);
        if(index != -1)
        {
            removeData(racer);
            racers = loadRacers();
            teams = loadTeams();
            comps = loadComps();
            return true;
        }
        return false;
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
            saveData(team);
            return true;
        }
        return false;
    }

    public boolean removeTeam(Team team)
    {
        int index = teams.indexOf(team);
        if(index != -1)
        {
            removeData(team);
            racers = loadRacers();
            teams = loadTeams();
            return true;
        }
        return false;
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
            saveData(track);
            return true;
        }
        return false;
    }

    public boolean removeTrack(Track track)
    {
        int index = tracks.indexOf(track);
        if(index != -1)
        {
            removeData(track);
            tracks = loadTracks();
            comps = loadComps();
            return true;
        }
        return false;
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
            saveData(comp);
            return true;
        }
        return false;
    }

    public boolean removeComp(Competition comp)
    {
        int index = comps.indexOf(comp);
        if(index != -1)
        {
            comps.remove(index);
            removeData(comp);
            return true;
        }
        return false;
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
            racers = loadRacers();
            teams = loadTeams();
            tracks = loadTracks();
            comps = loadComps();
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

    public <T> void saveList(List<T> list)
    {
        for(T el : list)
            saveData(el);
    }

    public <T> void saveData(T data)
    {
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Racer.class)
                                .addAnnotatedClass(Team.class)
                                .addAnnotatedClass(Track.class)
                                .addAnnotatedClass(Competition.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(data);
        session.getTransaction().commit();
    }

    public <T> void removeList(List<T> list)
    {
        for(T el : list)
            removeData(el);
    }

    public <T> void removeData(T data)
    {
        factory = new Configuration()
								.configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Racer.class)
                                .addAnnotatedClass(Team.class)
                                .addAnnotatedClass(Track.class)
                                .addAnnotatedClass(Competition.class)
                                .buildSessionFactory();
                                
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(data);
        session.getTransaction().commit();
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

    public Racer getRacerByID(int id)
    {
        for(Racer r : racers)
            if(r.getId() == id)
                return r;
        return null;
    }

    public Team getTeamByID(int id)
    {
        for(Team t : teams)
            if(t.getId() == id)
                return t;
        return null;
    }

    public Track getTrackByID(int id)
    {
        for(Track t : tracks)
            if(t.getId() == id)
                return t;
        return null;
    }

    public Competition getCompByID(int id)
    {
        for(Competition c : comps)
            if(c.getId() == id)
                return c;
        return null;
    }
}