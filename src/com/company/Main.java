package com.company;
                                                                // LOOK AT TEXT FILES 
public class Main {

    public static class SkeletonProgram {

        char board[][];
        Player playerOne;
        Player playerTwo;

        Console console = new Console();

        public SkeletonProgram() {
            console.printLeaderBoard();

            board = new char[5][5];                                                          // the names now cannot be the same or blank
            playerOne = new Player(console.readLine("What is the name of player one? "));
            playerTwo = new Player(console.readLine("What is the name of player two? "));
            while (playerOne.toString().equals(playerTwo.toString())) {
                System.out.println("names entered must be different");
                playerOne = new Player(console.readLine("What is the name of player one?"));
                playerTwo = new Player(console.readLine("what is the name of player two?"));
            }
            while (playerOne.getName().isEmpty() || playerTwo.getName().isEmpty()){
                System.out.println("blank names are invalid");
                playerOne = new Player(console.readLine("What is the name of player one?"));
                playerTwo = new Player(console.readLine("what is the name of player two?"));
                playerOne.setScore(0);
                playerTwo.setScore(0);


            }

            do {
                playerOne.setSymbol(console.readChar((playerOne.getName()
                        + " what symbol do you wish to use X or O? ")));
                if (playerOne.getSymbol() != 'X' && playerOne.getSymbol() != 'O') {
                    console.println("Symbol to play must be uppercase X or O");
                }
            } while (playerOne.getSymbol() != 'X' && playerOne.getSymbol() != 'O');

            if (playerTwo.getSymbol() == 'X') {
                playerTwo.setSymbol('O');
            } else {
                playerTwo.setSymbol('X');
            }

            char startSymbol = 'X';
            char replay;

            do {
                int noOfMoves = 0;
                boolean gameHasBeenDrawn = false;
                boolean gameHasBeenWon = false;
                clearBoard();
                console.println();
                displayBoard();
                if (startSymbol == playerOne.getSymbol()) {
                    console.println(playerOne.getName() + " starts playing " + startSymbol);
                } else {
                    console.println(playerTwo.getName() + " starts playing " + startSymbol);
                }
                console.println();
                char currentSymbol = startSymbol;
                boolean validMove;
                Coordinate coordinate;
                do {

                    do {

                        coordinate = getMoveCoordinates();
                        validMove = checkValidMove(coordinate, board);
                        if (!validMove) {
                            console.println("Coordinates invalid, please try again");
                        }
                    } while (!validMove);

                    board[coordinate.getX()][coordinate.getY()] = currentSymbol;
                    displayBoard();
                    gameHasBeenWon = checkXOrOHasWon();
                    noOfMoves++;

                    if (!gameHasBeenWon) {

                        if (noOfMoves == 9) {
                            gameHasBeenDrawn = true;
                        } else {

                            if (currentSymbol == 'X') {
                                currentSymbol = 'O';
                            } else {
                                currentSymbol = 'X';
                            }

                        }
                    }

                } while (!gameHasBeenWon && !gameHasBeenDrawn);

                if (gameHasBeenWon) {
                    if (playerOne.getSymbol() == currentSymbol) {
                        console.println(playerOne.getName() + " congratulations you win!");
                        playerOne.addScore();
                    } else {
                        console.println(playerTwo.getName() + " congratulations you win!");
                        playerTwo.addScore();
                    }
                } else {
                    console.println("A draw this time!");
                }

                console.println("\n" + playerOne.getName() + " your score is: " + String.valueOf(playerOne.getScore()));
                console.println(playerTwo.getName() + " your score is: " + String.valueOf(playerTwo.getScore()));
                console.println();
                if (startSymbol == playerOne.getSymbol()) {
                    startSymbol = playerTwo.getSymbol();
                } else {
                    startSymbol = playerOne.getSymbol();
                }
                replay = console.readChar("\n Another game Y/N? ");
            } while (replay != 'N' && replay != 'n');

            console.writeFile(playerOne.toString());
            console.writeFile(playerTwo.toString());
        }

        void displayBoard() {
            int row;
            int column;
            console.println(" | 1 2 3 4 ");
            console.println("--+-----------");
            for (row = 1; row <= 4; row++) {
                console.write(row + " | ");
                for (column = 1; column <= 4; column++) {
                    console.write(board[column][row] + " ");
                }
                console.println();
            }
        }

        void clearBoard() {
            int row;
            int column;
            for (row = 1; row <= 4; row++) {
                for (column = 1; column <= 4; column++) {
                    board[column][row] = ' ';
                }
            }
        }

        Coordinate getMoveCoordinates() {
            Coordinate coordinate = new Coordinate(console.readInteger("Enter x Coordinate: "), console.readInteger("Enter y Coordinate: "));
            return coordinate;
        }

        boolean checkValidMove(Coordinate coordinate, char[][] board) {
            boolean validMove;
            validMove = true;
            if (coordinate.getX() < 1 || coordinate.getX() > 3) {
                validMove = false;
            }
            return validMove;
        }

        boolean checkXOrOHasWon() {
            boolean xOrOHasWon;
            int row;
            int column;
            xOrOHasWon = false;

            for (column = 1; column <= 3; column++) {
                if (board[column][1] == board[column][2]
                        && board[column][2] == board[column][3]
                        && board[column][2] != ' ') {
                    xOrOHasWon = true;
                }
            }
            for (row = 1; row <= 3; row++) {
                if (board[1][row] == board[2][row]
                        && board[2][row] == board[3][row]
                        && board[2][row] != ' ') {
                    xOrOHasWon = true;
                }
            }
            if (board [1][1] == board [2][2]
                && board [2][2] == board [3][3]
                && board [2][2] != ' '){
                xOrOHasWon = true;
            }
            if (board [1][3] == board[2][2]
                && board [2][2] == board [3] [1]
                && board [2][2] != ' '){
                xOrOHasWon = true;
            }
            return xOrOHasWon;
        }
    }

    public static void main(String[] args) {
        new SkeletonProgram();
    }
}