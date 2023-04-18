package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public interface GameMap {
    /**
     * @param {ArrayList<Territory>} territories: the territories to be set
     * @return {*}
     * @description: This function sets the territories
     */
    public void setTerritories(ArrayList<Territory> territories);

    /**
     * @return {ArrayList<Territory>} territories: the territories
     * @description: This function gets the territories
     */
    public ArrayList<Territory> getTerritories();
    public ArrayList<Player> getAllPlayerList();
    public void setAllPlayerList(ArrayList<Player> allPlayerList);
    public Player getPlayerById(int id);
    public Player getPlayer(int playerID);
    /**
     * @param {String} name
     * @return {*}
     * @description: This function gets the territory by name
     */
    public Territory getTerritoryByName(String name);

    /**
     * @return {*}
     * @description: This function gets the territories by owner Name
     */
    public ArrayList<Territory> getTerritoriesByOwnerName(String ownerName);

    /**
     * this function adds one unit in all territories
     */
    public void callUp();

    public Vector<Territory> getTerritoriesByOwnId(int ownId);

    public HashMap<String, Integer> getTerritoryNameAndUnitNums();

    public HashMap<String, Integer> getTerritoryNameAndOwnership();

    public void setTerritoryNameAndUnitNums(HashMap<String, Integer> territoryNameAndUnitNums);


    public void setTerritoryNameAndOwnership(HashMap<String, Integer> territoryNameAndOwnership);

    public boolean isAllTerritoryOccupiedByOne();

    public String getWinner();
    /* Determines whether the specified player has lost the game based on the territories they currently own.
     * @param playerId the ID of the player to check for loss
     * @return true if the player has lost (i.e. does not own any territories), false otherwise
     */
    public boolean isLose(int playerId);

    Player getPlayerByName(String playerName);
}
