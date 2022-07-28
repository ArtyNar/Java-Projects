public class Player 
{
    private String fName;
    private String lName;

    private double shoorPct = 0.0;
    private int rebounds = 0;
    private int assists = 0;
    private int turnovers = 0;
    // Will use it for a toString method
    String posArr[] =  {"Point Guard", "Shooting Guard", "Small Forward", "Power Forward", "Center"}; 

    String conference;
    int position;
    boolean starter;

    public Player(String fName, String lName, double shootPct, int rebounds, int assists, int turnovers)
    {
        this.fName = fName;
        this.lName = lName;
        this.shoorPct = shootPct;
        this.rebounds = rebounds;
        this.assists = assists;
        this.turnovers = turnovers;
    }

    public Player(String fName, String lName, double shootPct, int rebounds, int assists, int turnovers, String conference, int position, boolean starter)
    {
        this(fName, lName, shootPct, rebounds, assists, turnovers);
        this.conference = conference;
        this.position = position;
        this.starter = starter;
    }

    public String getConference()
    {
        return conference;
    }

    public int getPosition()
    {
        return position;
    }

    public boolean isStarter()
    {
        return starter;
    }


    public String getFirstName()
    {
        return fName;
    }

    public String getLastName()
    {
        return lName;
    }

    public double getShootPct()
    {
        return shoorPct;
    }

    public int getAssists()
    {
        return assists;
    }

    public int getRebounds()
    {
        return rebounds;
    }

    public int getTurnovers()
    {
        return turnovers;
    }

    public void setFName(String fn)
    {
        fName = fn;
    }

    public void setLName(String ln)
    {
        lName = ln;
    }

    public void setShootP(double sp)
    {
        shoorPct = sp;
    }

    public void setRebounds(int reb)
    {
        rebounds = reb;
    }

    public void setTurn(int turn)
    {
        turnovers = turn;
    }

    public void setAssists(int asst)
    {
        assists = asst;
    }

    public void setConference(String conf)
    {
        conference = conf;
    }

    public void setPosition(int pos)
    {
        position = pos;
    }

    public void setStarter(boolean startr)
    {
        starter = startr;
    }

    public String toString()
    {
        String out = fName + " " + lName + "; Shooting %: " + shoorPct + "; Rebounds: " + rebounds + "; Assists: " + assists + "; Turnovers: " + turnovers + "; Conference: " + conference + "; Position: " + posArr[position-1] + "; Starter: " + starter;
        return out + "\n";
    }
}
