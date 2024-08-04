//Creates a Player Class which will be referenced in the logic
public class Player {
  public String name;
  public String position;
  public String team;
  public int tier;
  public double adp;


  public static void main(String[] args) {
    
  }

  //No-Arg Constructor
  public Player() {
    this.name = "";
    this.position = "";
    this.team = "";
    this.tier = 0;
    this.adp = 0.0;
  }

  //Constructor which will be commonly used
  public Player(String iName, String iPosition, String iTeam, int iTier, double iadp) {
    this.name = iName;
    this.position = iPosition;
    this.team = iTeam;
    this.tier = iTier;
    this.adp = iadp;
  }
}
