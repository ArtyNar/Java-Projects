/**
 * @author Artem Kova
 * @date 4/24/2022
 * @class 3230 Java
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ScrollBarUI;

import java.io.File;
import java.util.Scanner;


public class BBallPanel extends JPanel
{
    private ArrayList<Player> players;

    private JMenuBar menuBar;
    private JButton newBtn = new JButton("New");
    private JButton saveBtn = new JButton("Save");
    private JButton exitBtn = new JButton("Exit");

    private JButton fullFrontBtn = new JButton("<<<");
    private JButton fullBackBtn = new JButton(">>>");
    private JButton forwardBtn = new JButton(">");
    private JButton backwardBtn = new JButton("<");

    private JButton loadBtn = new JButton("Load");

    private ButtonGroup rBtnGroup = new ButtonGroup();
    private JRadioButton eastRbtn = new JRadioButton("East");
    private JRadioButton westRbtn = new JRadioButton("West");

    private JCheckBox starterJChB = new JCheckBox("Starter");

    String posArr[] =  {"Point Guard", "Shooting Guard", "Small Forward", "Power Forward", "Center"};
    private JComboBox positionCmbB = new JComboBox<String>(posArr);
    // Labels

    private JLabel  positionLabl = new JLabel("Position");
    private JLabel currRecLabl = new JLabel("0 of 0");

    private JLabel fNameLbl = new JLabel("First Name");
    private JLabel lNameLbl = new JLabel("Last Name");
    private JLabel shootLbl = new JLabel("Shooting %");
    private JLabel reboundLbl = new JLabel("Rebounds");
    private JLabel assistLbl = new JLabel("Assists");
    private JLabel turnovLbl = new JLabel("Turnovers");


    private JTextField fNameFld = new JTextField("", 10);
    private JTextField lNameFld = new JTextField("", 10);
    private JTextField shootFld = new JTextField("", 10);
    private JTextField reboundFld = new JTextField("", 10);
    private JTextField assistFld = new JTextField("", 10);
    private JTextField turnovFld = new JTextField("", 10);

    private JMenu fileMenu = new JMenu("File");
    private JMenu dataMenu = new JMenu("Data");

    // Creating File Menu Options
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem saveMenuItem = new JMenuItem("Save");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");
    private JMenuItem loadDataMenuItem = new JMenuItem("Load Data");
    private JMenuItem firstRecordMenuItem = new JMenuItem("First Record");
    private JMenuItem lastRecordMenuItem = new JMenuItem("Last Record");

    private int curr = 0;

    // A constructor
    public BBallPanel(JMenuBar bar)
    {
        // First, dealing with a Menu Bar
        menuBar = bar;
        makeMenu();

        // Defining our players arrayList
        players = new ArrayList<>();

        setLayout(new BorderLayout());

        JPanel north = new JPanel();
        JPanel south = new JPanel();
        JPanel mid = new JPanel();
        JPanel west = new JPanel();
        JPanel east = new EastPanel(players);

        //  North Panel Setup
        newBtn.setMnemonic('N');
        saveBtn.setMnemonic('S');
        exitBtn.setMnemonic('X');
        loadBtn.setMnemonic('L');

        north.add(newBtn);
        north.add(saveBtn);
        north.add(exitBtn);
        north.add(currRecLabl);
        add(north, BorderLayout.NORTH);
        //   South Panel Setup
        south.add(fullFrontBtn);
        south.add(backwardBtn);
        south.add(forwardBtn);
        south.add(fullBackBtn);
        add(south, BorderLayout.SOUTH);
        //  Mid Panel Setup
        mid.setLayout(new FlowLayout());
        mid.add(fNameLbl);
        fNameLbl.setPreferredSize(new Dimension(120, 25));
        fNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(fNameFld);
        mid.add(lNameLbl);
        lNameLbl.setPreferredSize(new Dimension(120, 25));
        lNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(lNameFld);
        mid.add(shootLbl);
        shootLbl.setPreferredSize(new Dimension(120, 25));
        shootLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(shootFld);
        mid.add(reboundLbl);
        reboundLbl.setPreferredSize(new Dimension(120, 25));
        reboundLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(reboundFld);
        mid.add(assistLbl);
        assistLbl.setPreferredSize(new Dimension(120, 25));
        assistLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(assistFld);
        mid.add(turnovLbl);
        turnovLbl.setPreferredSize(new Dimension(120, 25));
        turnovLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mid.add(turnovFld);
        add(mid, BorderLayout.CENTER);
 
        // create a slider
        JSlider b = new JSlider(0, 50, 10);
 
        // paint the ticks and tracks
        b.setPaintTrack(true);
        b.setPaintTicks(true);
        b.setPaintLabels(true);
 
        // set spacing
        b.setMajorTickSpacing(10);
        b.setMinorTickSpacing(1);
        b.setValue(0);

        b.addChangeListener(e ->
        {
            curr = b.getValue();
            if(players.size() == 0)
            {
                JOptionPane.showMessageDialog(null, "There are no records");
                return;
            }
            if(curr+1 > players.size())
            {
                curr = 0;
                b.setValue(0);
            }
            else 
            {
                updateFields(b.getValue());
            }

        });
        
        // West panel setup
        mid.add(b);

        west.setPreferredSize(new Dimension(140, 0));

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Conference");
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();

        //west.setBorder(etched);
        mid.setBorder(raisedbevel);
        north.setBorder(loweredetched);
        south.setBorder(loweredetched);

        west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));

        add(west, BorderLayout.WEST);

        loadBtn.setAlignmentX(CENTER_ALIGNMENT);

        Box radioBox = Box.createHorizontalBox();
        radioBox.setBorder(titled);
        radioBox.add(eastRbtn);
        radioBox.add(westRbtn);

        west.add(Box.createRigidArea(new Dimension(0, 10)));
        west.add(loadBtn);
        loadBtn.addActionListener(new FileChooser());
        west.add(Box.createRigidArea(new Dimension(0, 10)));
        west.add(radioBox);

        west.add(Box.createRigidArea(new Dimension(0, 10)));
        west.add(starterJChB);
        starterJChB.setAlignmentX(CENTER_ALIGNMENT);
        west.add(Box.createRigidArea(new Dimension(0, 10)));

        west.add(positionLabl);
        positionLabl.setAlignmentX(CENTER_ALIGNMENT);
        west.add(Box.createRigidArea(new Dimension(0, 10)));
        west.add(positionCmbB);
        positionCmbB.setMaximumSize( positionCmbB.getPreferredSize() );

        positionCmbB.setAlignmentX(CENTER_ALIGNMENT);

        // Adding to our checkbox group
        rBtnGroup.add(eastRbtn);
        rBtnGroup.add(westRbtn);

        // East panel setup
        add(east, BorderLayout.EAST);

        // Now, adding actions
        exitBtn.addActionListener(e -> System.exit(0));
        saveBtn.addActionListener(e -> saveRecord());
        newBtn.addActionListener(e -> newRecord());
        fullFrontBtn.addActionListener(e -> firstRecord());
        fullBackBtn.addActionListener(e -> lastRecord());
        forwardBtn.addActionListener(e -> nextRecord());
        backwardBtn.addActionListener(e -> previousRecord());
    }

    private void makeMenu()
    {
        menuBar.add(fileMenu);
        menuBar.add(dataMenu);

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        // Creating Data Menu Options
        dataMenu.add(loadDataMenuItem);
        dataMenu.add(firstRecordMenuItem);
        dataMenu.add(lastRecordMenuItem);

        // Adding Listener to Menu Options
        exitMenuItem.addActionListener( (event) -> System.exit(0));
        saveMenuItem.addActionListener(e -> saveRecord());
        newMenuItem.addActionListener(e -> newRecord());
        firstRecordMenuItem.addActionListener(e -> firstRecord());
        lastRecordMenuItem.addActionListener(e -> lastRecord());
        
        loadDataMenuItem.addActionListener(new FileChooser());


        // Adding accelerators
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        loadDataMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        firstRecordMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
        lastRecordMenuItem.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
    }

    private void loadData(File file)
    {
        try (Scanner inputFile = new Scanner(file))
        {
            while(inputFile.hasNext())
            {
                String fName = inputFile.next();
                String lName = inputFile.next();
                double shootPct = inputFile.nextDouble();
                int rebounds = inputFile.nextInt();
                int assists = inputFile.nextInt();
                int turnovers = inputFile.nextInt();
                String conference = inputFile.next();
                int position =  inputFile.nextInt();
                boolean starter =  inputFile.nextBoolean();

                players.add(new Player(fName, lName, shootPct, rebounds, assists, turnovers, conference, position, starter));

                inputFile.nextLine(); // Jumping to the next line
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void newRecord()
    {
        clearFields();
        curr = 1;
    }

    private void saveRecord()
    {
        String fn, ln, con;
        double sht;
        int reb, asst, turn, pos;
        boolean start;

        // First 3 fields need to be filled
        if(fNameFld.getText().isEmpty() || lNameFld.getText().isEmpty() || shootFld.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Players must have a minimum of First name, Last name, and a Shooting %");
            return;
        }
        
        // Check if a conference is selected
        if(!eastRbtn.isSelected() && !westRbtn.isSelected())
        {
            JOptionPane.showMessageDialog(null, "Players must select a conference");
            return;
        }

        try
        {
            fn = fNameFld.getText();
            ln =lNameFld.getText();
            sht = Double.parseDouble(shootFld.getText());

            con = eastRbtn.isSelected() ? "East" : "West";
            
            start = starterJChB.isSelected() ? true : false;

            pos = positionCmbB.getSelectedIndex() + 1;
            
            // Problemsolving the fact that we cant pass "" into an int
            if(reboundFld.getText().isEmpty())
            {
                reb = 0;
            }
            else
            {
                reb = Integer.parseInt(reboundFld.getText());
            }
            if(assistFld.getText().isEmpty())
            {
                asst = 0;
            }
            else
            {
                asst = Integer.parseInt(assistFld.getText());
            }
            if(turnovFld.getText().isEmpty())
            {
                turn = 0;
            }
            else
            {
                turn = Integer.parseInt(turnovFld.getText());
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "You have entered wrong data.");
            return;
        }

        players.add(new Player(fn, ln, sht, reb, asst, turn, con, pos, start));
        currRecLabl.setText("0 of " + players.size());
        curr = -1;
        clearFields();
    }

    private void firstRecord()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no saved records to show.");
            return;
        }
        else
        {
            curr = 0;
            updateFields(0);
            //currRecLabl.setText(1 + " of " + players.size());
        }
    }

    private void lastRecord()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no saved records to show.");
            return;
        }
        else
        {
            curr = players.size()-1;
            updateFields(players.size()-1);
            //currRecLabl.setText((curr+1) + " of " + players.size());
        }

    }

    private void nextRecord()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no saved records to show.");
        }
        else if(curr != players.size()-1)
        {
            curr++;
            updateFields(curr);
            
            //currRecLabl.setText((curr+1) + " of " + players.size());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No more records");
        }
    }

    private void previousRecord()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no saved records to show.");
        }else if(curr > 0)
        {
            curr--;
            updateFields(curr);

            //currRecLabl.setText((curr+1) + " of " + players.size());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No more records");
        }
    }

    private void clearFields()
    {
        fNameFld.setText("");
        lNameFld.setText("");
        shootFld.setText("");
        reboundFld.setText("");
        assistFld.setText("");
        turnovFld.setText("");
        currRecLabl.setText("0 of "+ players.size());
        rBtnGroup.clearSelection();
        starterJChB.setSelected(false);
    }

    private void updateFields(int indx)
    {
        fNameFld.setText(players.get(indx).getFirstName());
        lNameFld.setText(players.get(indx).getLastName());

        shootFld.setText(String.valueOf(players.get(indx).getShootPct()));
        reboundFld.setText(String.valueOf(players.get(indx).getRebounds()));
        assistFld.setText(String.valueOf(players.get(indx).getAssists()));
        turnovFld.setText(String.valueOf(players.get(indx).getTurnovers()));
        
        if(players.get(indx).getConference().equals("East"))
        {
            eastRbtn.setSelected(true);
        }
        else
        {
            westRbtn.setSelected(true);
        }
        
        if(players.get(indx).isStarter()) 
        {
            starterJChB.setSelected(true);
        }
        else
        {
            starterJChB.setSelected(false);
        }

        positionCmbB.setSelectedIndex(players.get(indx).getPosition()-1);
        currRecLabl.setText((curr+1) + " of " + players.size());
    }

    private class FileChooser implements ActionListener
    {
        JFileChooser  fileChooser;

        public FileChooser()
        {
            //fileChooser.setCurrentDirectory(new File("."));
            fileChooser = new JFileChooser(new File("."));
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int result = fileChooser.showOpenDialog(BBallPanel.this);

            if (result == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile = fileChooser.getSelectedFile();
                loadData(selectedFile);
                firstRecord();   
            }    
        }
    }

}
