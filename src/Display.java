public class Display {
    public void render(Maze maze) {
        StringBuilder str = new StringBuilder();
        int[][] field = maze.getField();

        for (int[] row : field) {
            for (int cell : row) {
                str.append(cell);
            }
            str.append("\n");
        }

        System.out.println(str.toString());
    }
}
