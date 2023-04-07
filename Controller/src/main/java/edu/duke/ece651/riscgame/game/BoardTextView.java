package edu.duke.ece651.riscgame.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BoardTextView {
    private boolean isFailed;
    private BoardMap boardMap;
    
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
        System.out.println("Player " + playerName + " :");
        System.out.println("-------------");
        for(Territory territory : boardMap.getTerritoriesByOwnerName(playerName)) {
            System.out.println(territory.displayInfo());
        }
    }

    public boolean printDeathInfo(String playerName) {
        // print the death info to the player (lose all territories) and asked if he wants to continue watching the game
        System.out.println("Player " + playerName + " has lost all territories!");
        System.out.println("You have been defeated. Do you want to continue watching the game? (Y/N)");
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

    public void printEndInfo(String playerName) {

    }
}
