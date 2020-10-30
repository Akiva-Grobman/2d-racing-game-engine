package com.k300.io.api.models;

import java.util.Arrays;

/*
*       Purpose:
*           this is the response object from the server that represents the up to date state of the room and players.
*       Contains:
*           a room object and an array of players (including the local player).
*/

@SuppressWarnings("unused")
public class PostResponse {

    // room field
    private Room room;
    // player array field
    private Player[] players;

    // player array accessor
    public Player[] getPlayers() {
        return players;
    }

    // player array modifier
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    // room accessor
    public Room getRoom() {
        return room;
    }

    // room modifier
    public void setRoom(Room room) {
        this.room = room;
    }

    // over ride to string
    @Override
    public String toString() {
        return "PostResponse{" +
                "room=" + room +
                ", players=" + Arrays.toString(players) +
                '}';
    }
}
