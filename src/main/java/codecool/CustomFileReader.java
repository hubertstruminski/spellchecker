package codecool;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader {

    private List<String> wordList;
    private final String path = "/home/hubert/Pulpit/spellchecker/src/main/resources/";

    public CustomFileReader() {
        wordList = new ArrayList<>();
    }

    public List<String> readWordList(String fileName) {
        try {
            wordList = Files.readAllLines(new File(path + fileName).toPath(), Charset.forName("utf-8"));
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return wordList;
    }
}
