package io.deeplay.camp.game;

public class Main {
    public static void main(String[] args) {
        int size = 4;
        String[] names = {"player0", "player1"};
        SelfPlay selfPlay = new SelfPlay(size, names);
        selfPlay.playGame();
    }
}
