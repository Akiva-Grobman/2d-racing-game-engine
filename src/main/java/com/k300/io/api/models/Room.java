package com.k300.io.api.models;

import java.util.Arrays;

/*
*       Purpose:
*           represent a JSon object in a POJO
*       Contains:
*           a room id, number of players in the room, a list of all players in the room, if the game has started
*/

@SuppressWarnings("unused")
public class Room {

    // the room id field
    private String id;
    // the sum of player field
    private String maxPlayers;
    // the list of players field
    private String[] playersList;
    // the boolean of if the game is active field
    private boolean gameStarted;

    // id accessor
    public String getId() {
        return id;
    }

    // id modifier
    public void setId(String id) {
        this.id = id;
    }

    // sum of players accessor
    public String getMaxPlayers() {
        return maxPlayers;
    }

    // sum of players modifier
    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    // player list accessor
    public String[] getPlayersList() {
        return playersList;
    }

    // player list modifier
    public void setPlayersList(String[] playersList) {
        this.playersList = playersList;
    }

    // game status accessor
    public boolean isGameStarted() {
        return gameStarted;
    }

    // game status modifier
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    // object toString
    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", maxPlayers='" + maxPlayers + '\'' +
                ", playersList=" + Arrays.toString(playersList) +
                ", gameStarted=" + gameStarted +
                '}';
    }

}
