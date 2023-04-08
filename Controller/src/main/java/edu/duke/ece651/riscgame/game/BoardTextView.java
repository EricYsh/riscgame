package edu.duke.ece651.riscgame.game;

import edu.duke.ece651.riscgame.order.Attack;
import edu.duke.ece651.riscgame.order.Commit;
import edu.duke.ece651.riscgame.order.Move;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BoardTextView {
    private BoardMap boardMap;

    public BoardTextView(BoardMap boardMap) {
        this.boardMap = boardMap;
    }

    public void printGameStartInfo(String playerName) {
        System.out.println("You are the "+ playerName + " player, what would you like to do?");
        System.out.println("(M)ove");
        System.out.println("(A)ttack");
        System.out.println("(D)one");
    }

    public HashMap<String, Integer> initUnitAssignment(String playerName, int initTotalUnits) throws IOException {
        System.out.println("Please set your units: E.g. Narnia 10 Midkemia 12 Oz 8");
        int numUnits = initTotalUnits;
        HashMap<String, Integer> res = new HashMap<String, Integer>();
        for(Territory territory : boardMap.getTerritoriesByOwnerName(playerName)) {
            while(true) {
                try (Scanner scanner = new Scanner(System.in)) {
                    System.out.println("How many units do you want to place in " + territory.getName() + "? (You have " + numUnits + " units left)");
                    int numUnitInTerr = scanner.nextInt();
                    if(numUnitInTerr > numUnits) {
                        System.out.println("You don't have enough units to place in " + territory.getName());
                        continue;
                    }
                    territory.setUnitNum(numUnitInTerr);
                    numUnits -= numUnitInTerr;
                    res.put(territory.getName(), numUnitInTerr);
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid number:");
                    continue;
                }
            }
        }
        return res;
    }

    public void printPlayerMap(String playerName) {
        System.out.println("\n" + playerName + " Player:");
        System.out.println("-------------");
        for(Territory territory : boardMap.getTerritoriesByOwnerName(playerName)) {
            System.out.println(territory.displayInfo());
        }
    }

    public Order issueOneOrder(int playerId) {
        System.out.println("What would you like to do?");
        System.out.println("(M)ove");
        System.out.println("(A)ttack");
        System.out.println("(D)one");
        while(true) {
            try (Scanner scanner = new Scanner(System.in)) {
                String input = scanner.nextLine();
                if(input.equals("M")) {
                    return issueMoveOrder(playerId);
                } else if(input.equals("A")) {
                    return issueAttackOrder(playerId);
                } else if(input.equals("D")) {
                    return issueCommitOrder(playerId);
                } else {
                    System.out.println("Please enter M, A or D");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Please enter M, A or D");
                continue;
            }
        }
    }

    private int getNumUnitsFromUser(int maxNumUnits) {
        while(true) {
            try (Scanner scanner = new Scanner(System.in)) {
                int input = scanner.nextInt();
                if(input > 0 && input <= maxNumUnits) {
                    return input;
                } else {
                    System.out.println("Please enter a valid number");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
    }

    // get start territory name from user and check if its belongs to the player and has enough units
    private String getStartTerritoryNameFromUser(int playerId) {
        while(true) {
            try (Scanner scanner = new Scanner(System.in)) {
                String input = scanner.nextLine();
                if(boardMap.getTerritoryByName(input) != null) {
                    if(boardMap.getTerritoryByName(input).getOwnId() == playerId) {
                        if(boardMap.getTerritoryByName(input).getUnitNum() > 1) {
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
            } catch (Exception e) {
                System.out.println("Please enter a valid territory name");
                continue;
            }
        }
    }

    private boolean checkNeighbor(String fromTerritoryName, String toTerritoryName) {
        for(Territory neighbor : boardMap.getTerritoryByName(fromTerritoryName).getNeighbors()) {
            if(neighbor.getName().equals(toTerritoryName)) {
                return true;
            }
        }
        return false;
    }

    // get the destination territory name from user and check if its a neighbor of the start territory, if it is MOVE order, check if it belongs to the player
    private String getDestTerritoryNameFromUser(int playerId, String fromTerritoryName, String orderType) {
        while(true) {
            try (Scanner scanner = new Scanner(System.in)) {
                String input = scanner.nextLine();
                if(boardMap.getTerritoryByName(input) != null) {
                    if(checkNeighbor(fromTerritoryName, input)) {
                        if(orderType.equals("MOVE")) {
                            if(boardMap.getTerritoryByName(input).getOwnId() == playerId) {
                                return input;
                            } else {
                                System.out.println("You can't move to " + input);
                                continue;
                            }
                        } else {
                            return input;
                        }
                    } else {
                        System.out.println(input + " is not a neighbor of " + fromTerritoryName);
                        continue;
                    }
                } else {
                    System.out.println("Please enter a valid territory name");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid territory name");
                continue;
            }
        }
    }

    public Move issueMoveOrder(int playerId) {
        System.out.println("Please enter the territory you want to move from:");
        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
        System.out.println("Please enter the territory you want to move to:");
        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "MOVE");
        System.out.println("Please enter the number of units you want to move:");
        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
        return new Move(numUnits, boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Attack, playerId);
    }

    public Attack issueAttackOrder(int playerId) {
        System.out.println("Please enter the territory you want to attack from:");
        String fromTerritoryName = getStartTerritoryNameFromUser(playerId);
        System.out.println("Please enter the territory you want to attack:");
        String toTerritoryName = getDestTerritoryNameFromUser(playerId, fromTerritoryName, "ATTACK");
        System.out.println("Please enter the number of units you want to attack with:");
        int numUnits = getNumUnitsFromUser(boardMap.getTerritoryByName(fromTerritoryName).getUnitNum());
        return new Attack(numUnits, boardMap.getTerritoryByName(fromTerritoryName), boardMap.getTerritoryByName(toTerritoryName), Type.Attack, playerId);
    }

    public Commit issueCommitOrder(int playerId) {
        return new Commit(0, null, null, Type.Commit, playerId);
    }

    private boolean scanYN() throws IOException {
        while(true) {
            try (Scanner scanner = new Scanner(System.in)) {
                String input = scanner.nextLine();
                if(input.equals("Y")) {
                    return true;
                } else if(input.equals("N")) {
                    return false;
                } else {
                    System.out.println("Please enter Y or N");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Please enter Y or N");
                continue;
            }
        }
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
        for(Territory territory : boardMap.getTerritories()) {
            System.out.println(territory.displayInfo());
        }
        System.out.println("-------------");
        System.out.println("Winner: " + boardMap.getWinner());
        System.out.println("Would you like to play again? (Y/N)");
        return scanYN();
    }
}