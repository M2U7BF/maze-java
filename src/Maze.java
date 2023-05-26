public class Maze {

    private int width;
    private int height;
    private int[][] field;
    private int[] goal;
    private int[] start;

    public Maze(int width, int height){
        this.field = new int[width][height];
    }

    public void setAnswerRoute(){}
    public void digging(){}

    // - maze
    // - field : int[width][height]
    //     - 最初に全て1で初期化。
    //     - 1がブロック。0が空。

    // - func setAnswer
    //     - field外にアクセスできない。
    //     - 最外列にアクセスした場合
    //         - 正当経路の長さが$\frac{(height+width)}{2}$以下の場合、再試行。
    //         - その他、ゴールとする
    //     - 進んだマスが空マスに隣接しない。
    // - func digging
    //     - https://algoful.com/Archive/Algorithm/MazeDig
}
