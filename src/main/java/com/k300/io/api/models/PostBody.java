package com.k300.io.api.models;

/*
*       Purpose:
*           this will be sent in each post request to update the servers representation of the local player.
*       Contains:
*           a player object representing the local player.
 */

@SuppressWarnings("unused")
public class PostBody {

    // player field
    private Player player;

    // player accessor
    public Player getPlayer() {
        return player;
    }

    // player modifier
    public void setPlayer(Player player) {
        this.player = player;
    }

    // over ride to string
    @Override
    public String toString() {
        return "PostBody{" +
                "player=" + player +
                '}';
    }
}
