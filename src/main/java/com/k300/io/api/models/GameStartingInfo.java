package com.k300.io.api.models;

@SuppressWarnings("unused")
public class GameStartingInfo {

    private Room room;
    private Player player;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "CarData{" +
                "room=" + room +
                ", player=" + player +
                '}';
    }

}
