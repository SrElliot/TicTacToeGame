package es.codegym.games.ticktacktoe;

import com.codegym.engine.cell.*;

public class TicTacToeGame extends Game {
    
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    private int[][] model = new int[3][3];
    private int currentPlayer;
    private boolean isGameStopped;

    public void initialize() {
        setScreenSize(3, 3);
        startGame();
        updateView();
    }

    public void startGame() {
        isGameStopped = false;
        currentPlayer = PLAYER_ONE;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                model[x][y] = 0;
            }
        }
    }

    public void updateCellView(int x, int y, int value) {
        if (value == PLAYER_ONE) {
            setCellValueEx(x, y, Color.WHITE, "X", Color.RED);
        } else if (value == PLAYER_TWO) {
            setCellValueEx(x, y, Color.WHITE, "O", Color.BLUE);
        } else {
            setCellValueEx(x, y, Color.WHITE, " ", Color.GREEN);
        }
    }

    public void updateView() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                updateCellView(x, y, model[x][y]);
            }
        }
    }

    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            return;
        }
        
        if (model[x][y] != 0) {
            return;
        }

        setSignAndCheck(x, y);

        currentPlayer = 3 - currentPlayer;

        computerTurn();
        currentPlayer = 3 - currentPlayer;
    }

    public void setSignAndCheck(int x, int y){
        model[x][y] = currentPlayer;
        updateView();

        if (checkWin(x, y, currentPlayer)) {
            isGameStopped = true;
            if (currentPlayer == PLAYER_ONE) {
                showMessageDialog(Color.NONE, "You Win!", Color.GREEN, 75);
            } else {
                showMessageDialog(Color.NONE, "Game Over", Color.RED, 75);
            }
        }

        if (!hasEmptyCell()) {
            isGameStopped = true;
            showMessageDialog(Color.NONE, "Draw!", Color.BLUE, 75);
            return;
        }
    }

    public boolean checkWin(int x, int y, int n){
        if (model[x][0] == n && model[x][1] == n && model[x][2] == n) {
            return true;
        }

        if (model[0][y] == n && model[1][y] == n && model[2][y] == n) {
            return true;
        }

        if (model[0][0] == n && model[1][1] == n && model[2][2] == n) {
            return true;
        }

        if (model[0][2] == n && model[1][1] == n && model[2][0] == n) {
            return true;
        }

        return false;
    }

    public boolean hasEmptyCell(){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (model[x][y] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped || key == Key.ESCAPE) {
            startGame();
            updateView();
        }
    }

    public void computerTurn() {
        int opponent = 3 - currentPlayer;

        if (model[1][1] == 0) {
            setSignAndCheck(1, 1);
            return;
        }
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (model[x][y] == 0 && checkFutureWin(x, y, opponent)) {
                    setSignAndCheck(x, y);
                    return;
                }
            }
        }

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (model[x][y] == 0) {
                    setSignAndCheck(x, y);
                    return;
                }
            }
        }
    }

    public boolean checkFutureWin(int x, int y, int n){
        if (model[x][y] != 0) {
            return false;
        }

        model[x][y] = n;
        boolean isWin = checkWin(x, y, n);
        model[x][y] = 0;

        return isWin;
    }
}