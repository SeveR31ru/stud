//import Races.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI
{
    public static void main(String[] args)
    {
        new MainMenu();
    }
}

class MainMenu
{
    private JFrame mainFrame;

    private DefaultTableModel racersModel;
    private JTable racersTable;
    private JScrollPane racersPane;
    private RacerViewer racerViewer;

    private DefaultTableModel teamsModel;
    private JTable teamsTable;
    private JScrollPane teamsPane;
    private TeamViewer teamViewer;
    
    private DefaultTableModel tracksModel;
    private JTable tracksTable;
    private JScrollPane tracksPane;
    private TrackViewer trackViewer;
    
    private DefaultTableModel compsModel;
    private JTable compsTable;
    private JScrollPane compsPane;
    private CompViewer compViewer;

    public MainMenu()
    {
        mainFrame = new JFrame();
        mainFrame.setTitle("Races manager");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(250, 350);


        GridLayout GLout = new GridLayout();
        GLout.setColumns(3);
        GLout.setRows(9); //Гонщики, команды, трассы и соревнования + пробелы между ними

        JPanel Gjp = new JPanel();
        Gjp.setLayout(GLout);
        JButton racers = new JButton("Racers");
        racers.addActionListener(
            event -> {
                System.out.println("Racers here");
                if(racerViewer == null)
                    racerViewer = new RacerViewer(mainFrame);
                racerViewer.setVisible(true);
            }
        );

        JButton teams = new JButton("Teams");
        teams.addActionListener(
            event -> {
                System.out.println("Teams here");
                if(teamViewer == null)
                    teamViewer = new TeamViewer(mainFrame);
                teamViewer.setVisible(true);
            }
        );

        JButton tracks = new JButton("Tracks");
        tracks.addActionListener(
            event -> {
                System.out.println("Tracks here");
                if(trackViewer == null)
                    trackViewer = new TrackViewer(mainFrame);
                trackViewer.setVisible(true);
            }
        );

        JButton competitions = new JButton("Competitions");
        competitions.addActionListener(
            event -> {
                System.out.println("Competitions here");
                if(compViewer == null)
                    compViewer = new CompViewer(mainFrame);
                compViewer.setVisible(true);
            }
        );

        Gjp.add(new JLabel("Select what do you want to see"));
        Gjp.add(racers);
        Gjp.add(new JLabel(""));
        Gjp.add(teams);
        Gjp.add(new JLabel(""));
        Gjp.add(tracks);
        Gjp.add(new JLabel(""));
        Gjp.add(competitions);
        Gjp.add(new JLabel(""));
        Gjp.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        mainFrame.add(Gjp, BorderLayout.CENTER);

        mainFrame.pack();
        mainFrame.setVisible(true);

        PrepareTable();
    }

    private void PrepareTable()
    {
        //============================RACERS============================//
        String[] racersColumns = {"ID", "NAME", "SURNAME", "TEAM", "SCORE"};
        racersModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        racersModel.setColumnIdentifiers(racersColumns);
        String[][] racerInfo = { 
            {"1", "Paul", "Surnamovich", "Uniq Team (ID 1)", "3124"},
            {"2", "Alan", "Walker", "Uniq Team (ID 1)", "213"},
            {"5", "Johnny", "McCarthy", "Uniq Team (ID 1)", "421"},
            {"51", "Andy", "Ktotovich", "Greatest Team (ID 2)", "31524"},
            {"61", "Paul", "Surnamovich", "Greatest Team (ID 2)", "3124"}
        };

        for(String[] i : racerInfo)
            racersModel.addRow(i);
        racersTable = new JTable(racersModel);
        racersTable.getColumnModel().getColumn(0).setMaxWidth(100);
        racersTable.setEnabled(true);
        racersTable.setAutoCreateRowSorter(true);    //сортировка в одну строку? Там можно было что ли?!?!?!
        racersPane = new JScrollPane(racersTable);
        racersPane.setPreferredSize(new Dimension(630, 380));

        //============================TEAMS============================//
        String[] teamsColumns = {"ID", "NAME", "RACERS IN TEAM"};
        teamsModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        teamsModel.setColumnIdentifiers(teamsColumns);
        String[][] teamInfo = { 
            {"1", "Uniq Team", "3"},
            {"2", "Greatest Team", "2"}
        };

        for(String[] i : teamInfo)
            teamsModel.addRow(i);
        teamsTable = new JTable(teamsModel);
        teamsTable.getColumnModel().getColumn(0).setMaxWidth(100);
        teamsTable.setEnabled(true);
        teamsTable.setAutoCreateRowSorter(true);    //сортировка в одну строку? Там можно было что ли?!?!?!
        teamsPane = new JScrollPane(teamsTable);
        teamsPane.setPreferredSize(new Dimension(530, 380));

        //============================TRACKS============================//
        String[] tracksColumns = {"ID", "NAME", "COUNTRY"};
        tracksModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        tracksModel.setColumnIdentifiers(tracksColumns);
        String[][] trackInfo = { 
            {"1", "Kazan Ring", "Russia"},
            {"2", "2f2f Formula Karting", "Pakistan"},
            {"3", "Rudskogen Motorsenter", "Norway"},
            {"4", "Circuito del Garda", "Italy"},
            {"5", "Kartodromo La Reina", "Chile"},
        };

        for(String[] i : trackInfo)
            tracksModel.addRow(i);
        tracksTable = new JTable(tracksModel);
        tracksTable.getColumnModel().getColumn(0).setMaxWidth(100);
        tracksTable.setEnabled(true);
        tracksTable.setAutoCreateRowSorter(true);    //сортировка в одну строку? Там можно было что ли?!?!?!
        tracksPane = new JScrollPane(tracksTable);
        tracksPane.setPreferredSize(new Dimension(530, 380));

        //============================COMPETITIONS============================//
        String[] compsColumns = {"ID", "NAME", "DATE", "TRACK", "RACERS", "WINNER"};
        compsModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        compsModel.setColumnIdentifiers(compsColumns);
        
        String[][] compInfo = { 
            {"1", "Dellimore Gas Comp", "2021-05-18 17:48:00", "Kazan Ring", "3", "Noone"},
            {"2", "LETI YLETI Comp", "2021-05-10 13:00:00", "Circuito del Garda", "5", "Andy Ktotovich (ID 51)"}
        };
        for(String[] i : compInfo)
            compsModel.addRow(i);
        compsTable = new JTable(compsModel);
        compsTable.getColumnModel().getColumn(0).setMaxWidth(100);
        compsTable.getColumnModel().getColumn(4).setMaxWidth(70);
        compsTable.setEnabled(true);
        compsTable.setAutoCreateRowSorter(true);    //сортировка в одну строку? Там можно было что ли?!?!?!
        /*compsTable.getColumnModel().getColumn(2).setPreferredWidth(200);    //DATE column
        compsTable.getColumnModel().getColumn(4).setPreferredWidth(200);    //NUMBER OF RACERS column
        compsTable.getColumnModel().getColumn(5).setPreferredWidth(200);    //NUMBER OF RACERS column*/
        compsPane = new JScrollPane(compsTable);
        compsPane.setPreferredSize(new Dimension(780, 380));
    }

    class RacerViewer extends JDialog
    {
        private JPanel RV_panel;

        public RacerViewer(JFrame owner)
        {
            super(owner, "Racers", false);
            this.setPreferredSize(new Dimension(650, 400));

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu racerMenu = new JMenu("Actions");
            menubar.add(racerMenu);
            JMenuItem openTeam = new JMenuItem("Open racer's team");
            openTeam.addActionListener(
            event -> {
                System.out.println("Racer's team here");
            } );

            racerMenu.add(openTeam);

            JMenuItem findRacer = new JMenuItem("Find racer");
            findRacer.addActionListener(
            event -> {
                System.out.println("Find racer here");
            } );
            racerMenu.add(findRacer);

            JMenuItem editRacer = new JMenuItem("Edit racer");
            editRacer.addActionListener(
            event -> {
                System.out.println("Edit racer here");
            } );

            racerMenu.add(editRacer);

            JMenuItem addRacer = new JMenuItem("Add racer");
            addRacer.addActionListener(
            event -> {
                System.out.println("Add racer here");
            } );
            
            racerMenu.add(addRacer);

            JMenuItem removeRacer = new JMenuItem("Remove racer");
            removeRacer.addActionListener(
            event -> {
                System.out.println("Remove racer here");
            } );

            racerMenu.add(removeRacer);
            this.setJMenuBar(menubar);

            RV_panel = new JPanel();
            RV_panel.add(racersPane);
            this.add(RV_panel);

            this.pack();
        }
    }

    class TeamViewer extends JDialog
    {
        private JPanel TV_panel;

        public TeamViewer(JFrame owner)
        {
            super(owner, "Teams", false);
            this.setPreferredSize(new Dimension(550, 400));

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu teamMenu = new JMenu("Actions");
            menubar.add(teamMenu);
            
            JMenuItem showRacers = new JMenuItem("Show racers in team");
            showRacers.addActionListener(
            event -> {
                System.out.println("Show racers in team here");
            } );
            teamMenu.add(showRacers);

            JMenuItem findTeam = new JMenuItem("Find team");
            findTeam.addActionListener(
            event -> {
                System.out.println("Find team here");
            } );
            teamMenu.add(findTeam);

            JMenuItem editTeam = new JMenuItem("Edit team");
            editTeam.addActionListener(
            event -> {
                System.out.println("Add team here");
            } );
            teamMenu.add(editTeam);

            JMenuItem addTeam = new JMenuItem("Add team");
            addTeam.addActionListener(
            event -> {
                System.out.println("Add team here");
            } );
            teamMenu.add(addTeam);

            JMenuItem removeTeam = new JMenuItem("Remove team");
            removeTeam.addActionListener(
            event -> {
                System.out.println("Remove team here");
            } );
            teamMenu.add(removeTeam);

            this.setJMenuBar(menubar);

            TV_panel = new JPanel();
            TV_panel.add(teamsPane);
            this.add(TV_panel);

            this.pack();
        }
    }

    class TrackViewer extends JDialog
    {
        private JPanel TeamV_panel;

        public TrackViewer(JFrame owner)
        {
            super(owner, "Tracks", false);
            this.setPreferredSize(new Dimension(550, 400));

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu trackMenu = new JMenu("Actions");
            menubar.add(trackMenu);

            JMenuItem findTrack = new JMenuItem("Find track");
            findTrack.addActionListener(
            event -> {
                System.out.println("Find track here");
            } );
            trackMenu.add(findTrack);

            JMenuItem editTrack = new JMenuItem("Edit track");
            editTrack.addActionListener(
            event -> {
                System.out.println("Edit track here");
            } );
            trackMenu.add(editTrack);

            JMenuItem addTrack = new JMenuItem("Add track");
            addTrack.addActionListener(
            event -> {
                System.out.println("Add track here");
            } );
            trackMenu.add(addTrack);

            JMenuItem removeTrack = new JMenuItem("Remove track");
            removeTrack.addActionListener(
            event -> {
                System.out.println("Remove tracks here");
            } );
            trackMenu.add(removeTrack);

            this.setJMenuBar(menubar);

            TeamV_panel = new JPanel();
            TeamV_panel.add(tracksPane);
            this.add(TeamV_panel);

            this.pack();
        }
    }

    class CompViewer extends JDialog
    {
        private JPanel CV_panel;

        public CompViewer(JFrame owner)
        {
            super(owner, "Competitions", false);
            this.setPreferredSize(new Dimension(800, 400));

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu compMenu = new JMenu("Actions");
            menubar.add(compMenu);

            JMenuItem showRacers = new JMenuItem("Show racers in competition");
            showRacers.addActionListener(
            event -> {
                System.out.println("Show racers in competition here");
            } );
            compMenu.add(showRacers);

            JMenuItem findComp = new JMenuItem("Find competition");
            findComp.addActionListener(
            event -> {
                System.out.println("Find competition here");
            } );
            compMenu.add(findComp);

            JMenuItem editCompetition = new JMenuItem("Edit competition");
            editCompetition.addActionListener(
            event -> {
                System.out.println("Edit competition here");
            } );
            compMenu.add(editCompetition);

            JMenuItem addCompetition = new JMenuItem("Add competition");
            addCompetition.addActionListener(
            event -> {
                System.out.println("Add competition here");
            } );
            compMenu.add(addCompetition);

            JMenuItem removeCompetition = new JMenuItem("Remove competition");
            removeCompetition.addActionListener(
            event -> {
                System.out.println("Remove competition here");
            } );
            compMenu.add(removeCompetition);

            this.setJMenuBar(menubar);

            CV_panel = new JPanel();
            CV_panel.add(compsPane);
            this.add(CV_panel);

            this.pack();
        }
    }
}