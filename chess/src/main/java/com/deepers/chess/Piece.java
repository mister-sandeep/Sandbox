package com.deepers.chess;

/**
 * Created by Deepers on 9/14/2018.
 */
public class Piece {
    public enum COLOR {BLACK, WHITE};
    private String type = "Unknown";
    private COLOR color;

    public Piece(COLOR c, String type) {
        this.color = c;
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(color == COLOR.BLACK ? "B" : "W");
        sb.append(type.charAt(0));
        return sb.toString();
    }

    public COLOR color() {
        return color;
    }
}
