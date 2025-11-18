package by.shift.minesweeper.gameplay;

final class Neighbour {

    private Neighbour() {
    }

    private static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public static int[][] getNeighbours() {
        int[][] copy = new int[NEIGHBOURS.length][];
        for (int i = 0; i < NEIGHBOURS.length; i++) {
            copy[i] = NEIGHBOURS[i].clone();
        }
        return copy;
    }
}
