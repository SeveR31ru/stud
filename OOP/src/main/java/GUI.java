import Races.*;

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
    private RaceHandler rHandler = null;
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
        this.rHandler = new RaceHandler();
        rHandler.loadData();
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
                    racerViewer = new RacerViewer(mainFrame, rHandler.getRacers());
                racerViewer.setVisible(true);
            }
        );

        JButton teams = new JButton("Teams");
        teams.addActionListener(
            event -> {
                System.out.println("Teams here");
                if(teamViewer == null)
                    teamViewer = new TeamViewer(mainFrame, rHandler.getTeams());
                teamViewer.setVisible(true);
            }
        );

        JButton tracks = new JButton("Tracks");
        tracks.addActionListener(
            event -> {
                System.out.println("Tracks here");
                if(trackViewer == null)
                    trackViewer = new TrackViewer(mainFrame, rHandler.getTracks());
                trackViewer.setVisible(true);
            }
        );

        JButton competitions = new JButton("Competitions");
        competitions.addActionListener(
            event -> {
                System.out.println("Competitions here");
                if(compViewer == null)
                    compViewer = new CompViewer(mainFrame, rHandler.getComps());
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
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex == 3)
                {
                    Racer toPrint = rHandler.getRacers().get(rowIndex);
                    System.out.println("Show " + toPrint.getName() + "'s team");
                }
                return false;
            }
        };
        racersModel.setColumnIdentifiers(racersColumns);
        List<Racer> racersList = rHandler.getRacers();
        if(racersList != null)
        {
            String[] racerInfo = new String[5];
            for(int i = 0; i < racersList.size(); i++)
            {
                Racer racer = racersList.get(i);
                racerInfo[0] = ("" + racer.getId());
                racerInfo[1] = (racer.getName());
                racerInfo[2] = (racer.getSurname());
                racerInfo[3] = (racer.getTeam().getName() + " (ID " + racer.getTeam().getId() + ")");
                racerInfo[4] = ("" + racer.getScore());
                racersModel.addRow(racerInfo);
            }
        }
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
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex == 2)
                    System.out.println("Show racers in the team");
                return false;
            }
        };
        teamsModel.setColumnIdentifiers(teamsColumns);
        List<Team> teamsList = rHandler.getTeams();
        if(teamsList != null)
        {
            String[] teamInfo = new String[3];
            for(int i = 0; i < teamsList.size(); i++)
            {
                Team team = teamsList.get(i);
                teamInfo[0] = ("" + team.getId());
                teamInfo[1] = (team.getName());
                teamInfo[2] = ("" + team.getRacers().size());
                teamsModel.addRow(teamInfo);
            }
        }
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
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        tracksModel.setColumnIdentifiers(tracksColumns);
        List<Track> tracksList = rHandler.getTracks();
        if(tracksList != null)
        {
            String[] trackInfo = new String[3];
            for(int i = 0; i < tracksList.size(); i++)
            {
                Track track = tracksList.get(i);
                trackInfo[0] = ("" + track.getId());
                trackInfo[1] = (track.getName());
                trackInfo[2] = (track.getCountry());
                tracksModel.addRow(trackInfo);
            }
        }
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
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex == 3)
                    System.out.println("Show track's info");
                else if(columnIndex == 4)
                    System.out.println("Show racers in the competition");
                else if(columnIndex == 5)
                    System.out.println("Show winner in the competition");
                return false;
            }
        };
        compsModel.setColumnIdentifiers(compsColumns);
        List<Competition> compsList = rHandler.getComps();
        if(compsList != null)
        {
            String[] compInfo = new String[6];
            for(int i = 0; i < compsList.size(); i++)
            {
                Competition comp = compsList.get(i);
                compInfo[0] = ("" + comp.getId());
                compInfo[1] = (comp.getName());
                compInfo[2] = ("" + comp.getDate());
                compInfo[3] = (comp.getTrack().getName());
                compInfo[4] = (""+ comp.getRacers().size());
                compInfo[5] = (""+ comp.getWinner().getName() + " " + comp.getWinner().getSurname() + " (ID " + comp.getWinner().getId() + ")");
                compsModel.addRow(compInfo);
            }
        }
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
        private List<Racer> racers;
        private JPanel RV_panel;

        public RacerViewer(JFrame owner, List<Racer>_racers)
        {
            super(owner, "Racers", false);
            this.setPreferredSize(new Dimension(650, 400));
            this.racers = _racers;

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu racerMenu = new JMenu("Actions");
            menubar.add(racerMenu);

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
        private List<Team> teams;
        private JPanel TV_panel;

        public TeamViewer(JFrame owner, List<Team>_teams)
        {
            super(owner, "Teams", false);
            this.setPreferredSize(new Dimension(550, 400));
            this.teams = _teams;

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu teamMenu = new JMenu("Actions");
            menubar.add(teamMenu);

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
        private List<Track> tracks;
        private JPanel TeamV_panel;

        public TrackViewer(JFrame owner, List<Track>_tracks)
        {
            super(owner, "Tracks", false);
            this.setPreferredSize(new Dimension(550, 400));
            this.tracks = _tracks;

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
        private List<Competition> comps;
        private JPanel CV_panel;

        public CompViewer(JFrame owner, List<Competition>_comps)
        {
            super(owner, "Competitions", false);
            this.setPreferredSize(new Dimension(800, 400));
            this.comps = _comps;

            JMenuBar menubar = new JMenuBar();
            this.setJMenuBar(menubar);

            JMenu compMenu = new JMenu("Actions");
            menubar.add(compMenu);

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