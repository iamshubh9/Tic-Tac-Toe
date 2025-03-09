package tictactoe.model;

public class PlayingPiece {
    PieceType type;

    public PlayingPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type == null) {
            return " ";
        }
        return type.name();
    }

}
