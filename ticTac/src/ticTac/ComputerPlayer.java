package ticTac;

import javax.swing.JButton;

public class ComputerPlayer {

    private final int PLAYER = 1;
    private final int COMPUTER = 2;

    public int takeTurn(JButton[] buttons) {
    	
    	// Check if there is a winning move available
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().isEmpty()) {
                buttons[i].setText("O");
                int result = checkWinner(buttons);
                buttons[i].setText("");
                if (result == COMPUTER) {
                    return i;
                }
            }
        }
    	
    	
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().isEmpty()) {
                buttons[i].setText("O");
                int score = miniMax(buttons, 0, false);
                buttons[i].setText("");
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private int miniMax(JButton[] buttons, int depth, boolean isMaximizing) {
        int result = checkWinner(buttons);
        if (result != 0) {
            return result * depth;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].getText().isEmpty()) {
                    buttons[i].setText("O");
                    int score = miniMax(buttons, depth + 1, false);
                    buttons[i].setText("");
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].getText().isEmpty()) {
                    buttons[i].setText("X");
                    int score = miniMax(buttons, depth + 1, true);
                    buttons[i].setText("");
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    private int checkWinner(JButton[] buttons) {
        int[][] winConditions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
        		{0, 4, 8}, {2, 4, 6}};
        for (int[] winCondition : winConditions) {
            if (!buttons[winCondition[0]].getText().isEmpty() &&
                buttons[winCondition[0]].getText().equals(buttons[winCondition[1]].getText()) &&
                buttons[winCondition[1]].getText().equals(buttons[winCondition[2]].getText())) {
                return buttons[winCondition[0]].getText().equals("X") ? PLAYER : COMPUTER;
            }
        }
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return 0;
            }
        }
        return 0;
    }
}
