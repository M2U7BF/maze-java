public class Maze {

    private int width;
    private int height;
    private int[][] field;
    private int[] goal;
    private int[] start;

    // public Maze(int width, int height){
    //     this.field = new int[width][height];
    // }

    public int[][] getField() {
        return field;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void setStartGoal(){
        // 同じ、または隣接の場合再試行
    }

    public void setAnswerRoute(){
        this.digging();

        // - func setAnswer
        //     - field外にアクセスできない。
        //     - 最外列にアクセスした場合
        //         - 正当経路の長さが$\frac{(height+width)}{2}$以下の場合、再試行。
        //         - その他、ゴールとする
        //     - 進んだマスが空マスに隣接しない。
    }

    public void digging(int[] currentCoordinate){
        if(currentCoordinate == null){
            currentCoordinate = new int[2];
        }

        // ランダムに、上下左右のマスを確認
        int[] coordinate = this.getRandomAroundCoordinate(currentCoordinate);

        // 上下左右のいずれかの方向に２マス先を見て、そこが通路でなければ道を延ばします。
        if(!isCorridor(coordinate)){ // 通路でない場合
            stretchCorridors(currentCoordinate ,coordinate);
        } else { // 通路である場合
            // 道を延ばす事ができなくなれば、この時既にある道からランダムに点（但し、Ｘ座標とＹ座標が偶数の点）を選んで道を延ばします。
            int[] corridorCoordinate = getRandomCorridorCoordinate();
            digging(corridorCoordinate);
        }
    }
    private int[] getRandomCorridorCoordinate() {
        // TODO:作成
        return goal;
    }
    private void stretchCorridors(int[] currentCoordinate, int[] coordinate) {}
    private int[] getRandomAroundCoordinate(int[] currentCoordinate) {
        int[] coordinate = new int[2];
        int randomNum1 = MazeUtil.generateRandomNumber(-2, 2);
        int randomNum2 = MazeUtil.generateRandomNumber(0, 2);

        coordinate[0] = currentCoordinate[0] + randomNum1;

        if (randomNum1 == 0) {
            coordinate[1] = currentCoordinate[1] + (randomNum2 == 0 ? 2 : -2);
        } else {
            coordinate[1] = currentCoordinate[1];
        }

        return coordinate;
    }
    private boolean isCorridor(int[] coordinate) {
        return this.field[coordinate[0]][coordinate[1]] == 0;
    }

    // - maze
    // - field : int[width][height]
    //     - 最初に全て1で初期化。
    //     - 1がブロック。0が空。
}
