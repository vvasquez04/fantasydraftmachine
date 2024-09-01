import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

//Runs the logic of the fantasy draft machine
//IMPORTANT NOTE: Right now, this operates on the understanding that the CurrentTiers.txt file will be updated manually and
//perfectly formatted. Otherwise, it will throw an error.
public class Runner {

    //Main method of the Runner
    public static void main(String[] args) {
        ArrayList<Player> qbs = new ArrayList<Player>();
        ArrayList<Player> rbs = new ArrayList<Player>();
        ArrayList<Player> wrs = new ArrayList<Player>();
        ArrayList<Player> tes = new ArrayList<Player>();
        readTiers(qbs, rbs, wrs, tes);
        ArrayList<Player> draftedPlayers = new ArrayList<Player>();
        runDraft(qbs, rbs, wrs, tes, 1, draftedPlayers);
    }

    //Populate data from user input
    public static boolean readTiers(ArrayList<Player> qbs, ArrayList<Player> rbs, ArrayList<Player> wrs, ArrayList<Player> tes) {
        System.out.println("Enter Txt file name");
        String fileName = StdIn.readString();

        if(fileName.length() >= 5) {
            if(fileName.substring(fileName.length() - 4).equals(".txt")) {
                if(readFile(fileName, qbs, rbs, wrs, tes)) {
                    return true;
                } else { return false; }
            }
            else { System.out.println("Input is not in the format: +.txt"); }
        } else {
            // Handle the case where the string has fewer than three characters
            System.out.println("Input is not in the format: +.txt");
            return false;
        }
        return false;
    }

    //Reads the txt file and populate position groups
    public static boolean readFile(String fileName, ArrayList<Player> qbs, ArrayList<Player> rbs, ArrayList<Player> wrs, ArrayList<Player> tes) {
        //Check if file exists
        Path path = Paths.get(fileName);
        Path inputsFolderPath = Paths.get("TierLists", fileName);
        if(Files.exists(inputsFolderPath)) {
            fileName = "TierLists/" + fileName;
        } else if(!(Files.exists(path))) {
            System.out.println("File does not exist in the current directory");
            return false;
        }

        //Open the File
        In in = new In(fileName);

        //Read the "QB" line and loop through the qbs, adding them to the ArrayList
        String currentLine = in.readLine();
        while(!(currentLine.equals("RB"))) {
            currentLine = in.readLine();
            if(!(currentLine.equals("RB"))) {
                String[] splitQbLine = currentLine.split("/");
                String qbName = splitQbLine[0];
                String qbTeam = splitQbLine[1];
                int qbTier = Integer.parseInt(splitQbLine[2]);
                double qbADP = Double.parseDouble(splitQbLine[3]);
                Player p = new Player(qbName, "QB", qbTeam, qbTier, qbADP);
                qbs.add(p);
            }
        }

        //Read and add rbs
        while(!(currentLine.equals("WR"))) {
            currentLine = in.readLine();
            if(!(currentLine.equals("WR"))) {
                String[] splitRbLine = currentLine.split("/");
                String rbName = splitRbLine[0];
                String rbTeam = splitRbLine[1];
                int rbTier = Integer.parseInt(splitRbLine[2]);
                double rbADP = Double.parseDouble(splitRbLine[3]);
                Player p = new Player(rbName, "RB", rbTeam, rbTier, rbADP);
                rbs.add(p);
            }
        }

        //Read and add wrs
        while(!(currentLine.equals("TE"))) {
            currentLine = in.readLine();
            if(!(currentLine.equals("TE"))) {
                String[] splitWrLine = currentLine.split("/");
                String wrName = splitWrLine[0];
                String wrTeam = splitWrLine[1];
                int wrTier = Integer.parseInt(splitWrLine[2]);
                double wrADP = Double.parseDouble(splitWrLine[3]);
                Player p = new Player(wrName, "WR", wrTeam, wrTier, wrADP);
                wrs.add(p);
            }
        }

        //Read and add tes
        while(!(currentLine.equals("END"))) {
            currentLine = in.readLine();
            if(!(currentLine.equals("END"))) {
                String[] splitTeLine = currentLine.split("/");
                String teName = splitTeLine[0];
                String teTeam = splitTeLine[1];
                int teTier = Integer.parseInt(splitTeLine[2]);
                double teADP = Double.parseDouble(splitTeLine[3]);
                Player p = new Player(teName, "TE", teTeam, teTier, teADP);
                tes.add(p);
            }
        }

        return true;
    }

    //Print Current Top Players
    public static void listPlayers(ArrayList<Player> qbs, ArrayList<Player> rbs, ArrayList<Player> wrs, ArrayList<Player> tes) {
        
        System.out.println("Current Top Players:");
        System.out.println("QBS");
        int qbSize = qbs.size();
        for(int i = 0; i < Math.min(5, qbSize); i++){
            System.out.println((i+1) + ") " + qbs.get(i).name + ", " + qbs.get(i).team + ", Tier " + qbs.get(i).tier + ", ADP " + qbs.get(i).adp);
        }
        if (qbSize < 5) {
            for(int i = qbSize; i < 5; i++) {
                System.out.println((i+1) + ") No more QBs available");
            }
        }

        System.out.println("RBS");
        int rbSize = rbs.size();
        for(int i = 0; i < Math.min(5, rbSize); i++){
            System.out.println((i+1) + ") " + rbs.get(i).name + ", " + rbs.get(i).team + ", Tier " + rbs.get(i).tier + ", ADP " + rbs.get(i).adp);
        }
        if (rbSize < 5) {
            for(int i = rbSize; i < 5; i++) {
                System.out.println((i+1) + ") No more RBs available");
            }
        }

        System.out.println("WRS");
        int wrSize = wrs.size();
        for(int i = 0; i < Math.min(5, wrSize); i++){
            System.out.println((i+1) + ") " + wrs.get(i).name + ", " + wrs.get(i).team + ", Tier " + wrs.get(i).tier + ", ADP " + wrs.get(i).adp);
        }
        if (wrSize < 5) {
            for(int i = wrSize; i < 5; i++) {
                System.out.println((i+1) + ") No more WRs available");
            }
        }

        System.out.println("TES");
        int teSize = tes.size();
        for(int i = 0; i < Math.min(5, teSize); i++){
            System.out.println((i+1) + ") " + tes.get(i).name + ", " + tes.get(i).team + ", Tier " + tes.get(i).tier + ", ADP " + tes.get(i).adp);
        }
        if (teSize < 5) {
            for(int i = teSize; i < 5; i++) {
                System.out.println((i+1) + ") No more TEs available");
            }
        }

    }

    public static void runDraft(ArrayList<Player> qbs, ArrayList<Player> rbs, ArrayList<Player> wrs, ArrayList<Player> tes, int currentPickNum, ArrayList<Player> draftedPlayers) {
        listPlayers(qbs, rbs, wrs, tes);

        System.out.println("Pick " + currentPickNum);
        System.out.println("Selected Player: ");
        String pickedPlayer = StdIn.readLine();
        if(pickedPlayer.equals("END")) {
            draftSummary(draftedPlayers);
            return;
        }
        
        boolean playerFound = false;

        playerFound = removePlayerFromList(qbs, pickedPlayer, draftedPlayers);
        if(!playerFound) {
            playerFound = removePlayerFromList(rbs, pickedPlayer, draftedPlayers);
        }
        if(!playerFound) {
            playerFound = removePlayerFromList(wrs, pickedPlayer, draftedPlayers);
        }
        if(!playerFound) {
            playerFound = removePlayerFromList(tes, pickedPlayer, draftedPlayers);
        }

        if(!playerFound) {
            System.out.println("Player does not exist or has been drafted");
            runDraft(qbs, rbs, wrs, tes, currentPickNum, draftedPlayers);
        } else {
            // Continue to the next pick
            runDraft(qbs, rbs, wrs, tes, currentPickNum + 1, draftedPlayers);
        }
    }

    private static boolean removePlayerFromList(ArrayList<Player> players, String pickedPlayer, ArrayList<Player> draftedPlayers) {
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player p = it.next();
            if(pickedPlayer.trim().equalsIgnoreCase(p.name.trim())) {
                draftedPlayers.add(p);
                it.remove();
                return true;
            } else if((pickedPlayer.trim().equalsIgnoreCase("def")) || (pickedPlayer.trim().equalsIgnoreCase("k"))) {
                return true;
            }
        }
        return false;
    }

    public static void draftSummary(ArrayList<Player> draftedPlayers) {
        System.out.println("The draft is complete! Draft summary:");
        System.out.println();
        for(int i = 0; i < draftedPlayers.size(); i++) {
            System.out.println("Pick " + (i+1) + ": " + draftedPlayers.get(i).name + ", " + draftedPlayers.get(i).position + " " + draftedPlayers.get(i).team + " - ADP " + draftedPlayers.get(i).adp);
        }
        //Would be cool to score draft and show largest ADP diffs
    }
}