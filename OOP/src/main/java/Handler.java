import Races.*;

import java.sql.*;
import java.util.*;

public class Handler
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
        Competition c_test = new Competition(0, "Rally", new java.util.Date(), t_test, racers);

        System.out.println(c_test + "\n\nRacers in database:");

        MyConnection mysqlCon = new MyConnection();
        mysqlCon.makeConnection();
        ResultSet rs = mysqlCon.makeQuery("select name, surname from racers;");
        try
        {
            while(rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
        catch(SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
        mysqlCon.closeConnection();
    }

    private static class MyConnection
    {
        private static final String url = "jdbc:mysql://localhost:3306/racers";
        private static final String user = "root";
        private static final String password = "password";
        private Connection connection;

        public MyConnection() {}

        public void makeConnection()
        {
            try
            {
                connection = DriverManager.getConnection(url, user, password);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        public Connection getConnection()
        {
            return connection;
        }

        public ResultSet makeQuery(String query)
        {
            ResultSet rs = null;
            try
            {
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
            }
            catch(SQLException sqlEx)
            {
                sqlEx.printStackTrace();
            }
            return rs;
        }

        public void closeConnection()
        {
            try
            {
                connection.close();
            }
            catch(SQLException sqlEx){}
        }
    }
}