public class App {
    public static void main(String[] args) throws Exception {
        Maze maze = new Maze();

        maze.setAnswerRoute();
        System.out.println(maze.printField());
        maze.digging(null);
        System.out.println(maze.printField());
    }
}
