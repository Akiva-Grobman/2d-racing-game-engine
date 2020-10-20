package com.k300.io.api.models;

import java.util.Arrays;

public class PostResponse {

    private Room room;
    private Player[] players;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "room=" + room +
                ", players=" + Arrays.toString(players) +
                '}';
    }
}
