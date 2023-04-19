package edu.duke.ece651.riscgame.game;

import edu.duke.ece651.riscgame.order.*;
import edu.duke.ece651.riscgame.rule.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// import edu.duke.ece651.riscgame.Client.*;
//TODO: change textview to gui
//call the controller of javafx to interact with the gui.

public class BoardTextView {
    private GameMap boardMap;
    private HashMap<Integer, String> idToName;
    // private ViewController viewController;


    public BoardTextView(GameMap boardMap) {
        this.boardMap = boardMap;
        idToName = new HashMap<>();
        idToName.put(0, "Avalon");
        idToName.put(1, "Braglavia");
        idToName.put(2, "Calador");
        idToName.put(3, "Excrier");
        idToName.put(4, "Ceyland");
    }

    public void updateBoardMap(GameMap boardMap) {
        this.boardMap = boardMap;
    }

    public void printGameStartInfo(String playerName) {
        System.out.println("You are the " + playerName + " player, what would you like to do?");
        System.out.println("(M)ove");
        System.out.println("(A)ttack");
        System.out.println("(U)pgrade");
        System.out.println("(T)ech Upgrade");
        System.out.println("(D)one");
    }

    // public HashMap<String, Integer> initUnitAssignment(String playerName, int initTotalUnits) throws IOException {
    //     System.out.println("Please set your units: E.g. Narnia 10 Midkemia 12 Oz 8");
    //     int numUnits = initTotalUnits;
    //     HashMap<String, Integer> res = new HashMap<String, Integer>();
    //     for(Territory territory : boardMap.getTerritoriesByOwnerName(playerName)) {
    //         while(true) {
    //             try (Scanner scanner = new Scanner(System.in)) {
    //                 System.out.println("How many units do you want to place in " + territory.getName() + "? (You have " + numUnits + " units left)");
    //                 int numUnitInTerr = scanner.nextInt();
    //                 if(numUnitInTerr > numUnits) {
    //                     System.out.println("You don't have enough units to place in " + territory.getName());
    //                     continue;
    //                 }
    //                 territory.setUnitNum(numUnitInTerr);
    //                 numUnits -= numUnitInTerr;
    //                 res.put(territory.getName(), numUnitInTerr);
    //                 break;
    //             } catch (Exception e) {
    //                 System.out.println("Please enter a valid number:");
    //                 continue;
    //             }
    //         }
    //     }
    //     return res;
    // }

    public void printPlayerMap(String playerName) {
        System.out.println("\n" + playerName + " Player:");
        System.out.println("-------------");
        Player player = boardMap.getPlayerByName(playerName);
        player.disPlayResources();
        System.out.println("-------------");
        for (Territory territory : boardMap.getTerritoriesByOwnerName(playerName)) {
            System.out.println(territory.displayInfo());
        }
    }

    public void printPlayerMap(int playerId) {
        System.out.println("\n" + idToName.get(playerId) + " Player:");
        System.out.println("-------------");
        Player player = boardMap.getPlayerById(playerId);
        player.disPlayResources();
        System.out.println("-------------");
        for (Territory territory : boardMap.getTerritoriesByOwnId(playerId)) {
            System.out.println(territory.displayInfo());
        }
    }

    public Order issueOneOrder(int playerId) {
        System.out.println("\nWhat would you like to do?");
        System.out.println("(M)ove");
        System.out.println("(A)ttack");
        System.out.println("(U)pgrade Units");
        System.out.println("(T)ech Upgrade");
        System.out.println("(D)one");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextLine();
            if (input.equals("M") || input.equals("m")) {
                validInput = true;
                return issueMoveOrder(playerId);
            } else if (input.equals("A") || input.equals("a")) {
                validInput = true;
                return issueAttackOrder(playerId);
            } else if (input.equals("U") || input.equals("u")) {
                validInput = true;
                return issueUpgradeUnitOrder(playerId);
            } else if (input.equals("T") || input.equals("t")) {
                validInput = true;
                return issueUpgradeTechOrder(playerId);
            } else if (input.equals("D") || input.equals("d")) {
                validInput = true;
                return new Commit(0, null, null, Type.Commit, playerId, null, null);
            } else {
                System.out.println("Please enter a valid input");
                continue;
            }
        }
        return null;
    }

    private int getNumUnitsFromUser(int maxNumUnits) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextInt();
            if (input > 0 && input <= maxNumUnits) {
                validInput = true;
                return input;
            } else {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
        return -1;
    }

    // get start territory name from user and check if its belongs to the player and has enough units
    private String getStartTerritoryNameFromUser(int playerId) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextLine();
            if (boardMap.getTerritoryByName(input) != null) {
                if (boardMap.getTerritoryByName(input).getOwnId() == playerId) {
                    if (boardMap.getTerritoryByName(input).getUnitNum() >= 1) {
                        validInput = true;
                        return input;
                    } else {
                        System.out.println("You don't have enough units to move from " + input);
                        continue;
                    }
                } else {
                    System.out.println("You don't own " + input);
                    continue;
                }
            } else {
                System.out.println("Please enter a valid territory name");
                continue;
            }
        }
        return null;
    }

    private boolean checkNeighbor(String fromTerritoryName, String toTerritoryName) {
        for (Territory neighbor : boardMap.getTerritoryByName(fromTerritoryName).getNeighbors()) {
            if (neighbor.getName().equals(toTerritoryName)) {
                return true;
            }
        }
        return false;
    }

    // get the destination territory name from user and check if its a neighbor of the start territory, if it is MOVE order, check if it belongs to the player
    private String getDestTerritoryNameFromUser(int playerId, String fromTerritoryName, String orderType) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextLine();
            if (boardMap.getTerritoryByName(input) != null) {
                if (orderType.equals("MOVE")) {
                    if (boardMap.getTerritoryByName(input).getOwnId() == playerId) {
                        validInput = true;
                        return input;
                    } else {
                        System.out.println("You don't own " + input);
                        continue;
                    }
                } else if (!checkNeighbor(fromTerritoryName, input) && orderType.equals("ATTACK")) {
                    System.out.println(input + " is not a neighbor of " + fromTerritoryName);
                    continue;
                } else {
                    validInput = true;
                    return input;
                }
            } else {
                System.out.println("Please enter a valid territory name");
                continue;
            }
        }
        return input;
    }


<<<<<<< HEAD
    private ArrayList<Integer> getUnitIndex(int playerId, String fromTerritoryName) {
        Scanner scanner = new Scanner(System.in);
        int territoryUnitMaxNum = boardMap.getTerritoryByName(fromTerritoryName).getUnits().size();
=======
    private ArrayList<Integer> getUnitIndex(int playerId) {
        Scanner scanner = new Scanner(System.in);
        int territoryUnitMaxNum = boardMap.getTerritoriesByOwnId(playerId).size();
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        String input;
        boolean isValid;
        ArrayList<Integer> numbers;
        do {
            System.out.print("Please enter numbers with max length: " + territoryUnitMaxNum);
<<<<<<< HEAD
            numbers = new ArrayList<>();
            input = scanner.nextLine();
            isValid = validateInputUnitIndex(input, numbers, territoryUnitMaxNum);
        } while(!isValid);
        return numbers;
    }

    private boolean validateInputUnitIndex(String input, ArrayList<Integer> numbers, int territoryUnitMaxIndex) {
        // separate the input string by space
        String[] tokens = input.split(" ");
        if (tokens.length < 1 || tokens.length > territoryUnitMaxIndex) {
=======
            input = scanner.nextLine();
            numbers = new ArrayList<>();
            isValid = validateInput(input, numbers, territoryUnitMaxNum);
        } while (!isValid);
        return numbers;
    }

    private boolean validateInput(String input, ArrayList<Integer> numbers, int territoryUnitMaxNum) {
        // separate the input string by space
        String[] tokens = input.split(" ");
        if (tokens.length < 1 || tokens.length > territoryUnitMaxNum) {
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
            return false;
        }
        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token);
<<<<<<< HEAD
                if (number < 0 || number >= territoryUnitMaxIndex) {
=======
                if (number < 1 || number > territoryUnitMaxNum) {
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
                    return false;
                } else {
                    numbers.add(number);
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

//    public Move issueMoveOrder(int playerId) {
//        System.out.println("Please enter the territory you want to move from:");
//        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
//        System.out.println("Please enter the territory you want to move to:");
//        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "MOVE");
//        System.out.println("Please enter the number of units you want to move:");
//        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
//        return new Move(numUnits, boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Move, playerId);
//    }

    public Move issueMoveOrder(int playerId) {
        System.out.println("Please enter the territory you want to move from:");
        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
        System.out.println("Please enter the territory you want to move to:");
        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "MOVE");
        System.out.println("---------The units you have in " + fromTerritoryName + "----------");
        printAllTerritory(fromTerritoryName);
        System.out.println("===================================================================");
        System.out.println("Please enter the index of units you want to move (separate by a space):");
//        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
<<<<<<< HEAD
        ArrayList<Integer> unitIndex = getUnitIndex(playerId, fromTerritoryName);
=======
        ArrayList<Integer> unitIndex = getUnitIndex(playerId);
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        return new Move(unitIndex.size(), boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Move, playerId, unitIndex, null);
    }

//    public Attack issueAttackOrder(int playerId) {
//        System.out.println("Please enter the territory you want to attack from:");
//        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
//        System.out.println("Please enter the territory you want to attack:");
//        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "ATTACK");
//        System.out.println("Please enter the number of units you want to attack with:");
//        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
//        return new Attack(numUnits, boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Attack, playerId);
//    }

    public Attack issueAttackOrder(int playerId) {
        System.out.println("Please enter the territory you want to attack from:");
        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
        System.out.println("Please enter the territory you want to attack:");
        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "ATTACK");
        System.out.println("---------The units you have in " + fromTerritoryName + "----------");
        printAllTerritory(fromTerritoryName);
        System.out.println("===================================================================");
        System.out.println("Please enter the number of units you want to attack with (separate by a space):");
//        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
<<<<<<< HEAD
        ArrayList<Integer> unitIndex = getUnitIndex(playerId, fromTerritoryName);
=======
        ArrayList<Integer> unitIndex = getUnitIndex(playerId);
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        return new Attack(unitIndex.size(), boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Attack, playerId, unitIndex, null);
    }

    public UpgradeTech issueUpgradeTechOrder(int playerId) {
        System.out.println("You technology level will be upgraded by 1 at the end of the turn!");
        return new UpgradeTech(-1, null, null, Type.UpgradeTech, playerId, null, null);
    }

    public UpgradeUnit issueUpgradeUnitOrder(int playerId) {
        System.out.println("Please enter the territory you want to upgrade units in:");
        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
        // print out all units in the territory
        System.out.println("---------The units you have in " + fromTerritoryName + "----------");
        printAllTerritory(fromTerritoryName);
        System.out.println("===================================================================");
        System.out.println("Please enter the index of units you want to upgrade (separate by a space):");
<<<<<<< HEAD
        ArrayList<Integer> unitIndex = getUnitIndex(playerId, fromTerritoryName);
=======
        ArrayList<Integer> unitIndex = getUnitIndex(playerId);
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        System.out.println("Please enter their level you want to upgrade to (separate by a space):");
        ArrayList<Integer> unitLevel = getUnitLevel(playerId, unitIndex.size());
//        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
        return new UpgradeUnit(unitIndex.size(), boardMap.getTerritoryByName(fromTerritoryName), null, Type.UpgradeUnit, playerId, unitIndex, unitLevel);
    }

    private ArrayList<Integer> getUnitLevel(int playerId, int length) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean isValid;
        ArrayList<Integer> levels;
        do {
            System.out.print("Player " + playerId + ": Please enter " + length + " numbers between 0-6 separated by spaces: ");
            input = scanner.nextLine();
            levels = new ArrayList<>();
<<<<<<< HEAD
            isValid = validateInputLevel(input, length, levels);
=======
            isValid = validateInput(input, length, levels);
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        } while (!isValid);
        return levels;
    }

<<<<<<< HEAD
    private boolean validateInputLevel(String input, int length, ArrayList<Integer> numbers) {
=======
    private boolean validateInput(String input, int length, ArrayList<Integer> numbers) {
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
        String[] tokens = input.split(" ");
        if (tokens.length != length) {
            return false;
        }

        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token);
<<<<<<< HEAD
                if (number < 1 || number > 6) {
=======
                if (number < 1 || number > 7) {
>>>>>>> 1057107511b36ca44d727e38a8cfce0af15287f2
                    return false;
                } else {
                    numbers.add(number);
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


    private void printAllTerritory(String territoryName) {
        for (Territory territory : boardMap.getTerritories()) {
            if (territory.getName().equals(territoryName)) {
                territory.displayAllUnit();
            }
        }
    }


    private boolean scanYN() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            input = scanner.nextLine();
            if (input.equals("Y") || input.equals("y")) {
                validInput = true;
                return true;
            } else if (input.equals("N") || input.equals("n")) {
                validInput = true;
                return false;
            } else {
                System.out.println("Please enter a valid input");
                continue;
            }
        }
        return false;
    }

    public boolean printDeathInfo(String playerName) throws IOException {
        // print the death info to the player (lose all territories) and asked if he wants to continue watching the game
        System.out.println(playerName + " Player has lost all territories!");
        System.out.println("You have been defeated. Do you want to continue watching the game? (Y/N)");
        return scanYN();
    }

    public boolean printEndInfo() throws IOException {
        System.out.println("Game Over!");
        System.out.println("-------------");
        for (Territory territory : boardMap.getTerritories()) {
            System.out.println(territory.displayInfo());
        }
        System.out.println("-------------");
        System.out.println("Winner: " + boardMap.getWinner());
        System.out.println("Would you like to play again? (Y/N)");
        return scanYN();
    }
}
