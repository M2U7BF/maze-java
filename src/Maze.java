import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Maze {

    private int width;
    private int height;
    private int[][] field;
    private int[] goal;
    private int[] start;

    public Maze() {
        int height = InputHandler.getInputInRange("1~100で、高さを入力: ", 1, 100);
        int width = InputHandler.getInputInRange("1~100で、幅を入力: ", 1, 100);

        try {
            this.setHeight(height);
            this.setWidth(width);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setField(new int[width][height]);
    }


    public int[][] getField() {
        return field;
    }
    public String printField(){
        String str = "";
        int[][] array = this.field;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    str += "⬛";
                } else {
                    str += "  ";
                }
            }
            str += "\n";
        }

        return str;
    }
    public void setField(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            Arrays.fill(field[i], 1);
        }

        this.field = field;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) throws IllegalArgumentException {
        if (height < 0 || height > 100) {
            throw new IllegalArgumentException("heightは、1~100の範囲で設定してください。");
        }

        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) throws IllegalArgumentException {
        if (width < 0 || width > 100) {
            throw new IllegalArgumentException("widthは、1~100の範囲で設定してください。");
        }

        this.width = width;
    }

    public void setStartGoal(){
        // 同じ、または隣接の場合再試行
    }

    public void setAnswerRoute(){
        this.digging(this.start);

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
        int[] coordinate = this.getRandomAroundCoordinate(currentCoordinate, 0);

        if(coordinate == null){
            // 終了
            return;
        }

        // 上下左右のいずれかの方向に２マス先を見て、そこが通路でなければ道を延ばします。
        if(!isCorridor(coordinate)){ // 通路でない場合
            stretchCorridors(currentCoordinate ,coordinate);
        } else { // 通路である場合
            // 道を延ばす事ができなくなれば、この時既にある道からランダムに点（但し、Ｘ座標とＹ座標が偶数の点）を選んで道を延ばします。
            int[] corridorCoordinate = getRandomCorridorCoordinate();
            digging(corridorCoordinate);
        }
    }
    private boolean isEdge(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];

        return (x == this.height || x == this.width) || (y == this.height || y == this.width);
    }
    private int[] getRandomCorridorCoordinate() {
        Random random = new Random();
        int rows = this.field.length;
        int cols = this.field[0].length;

        // 1のインデックスを収集する
        List<int[]> oneIndices = new ArrayList<>();
        for (int i = 1; i < rows; i += 2) {
            for (int j = 1; j < cols; j += 2) {
                if (this.field[i][j] == 1) {
                    oneIndices.add(new int[]{i, j});
                }
            }
        }

        if (oneIndices.isEmpty()) {
            return null; // 配列に1が存在しない場合はnullを返す
        }

        // ランダムなインデックスを選ぶ
        int randomIndex = random.nextInt(oneIndices.size());
        return oneIndices.get(randomIndex);
    }
    private void stretchCorridors(int[] currentCoordinate, int[] coordinate) {
        int row1 = currentCoordinate[0];
        int col1 = currentCoordinate[1];
        int row2 = coordinate[0];
        int col2 = coordinate[1];

        this.field[row1][col1] = 0;
        this.field[row1 + Math.abs(row2 - row1)][col1 + Math.abs(col2 - col1)] = 0;
        this.field[row2][col2] = 0;
    }
    private int[] getRandomAroundCoordinate(int[] coordinate, int count) {
        if(count == 10){
            return null;
        }

        int x = coordinate[0];
        int y = coordinate[1];

        Random random = new Random();
        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
        int[] randomDirection = directions[random.nextInt(directions.length)];

        int newX = x + randomDirection[0];
        int newY = y + randomDirection[1];

        if (newX >= 0 && newX <= this.width && newY >= 0 && newY <= this.height) {
            return new int[]{newX, newY};
        } else {
            count++;
            return getRandomAroundCoordinate(coordinate, count);
        }
    }
    private boolean isCorridor(int[] coordinate) {
        return this.field[coordinate[0]][coordinate[1]] == 0;
    }

    // - maze
    // - field : int[width][height]
    //     - 最初に全て1で初期化。
    //     - 1がブロック。0が空。
}
