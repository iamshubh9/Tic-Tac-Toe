package tictactoe.model;

public class Board {

    int size;
    PlayingPiece[][] board;

    public int getSize() {
        return size;
    }

    public PlayingPiece[][] getBoard() {
        return board;
    }

    public Board(int size, PlayingPiece[][] board) {
        this.size = size;
        this.board = board;
    }

    public boolean addPiece(PlayingPiece playingPiece, int row, int column) {
        if (board[row][column] == null) {
            board[row][column] = playingPiece;
            return true;
        }
        else {
            System.out.println("Enter correct row and column");
            return false;
        }

    }

}
