package tictactoe.tictactoe.game;

import tictactoe.model.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Game {

    Deque<Player> players;
    Board board;

    public void initializeGame() {
        board = new Board(3, new PlayingPiece[3][3]);
        Player playerX = new Player("PlayerX", new PlayingPieceX());
        Player playerO = new Player("PlayerO", new PlayingPieceO());
        players = new ArrayDeque<>();
        players.add(playerX);
        players.add(playerO);
    }

    public void startGame() {
        System.out.println("Game Started");
        System.out.println("First Player : " + players.getFirst());
        System.out.println("Second Player : " + players.getLast());
        printBoard();
        boolean isWinnerOrTie = false;
        Scanner sc = new Scanner(System.in);;

        while(!isWinnerOrTie){
            Player player = players.removeFirst();
            int row, column;
            boolean addPossible = false;
            System.out.println("Player turn : " + player.getName());
            while(!addPossible) {
                System.out.println("Enter position : ");
                System.out.println("Enter row : ");
                row = sc.nextInt();
                System.out.println("Enter column : ");
                column = sc.nextInt();
                addPossible = board.addPiece(player.getPlayingPiece(), row, column);
            }
            printBoard();
            boolean winner = isWinner(player);
            if (winner) {
                System.out.println("The winner is " + player.getName());
                isWinnerOrTie = true;
                return;
            }
            boolean tie = isTie();
            if (tie) {
                System.out.println("The game tied.");
                isWinnerOrTie = true;
                return;
            }
            players.addLast(player);
        }

    }

    private void printBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                System.out.print(" | " + (board.getBoard()[i][j] != null ? board.getBoard()[i][j] : " "));
            }
            System.out.println(" |");
        }
    }

    private boolean isWinner(Player player) {
        PlayingPiece piece = player.getPlayingPiece();
        PieceType pieceType = piece.getType();

        int count = 0;
        //check all rows
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j] != null){
                    if (board.getBoard()[i][j].getType().name().equals(player.getPlayingPiece().getType().name())) {
                        count++;
                    }
                }
            }
            if(count == board.getSize()){
                return true;
            }
            count = 0;
        }

        //check all columns
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[j][i] != null){
                    if (board.getBoard()[j][i].getType().name().equals(player.getPlayingPiece().getType().name())) {
                        count++;
                    }
                }
            }
            if(count == board.getSize()){
                return true;
            }
            count = 0;
        }

        //check all diagonals
        //(0,0)(1,1)(2,2)
        //(2,0)(1,1)(0,2)
        int count2 = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (i == j) {
                    if (board.getBoard()[i][j] != null){
                        if (board.getBoard()[i][j].getType().name().equals(player.getPlayingPiece().getType().name())) {
                            count++;
                        }
                    }
                }
                if (i == (board.getSize() -j -1)) {
                    if (board.getBoard()[i][j] != null){
                        if (board.getBoard()[i][j].getType().name().equals(player.getPlayingPiece().getType().name())) {
                            count2++;
                        }
                    }
                }
            }
        }
        if(count == board.getSize() || count2 == board.getSize()){
            return true;
        }

        return false;
    }

    private boolean isTie() {
        boolean tie = true;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j] == null) {
                    tie = false;
                }
            }
        }
        return tie;
    }

}
