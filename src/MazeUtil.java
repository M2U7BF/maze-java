import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MazeUtil {
    public static void exportTxtFile(String content) {
        String filePath = "./output"+ getNowTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +".txt"; // ファイルパスの設定

        // - 1 → ■、0→ 空白 と解釈

        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            System.out.println("txtファイルが生成されました。");
        } catch (IOException e) {
            System.out.println("ファイルの生成に失敗しました。");
            e.printStackTrace();
        }
    }

    public static String getNowTime(DateTimeFormatter formatter){
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Invalid range");
        }

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
