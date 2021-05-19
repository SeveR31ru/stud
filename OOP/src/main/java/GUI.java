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

        PrepareTables();
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
                    racerViewer = new RacerViewer(mainFrame, rHandler.getRacers(), racersPane, true);
                racerViewer.setVisible(true);
            }
        );

        JButton teams = new JButton("Teams");
        teams.addActionListener(
            event -> {
                System.out.println("Teams here");
                if(teamViewer == null)
                    teamViewer = new TeamViewer(mainFrame, rHandler.getTeams(), teamsPane, true);
                teamViewer.setVisible(true);
            }
        );

        JButton tracks = new JButton("Tracks");
        tracks.addActionListener(
            event -> {
                System.out.println("Tracks here");
                if(trackViewer == null)
                    trackViewer = new TrackViewer(mainFrame, rHandler.getTracks(), tracksPane, true);
                trackViewer.setVisible(true);
            }
        );

        JButton competitions = new JButton("Competitions");
        competitions.addActionListener(
            event -> {
                System.out.println("Competitions here");
                if(compViewer == null)
                    compViewer = new CompViewer(mainFrame, rHandler.getComps(), compsPane, true);
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
    }

    private void PrepareTables()
    {
        //============================RACERS============================//
        String[] racersColumns = {"ID", "NAME", "SURNAME", "TEAM", "SCORE"};
        racersModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex == 3)
                {
                    showObj(rHandler.getRacers().get(rowIndex).getTeam());
                    /*Racer toPrint = rHandler.getRacers().get(rowIndex);
                    System.out.println("Show " + toPrint.getName() + "'s team");*/
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
                {
                    showObjs(rHandler.getTeams().get(rowIndex).getRacers());
                }
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
                {
                    showObj(rHandler.getComps().get(rowIndex).getTrack());
                    /*Track trackToShow = rHandler.getComps().get(rowIndex).getTrack();
                    List<Track>trackList = new ArrayList<Track>();
                    trackList.add(trackToShow);
                    TrackViewer trackShown = new TrackViewer(mainFrame, trackList, getPane(trackList) ,false);
                    trackShown.setVisible(true);
                    System.out.println("Show track's info");*/
                }
                else if(columnIndex == 4)
                {
                    showObjs(rHandler.getComps().get(rowIndex).getRacers());
                    /*Competition comp = rHandler.getComps().get(rowIndex);
                    RacerViewer racersInCompViewer = new RacerViewer(mainFrame, comp.getRacers(), getPane(comp.getRacers()), false);
                    racersInCompViewer.setVisible(true);
                    System.out.println("Show racers in the competition");*/
                }
                else if(columnIndex == 5)
                {
                    showObj(rHandler.getComps().get(rowIndex).getWinner());
                    /*Competition comp = rHandler.getComps().get(rowIndex);
                    List<Racer>racerList = new ArrayList<Racer>();
                    racerList.add(comp.getWinner());
                    RacerViewer winnerViewer = new RacerViewer(mainFrame, racerList, getPane(racerList), false);
                    winnerViewer.setVisible(true);
                    System.out.println("Show winner in the competition");*/
                }
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

    private <T> JScrollPane getPane(List<T> objs)  //Using for find
    {
        JScrollPane jPane;
        DefaultTableModel tModel;
        JTable table;
        Dimension dimension;
        if(objs.get(0).getClass() == Racer.class)
        {
            dimension = new Dimension(630, 380);
            String[] racersColumns = {"ID", "NAME", "SURNAME", "TEAM", "SCORE"};
            tModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if(columnIndex == 3)
                    {
                        Racer toPrint = (Racer)objs.get(rowIndex);
                        showObj(toPrint.getTeam());
                    }
                    return false;
                }
            };
            tModel.setColumnIdentifiers(racersColumns);
            String[] racerInfo = new String[5];
            for(int i = 0; i < objs.size(); i++)
            {
                Racer racer = (Racer)objs.get(i);
                racerInfo[0] = ("" + racer.getId());
                racerInfo[1] = (racer.getName());
                racerInfo[2] = (racer.getSurname());
                racerInfo[3] = (racer.getTeam().getName() + " (ID " + racer.getTeam().getId() + ")");
                racerInfo[4] = ("" + racer.getScore());
                tModel.addRow(racerInfo);
            }
        }
        else if(objs.get(0).getClass() == Team.class)
        {
            dimension = new Dimension(530, 380);
            String[] teamsColumns = {"ID", "NAME", "RACERS IN TEAM"};
            tModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if(columnIndex == 2)
                    {
                        Team team = (Team)objs.get(rowIndex);
                        showObjs(team.getRacers());
                    }
                    return false;
                }
            };
            tModel.setColumnIdentifiers(teamsColumns);
            String[] teamInfo = new String[3];
            for(int i = 0; i < objs.size(); i++)
            {
                Team team = (Team)objs.get(i);
                teamInfo[0] = ("" + team.getId());
                teamInfo[1] = (team.getName());
                teamInfo[2] = ("" + team.getRacers().size());
                tModel.addRow(teamInfo);
            }
        }
        else if(objs.get(0).getClass() == Track.class)
        {
            String[] tracksColumns = {"ID", "NAME", "COUNTRY"};
            tModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            tModel.setColumnIdentifiers(tracksColumns);
            String[] trackInfo = new String[3];
            for(int i = 0; i < objs.size(); i++)
            {
                Track track = (Track)objs.get(i);
                trackInfo[0] = ("" + track.getId());
                trackInfo[1] = (track.getName());
                trackInfo[2] = (track.getCountry());
                tModel.addRow(trackInfo);
            }
            dimension = new Dimension(530, 380);
        }
        else    //Competition.class
        {
            String[] compsColumns = {"ID", "NAME", "DATE", "TRACK", "RACERS", "WINNER"};
            tModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if(columnIndex == 3)
                    {
                        Competition comp = (Competition)objs.get(rowIndex);
                        showObj(comp.getTrack());
                        /*Track trackToShow = comp.getTrack();
                        List<Track>trackList = new ArrayList<Track>();
                        trackList.add(trackToShow);
                        TrackViewer trackShown = new TrackViewer(mainFrame, trackList, getPane(trackList), false);
                        trackShown.setVisible(true);
                        System.out.println("Show track's info");*/
                    }
                    else if(columnIndex == 4)
                    {
                        Competition comp = (Competition)objs.get(rowIndex);
                        showObjs(comp.getRacers());
                        /*RacerViewer racersInCompViewer = new RacerViewer(mainFrame, comp.getRacers(), getPane(comp.getRacers()), false);
                        racersInCompViewer.setVisible(true);
                        System.out.println("Show racers in the competition");*/
                    }
                    else if(columnIndex == 5)
                    {
                        Competition comp = (Competition)objs.get(rowIndex);
                        showObj(comp.getWinner());
                        /*List<Racer>racerList = new ArrayList<Racer>();
                        racerList.add(comp.getWinner());
                        RacerViewer winnerViewer = new RacerViewer(mainFrame, racerList, getPane(racerList), false);
                        winnerViewer.setVisible(true);
                        System.out.println("Show winner in the competition");*/
                    }
                    return false;
                }
            };
            tModel.setColumnIdentifiers(compsColumns);
            String[] compInfo = new String[6];
            for(int i = 0; i < objs.size(); i++)
            {
                Competition comp = (Competition)objs.get(i);
                compInfo[0] = ("" + comp.getId());
                compInfo[1] = (comp.getName());
                compInfo[2] = ("" + comp.getDate());
                compInfo[3] = (comp.getTrack().getName());
                compInfo[4] = (""+ comp.getRacers().size());
                compInfo[5] = (""+ comp.getWinner().getName() + " " + comp.getWinner().getSurname() + " (ID " + comp.getWinner().getId() + ")");
                compsModel.addRow(compInfo);
            }
            dimension = new Dimension(780, 380);
        }
        table = new JTable(tModel);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.setEnabled(true);
        table.setAutoCreateRowSorter(true);    //сортировка в одну строку? Там можно было что ли?!?!?!
        jPane = new JScrollPane(table);
        jPane.setPreferredSize(dimension);
        return jPane;
    }

    private <T> void showObj(T obj)
    {
        if(obj.getClass() == Racer.class)
        {
            List<Racer>rList = new ArrayList<>();
            rList.add( (Racer)obj );
            RacerViewer rViewer = new RacerViewer(mainFrame, rList, getPane(rList), false);
            rViewer.setVisible(true);
            System.out.println("Show racer here");
        }
        else if(obj.getClass() == Team.class)
        {
            List<Team>tList = new ArrayList<>();
            tList.add( (Team)obj );
            TeamViewer tViewer = new TeamViewer(mainFrame, tList, getPane(tList), false);
            tViewer.setVisible(true);
            System.out.println("Show team here");
        }
        else if(obj.getClass() == Track.class)
        {
            List<Track>tList = new ArrayList<>();
            tList.add( (Track)obj );
            TrackViewer tViewer = new TrackViewer(mainFrame, tList, getPane(tList), false);
            tViewer.setVisible(true);
            System.out.println("Show track here");
        }
        else
        {
            List<Competition>cList = new ArrayList<>();
            cList.add( (Competition)obj );
            CompViewer cViewer = new CompViewer(mainFrame, cList, getPane(cList), false);
            cViewer.setVisible(true);
            System.out.println("Show competition here");
        }
    }

    private <T> void showObjs(List<T> objs)
    {
        if(objs.get(0).getClass() == Racer.class)
        {
            RacerViewer rViewer = new RacerViewer(mainFrame, (List<Racer>)objs, getPane((List<Racer>)objs), false);
            rViewer.setVisible(true);
            System.out.println("Show racers here");
        }
        else if(objs.get(0).getClass() == Team.class)
        {
            TeamViewer tViewer = new TeamViewer(mainFrame, (List<Team>)objs, getPane((List<Team>)objs), false);
            tViewer.setVisible(true);
            System.out.println("Show team here");
        }
        else if(objs.get(0).getClass() == Track.class)
        {
            TrackViewer tViewer = new TrackViewer(mainFrame, (List<Track>)objs, getPane((List<Track>)objs), false);
            tViewer.setVisible(true);
            System.out.println("Show track here");
        }
        else
        {
            CompViewer cViewer = new CompViewer(mainFrame, (List<Competition>)objs, getPane((List<Competition>)objs), false);
            cViewer.setVisible(true);
            System.out.println("Show competition here");
        }
    }

    class RacerViewer extends JDialog
    {
        private List<Racer> racers;
        private JPanel RV_panel;

        public RacerViewer(JFrame owner, List<Racer>_racers, JScrollPane _racersPane, boolean editable)
        {
            super(owner, "Racers", false);
            this.setPreferredSize(new Dimension(650, 400));
            this.racers = _racers;
            if(editable)
                addMenu();

            RV_panel = new JPanel();
            RV_panel.add(_racersPane);
            this.add(RV_panel);

            this.pack();
        }

        private void addMenu()
        {
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
        }
    }

    class TeamViewer extends JDialog
    {
        private List<Team> teams;
        private JPanel TV_panel;

        public TeamViewer(JFrame owner, List<Team>_teams, JScrollPane _teamsPane, boolean editable)
        {
            super(owner, "Teams", false);
            this.setPreferredSize(new Dimension(550, 400));
            this.teams = _teams;

            if(editable)
                addMenu();

            TV_panel = new JPanel();
            TV_panel.add(_teamsPane);
            this.add(TV_panel);

            this.pack();
        }

        private void addMenu()
        {
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
        }
    }

    class TrackViewer extends JDialog
    {
        private List<Track> tracks;
        private JPanel TeamV_panel;

        public TrackViewer(JFrame owner, List<Track>_tracks, JScrollPane _tracksPane, boolean editable)
        {
            super(owner, "Tracks", false);
            this.setPreferredSize(new Dimension(550, 400));
            this.tracks = _tracks;
            
            if(editable)
                addMenu();

            TeamV_panel = new JPanel();
            TeamV_panel.add(_tracksPane);
            this.add(TeamV_panel);

            this.pack();
        }

        private void addMenu()
        {
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
        }
    }

    class CompViewer extends JDialog
    {
        private List<Competition> comps;
        private JPanel CV_panel;

        public CompViewer(JFrame owner, List<Competition>_comps, JScrollPane _compsPane, boolean editable)
        {
            super(owner, "Competitions", false);
            this.setPreferredSize(new Dimension(800, 400));
            this.comps = _comps;
            
            if(editable)
                addMenu();

            CV_panel = new JPanel();
            CV_panel.add(_compsPane);
            this.add(CV_panel);

            this.pack();
        }

        private void addMenu()
        {
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
        }
    }
}