import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.*;
/**
 * @author Artem Koval
 * @date 4/24/2022
 * @class CS 3230
 */

public class EastPanel extends JPanel
{
    private JLabel filterLbl = new JLabel("FILTER DATA", SwingConstants.CENTER);
    private JLabel firstStartLbl = new JLabel("First start with");
    private JLabel lastStartLbl = new JLabel("Last start with");
    private JLabel assistLbl = new JLabel("Assist>");

    private JTextField firstStartTf = new JTextField("", 10);
    private JTextField lastStartTf = new JTextField("", 10);
    private JTextField assistTf = new JTextField("0", 10);

    private JTextArea textArea = new JTextArea("[]",9, 65);
    private JScrollPane scrollPane = new JScrollPane(textArea);

    private JButton allDataBtn = new JButton("All Data");
    private JButton filterBtn = new JButton("Filter");
    private JButton clearBtn = new JButton("Clear Data");
    private JButton totalAssistsBtn = new JButton("Total Assists");
    private JButton totalReboundsBtn = new JButton("Total Rebounds");
    private JButton totalTurnoversBtn = new JButton("Total Turnovers");

    // Created the private variable-copy of players array, so I can conviniently use it in my functions
    private ArrayList<Player> players;

    EastPanel(ArrayList<Player> players)
    {
        this.players = players;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(700, 0));  

        add(allDataBtn);
        add(filterBtn);
        add(clearBtn);

        add(Box.createRigidArea(new Dimension(25, 25)));

        add(totalAssistsBtn);
        add(totalReboundsBtn);
        add(totalTurnoversBtn);

        add(filterLbl);
        filterLbl.setPreferredSize(new Dimension(700, 20));
        
        add(firstStartLbl);
        add(firstStartTf);
        add(lastStartLbl);
        add(lastStartTf);
        add(assistLbl);
        add(assistTf);

        textArea.setBackground(Color.lightGray);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        //add(textArea);
        add(scrollPane);

        //Adding the actions
        allDataBtn.addActionListener(e -> displayAllData());
        clearBtn.addActionListener(e -> clearAllData());
        filterBtn.addActionListener(e -> filter());
        totalAssistsBtn.addActionListener(e -> displayAssists());
        totalReboundsBtn.addActionListener(e -> displayRebounds());
        totalTurnoversBtn.addActionListener(e -> displayTurnovers());
    }

    // Next three functions calculate the total numbers for assists,
    // Turnovers, or rebounds and displays them.
    private void displayAssists()
    {
        Integer sum = players.stream()
            .mapToInt(p -> p.getAssists()).sum();
        
        textArea.setText("Total Assists: " + sum);
    }

    private void displayRebounds()
    {
        Integer sum = players.stream()
            .mapToInt(p -> p.getRebounds()).sum();
        
        textArea.setText("Total Rebounds: " + sum);
    }

    private void displayTurnovers()
    {
        Integer sum = players.stream()
            .mapToInt(p -> p.getTurnovers()).sum();
        
        textArea.setText("Total Turnovers: " + sum);
    }

    // Prints all the players onto a text area
    private void displayAllData()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no records");
            return;
        }


        // A text that we will then display
        String text = "";

        for (Player p : players) 
        {
            text += p.toString(); 
        }

        textArea.setText(text);
    }

    private void clearAllData()
    {
        textArea.setText("");
        firstStartTf.setText("");
        lastStartTf.setText("");
        assistTf.setText("");
    }

    // Filters the data based on given parameters, and prints it onto a text area 
    private void filter()
    {
        if(players.size() == 0)
        {
            JOptionPane.showMessageDialog(null, "There are no records");
            return;
        }
        textArea.setText("");
        final int assistCond;
        int assists;
        // Have to check if the field has data before parsing 
        try
        {
            if(assistTf.getText().isEmpty()) 
            {
                assists = 0;
            }
            else
            {
                assists = Integer.parseInt(assistTf.getText());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Assists have to be a number");
            return;
        }

        assistCond = assists;

        String firstStartWth = firstStartTf.getText();
        String lastStartWth = lastStartTf.getText();

        // Filters a data using a stream
        Stream<Player> filtered = players.stream()
            .filter(p -> p.getAssists() >= assistCond)
            .filter(p -> p.getFirstName().startsWith(firstStartWth))
            .filter(p -> p.getLastName().startsWith(lastStartWth));
        
        // Prints our filtered data onto the textArea

        filtered.forEach(p -> textArea.append(p.toString()));
    }
}
