package com.k300.io.api.models;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Room {

    private String id;
    private String maxPlayers;
    private String[] playersList;
    private boolean gameStarted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String[] getPlayersList() {
        return playersList;
    }

    public void setPlayersList(String[] playersList) {
        this.playersList = playersList;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

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
