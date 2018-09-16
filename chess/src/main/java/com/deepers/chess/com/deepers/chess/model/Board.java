package com.deepers.chess.com.deepers.chess.model;

import android.util.Log;

import com.deepers.chess.Bishop;
import com.deepers.chess.King;
import com.deepers.chess.Knight;
import com.deepers.chess.Pawn;
import com.deepers.chess.Piece;
import com.deepers.chess.Queen;
import com.deepers.chess.Rook;

import java.util.ArrayList;

/**
 * Created by Deepers on 9/14/2018.
 */
public class Board {
    private static final String TAG = Board.class.getSimpleName();
    // we index the board from 1-8, ignoring index 0
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final char[] COL = new char[]{'-', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    private Piece.COLOR player = Piece.COLOR.WHITE; // who's turn it is (white always starts)

    private Cell srcCell = null;
    private Cell destCell = null;

    private Cell[][] mBoard;

    public Board() {
        mBoard = new Cell[ROWS][COLUMNS];

        // Cell A1 is the White player's bottom left corner
        // D1 and D8 are for the Queens.

        for (int r = ROWS - 1; r > 0; --r) {
            for (int c = 1; c < COLUMNS; ++c) {
                if (r == 2) {
                    mBoard[r][c] = new Cell(new Pawn(Piece.COLOR.WHITE), r, c);
                } else if (r == 7) {
                    mBoard[r][c] = new Cell(new Pawn(Piece.COLOR.BLACK), r, c);
                } else if (r == 1 || r == 8){
                    Piece.COLOR color;
                    if (r == 1) {
                        color = Piece.COLOR.WHITE;
                    } else {
                        color = Piece.COLOR.BLACK;
                    }
                    Piece p;
                    switch (c) {
                        case 1:
                        case 8:
                            p = new Rook(color);
                            break;
                        case 2:
                        case 7:
                            p = new Knight(color);
                            break;
                        case 3:
                        case 6:
                            p = new Bishop(color);
                            break;
                        case 4:
                            p = new Queen(color);
                            break;
                        case 5:
                            p = new King(color);
                            break;
                        default:
                            p = null;
                    }

                    mBoard[r][c] = new Cell(p, r, c);

                } else {
                    mBoard[r][c] = new Cell(null, r, c);
                }
            }
        }
        Log.d(TAG, toString());
    }

    /**
     * Represent a square on the board. Knows whether it has piece on it.
     * All methods are package private so the Board is the only entity allowed
     * to create or edit the Cell. A list of Cells may be returned to the
     * View/Activity via the findValidMoves() method, but that view can only
     * access the coordinates of those Cells.
     */
    public class Cell {
        private Piece myPiece = null;
        public final int row;
        public final int col;

        Cell(Piece p, int row, int col) {
            myPiece = p; // may be null for empty cells
            this.row = row;
            this.col = col;
        }

        boolean hasPiece() {
            return myPiece != null;
        }
        Piece getPiece() {
            return myPiece;
        }

        void setPiece(Piece p) {
            myPiece = p;
        }

        boolean movePiece(Cell dest) {
            if (dest.hasPiece()) {
                return false;
            }
            dest.setPiece(myPiece);
            myPiece = null;
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Current Board: \n\n");
        sb.append("  A  B  C  D  E  F  G  H\n");
        for (int r = ROWS - 1; r > 0; --r) {
            sb.append(r + " ");
            for (int c = 1; c < COLUMNS; ++c) {
                if (mBoard[r][c].hasPiece()) {
                    sb.append(mBoard[r][c].getPiece().toString());
                    sb.append(" ");
                } else {
                    sb.append("*  ");
                }
            }
            sb.append(r + "\n");
        }
        sb.append("  A  B  C  D  E  F  G  H\n\n");

        return sb.toString();
    }

    public boolean selectSourceCell(int row, int col) {
        if (!isFirstChoiceValid(row, col)) {
            return false;
        }

        srcCell = mBoard[row][col];
        return true;
    }

    public void unselectSourceCell() {
        srcCell = null;
    }

    public boolean selectDestCell(int row, int col) {
        if (!isSecondChoiceValid(row, col)) {
            return false;
        }

        destCell = mBoard[row][col];

        // TODO evaluate and make the move; may need to remove a piece from the opponent's set

        // switch players
        player = player == Piece.COLOR.BLACK ? Piece.COLOR.WHITE : Piece.COLOR.BLACK;

        return true;
    }

    private boolean isFirstChoiceValid(int row, int col) {
        if (row == 0 || col == 0 || row >= ROWS || col >= COLUMNS) {
            return false;
        }
        // only valid if cell contains a piece of the same color as the current player
        Cell cell = mBoard[row][col];
        if (cell.hasPiece() && cell.getPiece().color() == player) {
            return true;
        }
        return false;
    }

    private boolean isSecondChoiceValid(int row, int col) {
        if (row == 0 || col == 0 || row >= ROWS || col >= COLUMNS) {
            return false;
        }
        // TODO fill in this logic
        // only valid IF cell doesn't have a piece of the same color
        // AND cell is in valid direction AND valid distance from source cell based on Piece properties
        // AND if this player's King is not placed in check.

        Cell cell = mBoard[row][col];

        return false;

    }

    public ArrayList<Cell> findValidMoves() {
        if (srcCell == null) {
            return null;
        }

        return new ArrayList<Cell>(); // TODO populate this with valid cells
    }

    public Piece.COLOR whoseTurnIsIt() {
        return player;
    }
}
