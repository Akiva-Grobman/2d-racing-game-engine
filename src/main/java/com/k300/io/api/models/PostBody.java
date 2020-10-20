package com.k300.io.api.models;

@SuppressWarnings("unused")
public class PostBody {

    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "PostBody{" +
                "player=" + player +
                '}';
    }
}
