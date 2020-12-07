import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileActions fileActions = new FileActions();
        fileActions.doAction();
    }
}

class FileActions {

    public void doAction() {
        try {
            writeListInFile(readFile("read_file.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Считывает файл
    // и возвращает список строк из слов в файле
    public List<String> readFile(String fileName) {
        List<String> list = new ArrayList<>();
        try {
            LineNumberReader reader =
                    new LineNumberReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] arr = removePunct(line).split(" ");
                list.addAll(Arrays.asList(arr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Записывает список из строк в файл
    private void writeListInFile(List<String> list) throws IOException {
        File file = new File("write_file.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String str : list) {
            writer.write(str + "\n");
        }
        writer.close();
    }

    // Удаляет из строки все символы пунктуации
    private String removePunct(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c) ||
                    Character.isDigit(c) ||
                    Character.isSpaceChar(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }
}