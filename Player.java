public class Player {
  public String name;
  public String position;
  public String team;
  public int tier;
  public double adp;
  public double lastSeasonPPG;


  public static void main(String[] args) {
    
  }

  public Player() {
    this.name = "";
    this.position = "";
    this.team = "";
    this.tier = 0;
    this.adp = 0.0;
    this.lastSeasonPPG = 0.0;
  }

  public Player(String iName, String iPosition, String iTeam, int iTier, double iadp, double iLastSeasonPPG) {
    this.name = iName;
    this.position = iPosition;
    this.team = iTeam;
    this.tier = iTier;
    this.adp = iadp;
    this.lastSeasonPPG = iLastSeasonPPG;
  }
}
