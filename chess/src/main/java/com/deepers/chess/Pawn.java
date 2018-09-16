package com.deepers.chess;

/**
 * Created by Deepers on 9/14/2018.
 */
public class Pawn extends Piece {
    public Pawn(COLOR c) {
        super(c, "Pawn");
    }

    /**
     * Pawns can make these moves:
     * 1. 1 row (positive for white, negative for black)
     * 2. 2 rows if starting from home (i.e. row 2 to 4 for white, row 7 to 5 for black)
     * 3. 1 diagonal square if taking an opponent's piece
     */
}
