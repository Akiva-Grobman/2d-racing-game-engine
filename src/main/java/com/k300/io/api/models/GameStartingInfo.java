package com.k300.io.api.models;


/*
*       Purpose:
*           this represents the message the client will receive from the server in the beginning of a match.
*       Contains:
*           this will contain a room object and a player object (see more in the Room and Player classes).
*           basically in what room is this client playing and the client starting information.
*/

@SuppressWarnings("unused")
public class GameStartingInfo {

    // room field
    private Room room;
    // player field
    private Player player;

    // room assessor
    public Room getRoom() {
        return room;
    }

    // room modifier
    public void setRoom(Room room) {
        this.room = room;
    }

    // player assessor
    public Player getPlayer() {
        return player;
    }

    // player modifier
    public void setPlayer(Player player) {
        this.player = player;
    }

    // over ride to string method
    @Override
    public String toString() {
        return "CarData{" +
                "room=" + room +
                ", player=" + player +
                '}';
    }

}
